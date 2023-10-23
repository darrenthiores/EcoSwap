package com.darrenthiores.ecoswap.android.presentation.search.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.components.tags.DefaultTag
import com.darrenthiores.ecoswap.android.presentation.components.textfields.DefaultTextField
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.domain.core.utils.Constant
import com.darrenthiores.ecoswap.domain.item.model.ItemCategory

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchHeader(
    modifier: Modifier = Modifier,
    searchText: String,
    selectedCategory: ItemCategory?,
    pagerState: PagerState,
    onTextChange: (String) -> Unit,
    onSearch: () -> Unit,
    onSelectCategory: (ItemCategory?) -> Unit,
    onBackClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Spacer(modifier = Modifier)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .then(
                        Modifier
                            .size(24.dp)
                    ),
                onClick = onBackClicked
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(id = R.string.back)
                )
            }

            DefaultTextField(
                text = searchText,
                onTextChange = onTextChange,
                placeholder = stringResource(id = R.string.search_placeholder),
                startIcon = Icons.Rounded.Search,
                onSubmit = {
                    onSearch()
                }
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(
                horizontal = 24.dp
            )
        ) {
            item {
                DefaultTag(
                    label = stringResource(id = R.string.all_items),
                    onClick = {
                        onSelectCategory(null)
                    },
                    selected = selectedCategory == null
                )
            }

            items(
                items = Constant.categories,
                key = { category -> category.id }
            ) { category ->
                DefaultTag(
                    label = category.display,
                    onClick = {
                        onSelectCategory(category)
                    },
                    selected = selectedCategory?.id == category.id
                )
            }
        }

        SearchTabRow(pagerState = pagerState)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(
    showBackground = true
)
@Composable
private fun HomeHeaderPreview() {
    EcoSwapTheme(
        darkTheme = false
    ) {
        SearchHeader(
            searchText = "",
            selectedCategory = null,
            pagerState = rememberPagerState(),
            onTextChange = {},
            onSearch = {},
            onSelectCategory = {},
            onBackClicked = {}
        )
    }
}