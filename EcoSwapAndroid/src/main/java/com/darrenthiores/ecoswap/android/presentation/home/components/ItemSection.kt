package com.darrenthiores.ecoswap.android.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.components.header.SectionHeader
import com.darrenthiores.ecoswap.android.presentation.components.loading.HorizontalItemPlaceholder
import com.darrenthiores.ecoswap.android.presentation.model_components.item.HorizontalItemList
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.domain.item.model.Item

@Composable
fun ItemSection(
    modifier: Modifier = Modifier,
    title: String,
    items: List<Item>,
    state: LazyListState,
    onSeeAllClick: () -> Unit,
    onItemClick: (Item) -> Unit,
    isLoading: Boolean
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SectionHeader(
            title = title,
            endAction = stringResource(id = R.string.see_all),
            onActionClick = onSeeAllClick
        )

        if (isLoading) {
            HorizontalItemPlaceholder()
        } else {
            HorizontalItemList(
                items = items,
                onClick = onItemClick,
                state = state
            )
        }
    }
}

@Preview
@Composable
private fun ItemSectionPreview() {
    EcoSwapTheme {
        ItemSection(
            title = "Recommended for you",
            items = emptyList(),
            state = rememberLazyListState(),
            onSeeAllClick = { },
            onItemClick = {  },
            isLoading = true
        )
    }
}