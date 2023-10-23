package com.darrenthiores.ecoswap.android.presentation.model_components.store

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.domain.store.model.Store

@Composable
fun StoreList(
    modifier: Modifier = Modifier,
    items: List<Store>,
    onClick: (Store) -> Unit,
    state: LazyGridState,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = 24.dp,
        vertical = 16.dp
    )
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = contentPadding,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        state = state
    ) {
        items(
            items = items,
            key = { item -> item.id }
        ) { item ->
            FixedStoreCard(
                store = item,
                onClick = {
                    onClick(item)
                }
            )
        }
    }
}