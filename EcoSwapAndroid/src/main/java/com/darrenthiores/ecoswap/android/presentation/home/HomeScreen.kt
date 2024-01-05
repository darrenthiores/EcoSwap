package com.darrenthiores.ecoswap.android.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.home.components.CategorySection
import com.darrenthiores.ecoswap.android.presentation.home.components.HomeHeader
import com.darrenthiores.ecoswap.android.presentation.home.components.ItemSection
import com.darrenthiores.ecoswap.android.presentation.home.components.StoreSection
import com.darrenthiores.ecoswap.presentation.home.HomeEvent
import com.darrenthiores.ecoswap.presentation.home.HomeState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    state: HomeState,
    searchText: String,
    onAndroidEvent: (AndroidHomeEvent) -> Unit,
    onEvent: (HomeEvent) -> Unit,
    onCategoryClick: (String) -> Unit,
    onSearch: () -> Unit,
    onItemClick: (String) -> Unit,
    onStoreClick: (String) -> Unit,
    onAddClick: () -> Unit
) {
    val categoryPagingState = rememberPagerState()
    val recommendationListState = rememberLazyListState()
    val nearbyListState = rememberLazyListState()
    val storeListState = rememberLazyListState()

    Scaffold(
        topBar = {
            HomeHeader(
                searchText = searchText,
                onTextChange = {
                    onAndroidEvent(AndroidHomeEvent.OnSearchChange(it))
                },
                onNotificationClick = { },
                onSearch = onSearch
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    modifier = Modifier
                        .size(32.dp),
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(id = R.string.add_item),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(
                vertical = 24.dp
            )
        ) {
            item {
                CategorySection(
                    pagerState = categoryPagingState,
                    viewAll = state.viewAll,
                    onItemClick = { category ->
                        onCategoryClick(category.id)
                    },
                    onToggleViewAll = {
                        onEvent(HomeEvent.ToggleViewAll)
                    }
                )
            }

            item {
                ItemSection(
                    title = stringResource(id = R.string.item_recommend),
                    items = state.recommendations,
                    state = recommendationListState,
                    onSeeAllClick = {  },
                    onItemClick = { item ->
                        onItemClick(item.id)
                    },
                    isLoading = state.isRecommendationLoading
                )
            }

            item {
                ItemSection(
                    title = stringResource(id = R.string.item_nearby),
                    items = state.nearby,
                    state = nearbyListState,
                    onSeeAllClick = {  },
                    onItemClick = { item ->
                        onItemClick(item.id)
                    },
                    isLoading = state.isNearbyLoading
                )
            }

            item {
                StoreSection(
                    title = stringResource(id = R.string.store_subtitle),
                    stores = state.stores,
                    state = storeListState,
                    onSeeAllClick = {  },
                    onStoreClick = { store ->
                        onStoreClick(store.id)
                    },
                    isLoading = state.isStoreLoading
                )
            }
        }
    }
}