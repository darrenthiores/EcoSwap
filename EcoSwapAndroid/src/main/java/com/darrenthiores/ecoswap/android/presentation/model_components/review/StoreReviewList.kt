package com.darrenthiores.ecoswap.android.presentation.model_components.review

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.domain.reviews.model.StoreReview

@Composable
fun StoreReviewList(
    modifier: Modifier = Modifier,
    items: List<StoreReview>,
    onUserClick: (String) -> Unit,
    state: LazyListState,
    padding: PaddingValues = PaddingValues(
        horizontal = 24.dp
    )
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = state,
        contentPadding = padding
    ) {
        items(
            items = items,
            key = { item -> item.id }
        ) { item ->
            StoreReviewItem(
                review = item,
                onUserClick = {
                    onUserClick(item.userId)
                }
            )
        }
    }
}