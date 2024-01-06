package com.darrenthiores.ecoswap.android.presentation.other_profile.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.darrenthiores.ecoswap.presentation.other_profile.model.OtherProfileTab
import com.darrenthiores.ecoswap.android.theme.Caption1B
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OtherProfileTabRow(
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        modifier = modifier,
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.primary,
        divider = {  }
    ) {
        OtherProfileTab.values().forEachIndexed { index, tab ->
            Tab(
                modifier = Modifier,
                text = {
                    Text(
                        text = tab.name,
                        style = Caption1B
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.onBackground
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun OtherProfileTabRowPreview() {
    EcoSwapTheme {
        val pagerState = rememberPagerState(0)

        OtherProfileTabRow(pagerState = pagerState)
    }
}