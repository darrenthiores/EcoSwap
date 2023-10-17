package com.darrenthiores.ecoswap.android.presentation.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.components.header.SectionHeader
import com.darrenthiores.ecoswap.android.presentation.components.indicator.PageIndicator
import com.darrenthiores.ecoswap.android.presentation.utils.getIcon
import com.darrenthiores.ecoswap.android.theme.Caption1R
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.domain.core.utils.Constant
import com.darrenthiores.ecoswap.domain.item.model.ItemCategory

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun CategorySection(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    viewAll: Boolean = true,
    onItemClick: (ItemCategory) -> Unit,
    onToggleViewAll: () -> Unit
) {
    val pageCount = Constant.categories.size.div(4)

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionHeader(
            title = stringResource(id = R.string.item_category),
            endAction = if (viewAll) stringResource(id = R.string.view_less) else stringResource(id = R.string.view_all),
            onActionClick = onToggleViewAll
        )

        if (!viewAll) {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth(),
                state = pagerState,
                pageCount = pageCount
            ) { page ->
                val index = page + 1
                val lastIndex = (index * 4) - 1
                val firstIndexCount = (lastIndex - 4)
                val firstIndex = if (firstIndexCount < 0) 0 else firstIndexCount
                val categories = Constant.categories.slice(
                    firstIndex..lastIndex
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    categories.forEach { category ->
                        CategoryItem(
                            modifier = Modifier
                                .clickable { onItemClick(category) },
                            category = category
                        )
                    }
                }
            }

            PageIndicator(
                pagerState = pagerState,
                pageCount = pageCount
            )
        } else {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth(),
                maxItemsInEachRow = 4,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Constant.categories.forEach { category ->
                    CategoryItem(
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .clickable { onItemClick(category) },
                        category = category
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun CategorySectionPreview() {
    EcoSwapTheme {
        val pagerState = rememberPagerState()

        CategorySection(
            pagerState = pagerState,
            onItemClick = {  }
        ) {
            
        }
    }
}

@Composable
private fun CategoryItem(
    modifier: Modifier = Modifier,
    category: ItemCategory
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(
                    color = MaterialTheme.colors.primary.copy(
                        alpha = 0.3f
                    ),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .width(36.dp),
                painter = painterResource(id = category.getIcon()),
                contentDescription = category.display,
                contentScale = ContentScale.FillWidth
            )
        }

        Text(
            text = category.display,
            style = Caption1R,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun CategoryItemPreview() {
    EcoSwapTheme {
        CategoryItem(
            category = Constant.categories[0]
        )
    }
}