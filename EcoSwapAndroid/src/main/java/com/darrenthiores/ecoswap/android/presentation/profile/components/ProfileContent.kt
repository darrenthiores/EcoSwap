package com.darrenthiores.ecoswap.android.presentation.profile.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.darrenthiores.ecoswap.android.presentation.model_components.item.ItemList
import com.darrenthiores.ecoswap.android.presentation.model_components.review.ReviewList
import com.darrenthiores.ecoswap.presentation.profile.model.ProfileTab
import com.darrenthiores.ecoswap.presentation.profile.ProfileState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    state: ProfileState,
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
                ProfileTopSection(
                    user = state.user
                )
            }

            item {
                Column(
                    modifier = Modifier.height(screenHeight)
                ) {
                    ProfileTabRow(
                        pagerState = pagerState
                    )

                    HorizontalPager(
                        pageCount = ProfileTab.values().size,
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
                                    state.reviews.isLoading -> {

                                    }
                                    state.reviews.items.isEmpty() -> {

                                    }
                                    else -> {
                                        ReviewList(
                                            items = state.reviews.items,
                                            onUserClick = { id ->
                                                onUserClick(id)
                                            },
                                            state = reviewListState
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
}