package com.darrenthiores.ecoswap.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.darrenthiores.ecoswap.android.presentation.add_item.AddItemScreen
import com.darrenthiores.ecoswap.android.presentation.add_item.AndroidAddItemViewModel
import com.darrenthiores.ecoswap.android.presentation.add_progress.AddProgressScreen
import com.darrenthiores.ecoswap.android.presentation.add_progress.AndroidAddProgressViewModel
import com.darrenthiores.ecoswap.android.presentation.boarding.AndroidBoardingViewModel
import com.darrenthiores.ecoswap.android.presentation.boarding.BoardingScreen
import com.darrenthiores.ecoswap.android.presentation.challenge_detail.AndroidChallengeDetailViewModel
import com.darrenthiores.ecoswap.android.presentation.challenge_detail.ChallengeDetailScreen
import com.darrenthiores.ecoswap.android.presentation.home.AndroidHomeViewModel
import com.darrenthiores.ecoswap.android.presentation.home.HomeScreen
import com.darrenthiores.ecoswap.android.presentation.inbox.AndroidInboxViewModel
import com.darrenthiores.ecoswap.android.presentation.inbox.InboxScreen
import com.darrenthiores.ecoswap.android.presentation.item_detail.AndroidItemDetailViewModel
import com.darrenthiores.ecoswap.android.presentation.item_detail.ItemDetailScreen
import com.darrenthiores.ecoswap.android.presentation.login.AndroidLoginViewModel
import com.darrenthiores.ecoswap.android.presentation.login.LoginScreen
import com.darrenthiores.ecoswap.android.presentation.message.AndroidMessageViewModel
import com.darrenthiores.ecoswap.android.presentation.message.MessageScreen
import com.darrenthiores.ecoswap.android.presentation.other_profile.AndroidOtherProfileViewModel
import com.darrenthiores.ecoswap.android.presentation.other_profile.OtherProfileScreen
import com.darrenthiores.ecoswap.android.presentation.profile.AndroidProfileViewModel
import com.darrenthiores.ecoswap.android.presentation.profile.ProfileScreen
import com.darrenthiores.ecoswap.android.presentation.register.AndroidRegisterViewModel
import com.darrenthiores.ecoswap.android.presentation.register.RegisterScreen
import com.darrenthiores.ecoswap.android.presentation.search.AndroidSearchViewModel
import com.darrenthiores.ecoswap.android.presentation.search.SearchScreen
import com.darrenthiores.ecoswap.android.presentation.splash.SplashScreen
import com.darrenthiores.ecoswap.android.presentation.store_item_detail.AndroidStoreItemDetailViewModel
import com.darrenthiores.ecoswap.android.presentation.store_item_detail.StoreItemDetailScreen
import com.darrenthiores.ecoswap.android.presentation.store_profile.AndroidStoreProfileViewModel
import com.darrenthiores.ecoswap.android.presentation.store_profile.StoreProfileScreen
import com.darrenthiores.ecoswap.android.presentation.sustainability.AndroidSustainabilityViewModel
import com.darrenthiores.ecoswap.android.presentation.sustainability.SustainabilityScreen
import com.darrenthiores.ecoswap.android.utils.AppBottomBar
import com.darrenthiores.ecoswap.android.utils.AppState
import com.darrenthiores.ecoswap.android.utils.Route
import com.darrenthiores.ecoswap.android.utils.TopLevelDestination
import com.darrenthiores.ecoswap.android.utils.rememberAppState
import com.darrenthiores.ecoswap.domain.utils.UiEvent
import com.darrenthiores.ecoswap.presentation.sustainability.SustainabilityEvent

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
                    onStoreClick = { id ->
                        navController.navigate(Route.StoreProfile.name + "/$id")
                    },
                    onAddClick = {
                        navController.navigate(Route.AddItem.name)
                    }
                )
            }

            composable(TopLevelDestination.Sustainability.name) {
                val viewModel: AndroidSustainabilityViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                val shouldRefresh by it
                    .savedStateHandle
                    .getStateFlow("shouldRefresh", false)
                    .collectAsState()

                LaunchedEffect(shouldRefresh) {
                    if (shouldRefresh) {
                        viewModel.onEvent(
                            event = SustainabilityEvent.Refresh
                        )
                    }
                }

                SustainabilityScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onChallengeClick = { id ->
                        navController.navigate(Route.ChallengeDetail.name + "/$id")
                    },
                    onAddClick = {
                        navController.navigate(Route.AddProgress.name)
                    }
                )
            }

            composable(TopLevelDestination.Profile.name) {
                val viewModel: AndroidProfileViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                ProfileScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onItemClick = { id ->
                        navController.navigate(Route.ItemDetail.name + "/$id")
                    },
                    onUserClick = { id ->
                        navController.navigate(Route.OtherProfile.name + "/$id")
                    },
                    onBackClick = {
                        navController.navigateUp()
                    },
                    onSettingClick = {  }
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
                    onStoreClick = { id ->
                        navController.navigate(Route.StoreProfile.name + "/$id")
                    },
                    onBackClicked = {
                        navController.navigateUp()
                    }
                )
            }

            composable(TopLevelDestination.Message.name) {
                val viewModel: AndroidInboxViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                InboxScreen(
                    state = state,
                    onBackClick = {
                        navController.navigateUp()
                    },
                    onMessageClick = { sentToId ->
                        navController.navigate(Route.Message.name + "/$sentToId")
                    }
                )
            }

            composable(
                route = Route.Message.name + "/{userId}",
                arguments = listOf(
                    navArgument("userId") {
                        NavType.StringType
                    }
                )
            ) {
                val viewModel: AndroidMessageViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                MessageScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onSettingClick = {  },
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(
                route = Route.ChallengeDetail.name + "/{challengeId}",
                arguments = listOf(
                    navArgument("challengeId") {
                        NavType.StringType
                    }
                )
            ) {
                val viewModel: AndroidChallengeDetailViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                val shouldRefresh by it
                    .savedStateHandle
                    .getStateFlow("shouldRefresh", false)
                    .collectAsState()

                LaunchedEffect(shouldRefresh) {
                    if (shouldRefresh) {
                        navController
                            .previousBackStackEntry
                            ?.savedStateHandle
                            ?.set(
                                "shouldRefresh",
                                true
                            )
                    }
                }

                ChallengeDetailScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onAddClick = {
                        if (state.challenge != null) {
                            navController.navigate(Route.AddProgress.name + "?challengeId=${state.challenge?.id}")
                        } else {
                            navController.navigate(Route.AddProgress.name)
                        }
                    },
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(
                route = Route.AddProgress.name + "?challengeId={challengeId}",
                arguments = listOf(
                    navArgument("challengeId") {
                        NavType.StringType
                        nullable = true
                        defaultValue = null
                    }
                )
            ) {
                val viewModel: AndroidAddProgressViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                val message = stringResource(id = R.string.success_message)

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                navController
                                    .previousBackStackEntry
                                    ?.savedStateHandle
                                    ?.set(
                                        "shouldRefresh",
                                        true
                                    )

                                navController.navigateUp()
                                appState.showSnackBar(message)
                            }
                            else -> Unit
                        }
                    }
                }

                AddProgressScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onBackClick = {
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
                    onUserClick = {
                        val id = state.user?.id

                        id?.let {
                            navController.navigate(Route.OtherProfile.name + "/$id")
                        }
                    },
                    onMessageClick = {  },
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(
                route = Route.StoreItemDetail.name + "/{itemId}",
                arguments = listOf(
                    navArgument("itemId") {
                        NavType.StringType
                    }
                )
            ) {
                val viewModel: AndroidStoreItemDetailViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                StoreItemDetailScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onSettingClick = {  },
                    onStoreClick = {
                        val id = state.store?.id

                        id?.let {
                            navController.navigate(Route.StoreProfile.name + "/$id")
                        }
                    },
                    onMessageClick = {  },
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(
                route = Route.OtherProfile.name + "/{userId}",
                arguments = listOf(
                    navArgument("userId") {
                        NavType.StringType
                    }
                )
            ) {
                val viewModel: AndroidOtherProfileViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                OtherProfileScreen(
                    state = state,
                    uiEvent = viewModel.uiEvent,
                    onEvent = viewModel::onEvent,
                    onItemClick = { id ->
                        navController.navigate(Route.ItemDetail.name + "/$id")
                    },
                    onUserClick = { id ->
                        navController.navigate(Route.OtherProfile.name + "/$id")
                    },
                    onMessageClick = {  },
                    onSettingClick = {  },
                    showSnackBar = { message ->
                        appState.showSnackBar(message)
                    },
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(
                route = Route.StoreProfile.name + "/{storeId}",
                arguments = listOf(
                    navArgument("storeId") {
                        NavType.StringType
                    }
                )
            ) {
                val viewModel: AndroidStoreProfileViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                StoreProfileScreen(
                    state = state,
                    uiEvent = viewModel.uiEvent,
                    onEvent = viewModel::onEvent,
                    onItemClick = { id ->
                        navController.navigate(Route.StoreItemDetail.name + "/$id")
                    },
                    onUserClick = { id ->
                        navController.navigate(Route.OtherProfile.name + "/$id")
                    },
                    onMessageClick = {  },
                    onSettingClick = {  },
                    showSnackBar = { message ->
                        appState.showSnackBar(message)
                    },
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(Route.AddItem.name) {
                val viewModel: AndroidAddItemViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                val photos = viewModel.photos

                val message = stringResource(id = R.string.success_message)

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                navController.navigateUp()
                                appState.showSnackBar(message)
                            }
                            else -> Unit
                        }
                    }
                }

                AddItemScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    photos = photos,
                    onPickPhotos = {
                        viewModel.onSelectPhoto(it)
                    },
                    onSelectPhoto = {
                        viewModel.onSelectPhoto(it)
                    },
                    onAddPhoto = {
                        viewModel.onAddPhoto(it)
                    },
                    onUpload = {
                        viewModel.upload()
                    },
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}