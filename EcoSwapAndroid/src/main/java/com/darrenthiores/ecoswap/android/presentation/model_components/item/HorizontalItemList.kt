package com.darrenthiores.ecoswap.android.presentation.model_components.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.domain.item.model.Item

@Composable
fun HorizontalItemList(
    modifier: Modifier = Modifier,
    items: List<Item>,
    onClick: (Item) -> Unit,
    state: LazyListState,
    padding: PaddingValues = PaddingValues(
        horizontal = 24.dp
    )
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        state = state,
        contentPadding = padding,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            items = items,
            key = { item -> item.id }
        ) { item ->
            ItemCard(
                item = item,
                onClick = {
                    onClick(item)
                }
            )
        }
    }
}