package com.darrenthiores.ecoswap.android.presentation.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.components.header.SettingHeader
import com.darrenthiores.ecoswap.android.presentation.profile.components.ProfileContent
import com.darrenthiores.ecoswap.presentation.profile.ProfileEvent
import com.darrenthiores.ecoswap.presentation.profile.ProfileState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
    state: ProfileState,
    onEvent: (ProfileEvent) -> Unit,
    onItemClick: (String) -> Unit,
    onUserClick: (String) -> Unit,
    onBackClick: () -> Unit,
    onSettingClick: () -> Unit
) {
    val pagerState = rememberPagerState()
    val listState = rememberLazyListState()
    val reviewListState = rememberLazyListState()
    val itemListState = rememberLazyGridState()

    val itemReachEnd = remember(state.items.items) {
        derivedStateOf {
            itemListState.layoutInfo
                .visibleItemsInfo
                .lastOrNull()?.index == state.items.items.size -1
        }
    }

    LaunchedEffect(itemReachEnd.value) {
        if(itemReachEnd.value && !state.items.endReached && !state.items.isLoading) {
            onEvent(ProfileEvent.LoadItemNextPage)
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
            onEvent(ProfileEvent.LoadReviewNextPage)
        }
    }

    Scaffold(
        topBar = {
            SettingHeader(
                modifier = Modifier
                    .padding(16.dp),
                text = stringResource(id = R.string.my_profile),
                onBackClick = onBackClick,
                onSettingClick = onSettingClick
            )
        }
    ) { paddingValues ->
        ProfileContent(
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