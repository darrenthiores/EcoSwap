package com.darrenthiores.ecoswap.android.presentation.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.darrenthiores.ecoswap.android.presentation.model_components.item.ItemList
import com.darrenthiores.ecoswap.android.presentation.model_components.store.StoreList
import com.darrenthiores.ecoswap.android.presentation.search.components.SearchHeader
import com.darrenthiores.ecoswap.presentation.search.SearchEvent
import com.darrenthiores.ecoswap.presentation.search.SearchState
import com.darrenthiores.ecoswap.presentation.search.model.SearchTab

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    state: SearchState,
    searchText: String,
    onEvent: (SearchEvent) -> Unit,
    onAndroidEvent: (AndroidSearchEvent) -> Unit,
    onItemClick: (String) -> Unit,
    onStoreClick: (String) -> Unit,
    onBackClicked: () -> Unit
) {
    val pagerState = rememberPagerState()
    val itemListState = rememberLazyGridState()
    val storeListState = rememberLazyGridState()

    val itemReachEnd = remember(state.items.items) {
        derivedStateOf {
            itemListState.layoutInfo
                .visibleItemsInfo
                .lastOrNull()?.index == state.items.items.size -1
        }
    }

    LaunchedEffect(itemReachEnd.value) {
        if(itemReachEnd.value && !state.items.endReached && !state.items.isLoading) {
            onEvent(SearchEvent.LoadItemNextPage)
        }
    }

    val storeReachEnd = remember(state.stores.items) {
        derivedStateOf {
            storeListState.layoutInfo
                .visibleItemsInfo
                .lastOrNull()?.index == state.stores.items.size -1
        }
    }

    LaunchedEffect(storeReachEnd.value) {
        if(storeReachEnd.value && !state.stores.endReached && !state.stores.isLoading) {
            onEvent(SearchEvent.LoadStoreNextPage)
        }
    }

    Scaffold(
        topBar = {
            SearchHeader(
                searchText = searchText,
                selectedCategory = state.category,
                pagerState = pagerState,
                onTextChange = {
                    onAndroidEvent(AndroidSearchEvent.OnSearchChange(it))
                },
                onSearch = {
                    onAndroidEvent(AndroidSearchEvent.OnSearch)
                },
                onSelectCategory = {
                    onAndroidEvent(AndroidSearchEvent.OnSelectCategory(it))
                },
                onBackClicked = onBackClicked
            )
        }
    ) { contentPadding ->
        HorizontalPager(
            modifier = Modifier
                .padding(contentPadding),
            pageCount = SearchTab.values().size,
            state = pagerState
        ) { index ->
            when (index) {
                0 -> {
                    when {
                        state.items.isLoading -> {

                        }
                        state.items.items.isEmpty() -> {

                        }
                        else -> {
                            ItemList(
                                items = state.items.items,
                                onClick = {
                                    onItemClick(it.id)
                                },
                                state = itemListState
                            )
                        }
                    }
                }
                1 -> {
                    when {
                        state.stores.isLoading -> {

                        }

                        state.stores.items.isEmpty() -> {

                        }

                        else -> {
                            StoreList(
                                items = state.stores.items,
                                onClick = {
                                    onStoreClick(it.id)
                                },
                                state = storeListState
                            )
                        }
                    }
                }
            }
        }
    }
}