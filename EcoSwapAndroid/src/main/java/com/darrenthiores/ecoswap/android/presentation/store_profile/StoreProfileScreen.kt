package com.darrenthiores.ecoswap.android.presentation.store_profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.components.header.SettingHeader
import com.darrenthiores.ecoswap.android.presentation.store_profile.components.ReviewStoreSheet
import com.darrenthiores.ecoswap.android.presentation.store_profile.components.StoreProfileBottomBar
import com.darrenthiores.ecoswap.android.presentation.store_profile.components.StoreProfileContent
import com.darrenthiores.ecoswap.domain.utils.UiEvent
import com.darrenthiores.ecoswap.presentation.store_profile.StoreProfileEvent
import com.darrenthiores.ecoswap.presentation.store_profile.StoreProfileState
import com.darrenthiores.ecoswap.utils.flow.CommonFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun StoreProfileScreen(
    state: StoreProfileState,
    uiEvent: CommonFlow<UiEvent>,
    onEvent: (StoreProfileEvent) -> Unit,
    onItemClick: (String) -> Unit,
    onUserClick: (String) -> Unit,
    onMessageClick: () -> Unit,
    onSettingClick: () -> Unit,
    showSnackBar: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val listState = rememberLazyListState()
    val reviewListState = rememberLazyListState()
    val itemListState = rememberLazyGridState()

    LaunchedEffect(key1 = true) {
        uiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> {
                    coroutineScope.launch {
                        sheetState.collapse()
                    }
                }
                is UiEvent.ShowSnackBar -> {
                    showSnackBar(event.message)
                }
                else -> Unit
            }
        }
    }

    val itemReachEnd = remember(state.items.items) {
        derivedStateOf {
            itemListState.layoutInfo
                .visibleItemsInfo
                .lastOrNull()?.index == state.items.items.size -1
        }
    }

    LaunchedEffect(itemReachEnd.value) {
        if(itemReachEnd.value && !state.items.endReached && !state.items.isLoading) {
            onEvent(StoreProfileEvent.LoadItemNextPage)
        }
    }

    val storeReachEnd = remember(state.reviews.items) {
        derivedStateOf {
            reviewListState.layoutInfo
                .visibleItemsInfo
                .lastOrNull()?.index == state.reviews.items.size -1
        }
    }

    LaunchedEffect(storeReachEnd.value) {
        if(storeReachEnd.value && !state.reviews.endReached && !state.reviews.isLoading) {
            onEvent(StoreProfileEvent.LoadReviewNextPage)
        }
    }

    BottomSheetScaffold(
        sheetContent = {
            ReviewStoreSheet(
                rating = state.rating,
                message = state.message,
                isLoading = state.isLoading,
                onPickRating = { rating ->
                    onEvent(StoreProfileEvent.OnPickRating(rating))
                },
                onMessageChange = { text ->
                    onEvent(StoreProfileEvent.OnMessageChange(text))
                },
                onUpload = {
                    onEvent(StoreProfileEvent.AddReview)
                }
            )
        },
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetBackgroundColor = MaterialTheme.colors.surface,
        sheetPeekHeight = 0.dp
    ) {
        Scaffold(
            topBar = {
                SettingHeader(
                    modifier = Modifier
                        .padding(16.dp),
                    text = stringResource(id = R.string.store),
                    onBackClick = onBackClick,
                    onSettingClick = onSettingClick
                )
            },
            bottomBar = {
                StoreProfileBottomBar(
                    onMessageClick = onMessageClick,
                    onAddReviewClick = {
                        coroutineScope.launch {
                            sheetState.expand()
                        }
                    }
                )
            }
        ) { paddingValues ->
            StoreProfileContent(
                modifier = Modifier
                    .padding(paddingValues),
                state = state,
                listState = listState,
                reviewListState = reviewListState,
                itemListState = itemListState,
                pagerState = pagerState,
                onItemClick = onItemClick,
                onUserClick = onUserClick
            )
        }
    }
}