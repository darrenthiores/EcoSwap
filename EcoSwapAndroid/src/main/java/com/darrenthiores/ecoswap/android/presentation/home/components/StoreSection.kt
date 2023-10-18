package com.darrenthiores.ecoswap.android.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.components.header.SectionHeader
import com.darrenthiores.ecoswap.android.presentation.components.loading.HorizontalStorePlaceholder
import com.darrenthiores.ecoswap.android.presentation.model_components.store.HorizontalStoreList
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.domain.store.model.Store

@Composable
fun StoreSection(
    modifier: Modifier = Modifier,
    title: String,
    stores: List<Store>,
    state: LazyListState,
    onSeeAllClick: () -> Unit,
    onStoreClick: (Store) -> Unit,
    isLoading: Boolean
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SectionHeader(
            modifier = Modifier
                .padding(
                    horizontal = 24.dp
                ),
            title = title,
            endAction = stringResource(id = R.string.see_all),
            onActionClick = onSeeAllClick
        )

        if (isLoading) {
            HorizontalStorePlaceholder()
        } else {
            HorizontalStoreList(
                stores = stores,
                onClick = onStoreClick,
                state = state
            )
        }
    }
}

@Preview
@Composable
private fun StoreSectionPreview() {
    EcoSwapTheme {
        StoreSection(
            title = "Recommended for you",
            stores = emptyList(),
            state = rememberLazyListState(),
            onSeeAllClick = { },
            onStoreClick = {  },
            isLoading = true
        )
    }
}