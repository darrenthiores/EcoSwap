package com.darrenthiores.ecoswap.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.darrenthiores.ecoswap.android.presentation.boarding.AndroidBoardingViewModel
import com.darrenthiores.ecoswap.android.presentation.boarding.BoardingScreen
import com.darrenthiores.ecoswap.android.presentation.home.AndroidHomeViewModel
import com.darrenthiores.ecoswap.android.presentation.home.HomeScreen
import com.darrenthiores.ecoswap.android.presentation.item_detail.AndroidItemDetailViewModel
import com.darrenthiores.ecoswap.android.presentation.item_detail.ItemDetailScreen
import com.darrenthiores.ecoswap.android.presentation.login.AndroidLoginViewModel
import com.darrenthiores.ecoswap.android.presentation.login.LoginScreen
import com.darrenthiores.ecoswap.android.presentation.register.AndroidRegisterViewModel
import com.darrenthiores.ecoswap.android.presentation.register.RegisterScreen
import com.darrenthiores.ecoswap.android.presentation.search.AndroidSearchViewModel
import com.darrenthiores.ecoswap.android.presentation.search.SearchScreen
import com.darrenthiores.ecoswap.android.presentation.splash.SplashScreen
import com.darrenthiores.ecoswap.android.utils.AppBottomBar
import com.darrenthiores.ecoswap.android.utils.AppState
import com.darrenthiores.ecoswap.android.utils.Route
import com.darrenthiores.ecoswap.android.utils.TopLevelDestination
import com.darrenthiores.ecoswap.android.utils.rememberAppState
import com.darrenthiores.ecoswap.domain.utils.UiEvent

@Composable
fun EcoSwap(
    appState: AppState = rememberAppState(),
    shouldShowOnBoarding: Boolean = true,
    shouldShowAuth: Boolean = true,
) {
    val scaffoldState = appState.scaffoldState
    val navController = appState.navController

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                AppBottomBar(
                    currentDestination = appState.currentDestination,
                    onTabSelected = {
                        navController.navigate(it.name)
                    }
                )
            }
        },
        scaffoldState = scaffoldState
    ) { paddingValues ->
        NavHost(
            modifier = Modifier
                .padding(paddingValues),
            navController = navController,
            startDestination = Route.Splash.name
        ) {
            composable(Route.Splash.name) {
                SplashScreen {
                    if (shouldShowOnBoarding) {
                        navController.navigate(Route.Boarding.name)
                    } else if (shouldShowAuth) {
                        navController.navigate(Route.Login.name)
                    } else {
                        navController.navigate(TopLevelDestination.Home.name)
                    }
                }
            }

            composable(Route.Boarding.name) {
                val viewModel: AndroidBoardingViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                navController.navigate(Route.Login.name)
                            }
                            else -> Unit
                        }
                    }
                }

                BoardingScreen(
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }

            composable(Route.Login.name) {
                val viewModel: AndroidLoginViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                navController.navigate(TopLevelDestination.Home.name)
                            }
                            else -> Unit
                        }
                    }
                }

                LoginScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onSignUp = {
                        navController.navigate(Route.Register.name)
                    }
                )
            }

            composable(Route.Register.name) {
                val viewModel: AndroidRegisterViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                navController.navigate(TopLevelDestination.Home.name)
                            }
                            else -> Unit
                        }
                    }
                }

                RegisterScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onSignIn = {
                        navController.navigateUp()
                    }
                )
            }

            composable(TopLevelDestination.Home.name) {
                val viewModel: AndroidHomeViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                val searchText = viewModel.searchText

                HomeScreen(
                    state = state,
                    searchText = searchText,
                    onAndroidEvent = viewModel::onEvent,
                    onEvent = viewModel::onEvent,
                    onCategoryClick = {
                        navController.navigate(Route.Search.name + "?categoryId=$it")
                    },
                    onSearch = {
                        navController.navigate(Route.Search.name + "?searchText=$it")
                    },
                    onItemClick = { id ->
                        navController.navigate(Route.ItemDetail.name + "/$id")
                    },
                    onStoreClick = {

                    }
                )
            }

            composable(
                route = Route.Search.name + "?searchText={searchText}&categoryId={categoryId}",
                arguments = listOf(
                    navArgument("searchText") {
                        NavType.StringType
                        nullable = true
                        defaultValue = null
                    },
                    navArgument("categoryId") {
                        NavType.StringType
                        nullable = true
                        defaultValue = null
                    }
                )
            ) {
                val viewModel: AndroidSearchViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                val searchText = viewModel.searchText

                SearchScreen(
                    state = state,
                    searchText = searchText,
                    onEvent = viewModel::onEvent,
                    onAndroidEvent = viewModel::onEvent,
                    onItemClick = { id ->
                        navController.navigate(Route.ItemDetail.name + "/$id")
                    },
                    onStoreClick = {

                    },
                    onBackClicked = {
                        navController.navigateUp()
                    }
                )
            }

            composable(
                route = Route.ItemDetail.name + "/{itemId}",
                arguments = listOf(
                    navArgument("itemId") {
                        NavType.StringType
                    }
                )
            ) {
                val viewModel: AndroidItemDetailViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                ItemDetailScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onSettingClick = {  },
                    onUserClick = { },
                    onMessageClick = {  },
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}