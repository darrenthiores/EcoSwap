package com.darrenthiores.ecoswap.android.presentation.store_profile.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.presentation.model_components.item.StoreItemList
import com.darrenthiores.ecoswap.android.presentation.model_components.review.StoreReviewList
import com.darrenthiores.ecoswap.android.presentation.store_profile.model.StoreProfileTab
import com.darrenthiores.ecoswap.android.theme.SubHeadlineR
import com.darrenthiores.ecoswap.presentation.store_profile.StoreProfileState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoreProfileContent(
    modifier: Modifier = Modifier,
    state: StoreProfileState,
    listState: LazyListState,
    reviewListState: LazyListState,
    itemListState: LazyGridState,
    pagerState: PagerState,
    onItemClick: (String) -> Unit,
    onUserClick: (String) -> Unit
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
    ) {
        val screenHeight = maxHeight

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState
        ) {
            item {
                StoreProfileTopSection(
                    store = state.store
                )
            }

            item {
                Column(
                    modifier = Modifier.height(screenHeight)
                ) {
                    StoreProfileTabRow(
                        pagerState = pagerState
                    )

                    HorizontalPager(
                        pageCount = StoreProfileTab.values().size,
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .nestedScroll(
                                remember {
                                    object : NestedScrollConnection {
                                        override fun onPreScroll(
                                            available: Offset,
                                            source: NestedScrollSource
                                        ): Offset {
                                            return if (available.y > 0) Offset.Zero else Offset(
                                                x = 0f,
                                                y = -listState.dispatchRawDelta(-available.y)
                                            )
                                        }
                                    }
                                }
                            )
                    ) { page ->
                        when (page) {
                            0 -> {
                                when {
                                    state.items.isLoading -> {

                                    }
                                    state.items.items.isEmpty() -> {

                                    }
                                    else -> {
                                        StoreItemList(
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
                                    state.reviews.isLoading -> {

                                    }
                                    state.reviews.items.isEmpty() -> {

                                    }
                                    else -> {
                                        StoreReviewList(
                                            items = state.reviews.items,
                                            onUserClick = { id ->
                                                onUserClick(id)
                                            },
                                            state = reviewListState
                                        )
                                    }
                                }
                            }
                            2 -> {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(64.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "This feature is not yet available :(",
                                        style = SubHeadlineR.copy(
                                            textAlign = TextAlign.Center
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}