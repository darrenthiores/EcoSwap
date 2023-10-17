package com.darrenthiores.ecoswap.android.presentation.components.indicator

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    pageCount: Int
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration)
                MaterialTheme.colors.primary else MaterialTheme.colors.primary.copy(
                    alpha = 0.2f
                )

            Box(
                modifier = Modifier
                    .size(6.dp)
                    .background(
                        color = color,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun PageIndicatorPreview() {
    EcoSwapTheme {
        val pagerState = rememberPagerState()

        PageIndicator(
            pagerState = pagerState,
            pageCount = 4
        )
    }
}