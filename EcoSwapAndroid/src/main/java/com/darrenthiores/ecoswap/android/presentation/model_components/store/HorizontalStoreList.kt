package com.darrenthiores.ecoswap.android.presentation.model_components.store

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.domain.store.model.Store

@Composable
fun HorizontalStoreList(
    modifier: Modifier = Modifier,
    stores: List<Store>,
    onClick: (Store) -> Unit,
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
            items = stores,
            key = { store -> store.id }
        ) { store ->
            StoreCard(
                store = store,
                onClick = {
                    onClick(store)
                }
            )
        }
    }
}