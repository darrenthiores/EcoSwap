package com.darrenthiores.ecoswap.android.presentation.item_detail.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.components.buttons.CircleButton
import com.darrenthiores.ecoswap.android.theme.Black
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.FootnoteB
import com.darrenthiores.ecoswap.android.theme.White

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemDetailTopSection(
    modifier: Modifier = Modifier,
    images: List<String>,
    pagerState: PagerState,
    onSettingClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp * 0.8

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        HorizontalPager(
            pageCount = images.size,
            state = pagerState
        ) { index ->
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenWidth.dp),
                contentScale = ContentScale.Crop,
                model = ImageRequest
                    .Builder(context)
                    .data(images[index])
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.ic_no_picture),
                error = painterResource(id = R.drawable.ic_no_picture),
                contentDescription = null
            )
        }

        CircleButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    horizontal = 24.dp,
                    vertical = 16.dp
                ),
            icon = Icons.Rounded.ArrowBack,
            onClick = onBackClick
        )

        CircleButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    horizontal = 24.dp,
                    vertical = 16.dp
                ),
            icon = Icons.Rounded.MoreHoriz,
            onClick = onSettingClick
        )

        val currentPage = pagerState.currentPage + 1

        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    horizontal = 24.dp,
                    vertical = 16.dp
                )
                .background(
                    color = Black.copy(
                        alpha = 0.5f
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                ),
            text = "$currentPage/${images.size}",
            style = FootnoteB.copy(
                color = White
            )
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun ItemDetailTopSection() {
    EcoSwapTheme {
        ItemDetailTopSection(
            images = listOf(
                "",
                ""
            ),
            pagerState = rememberPagerState(),
            onSettingClick = {  },
            onBackClick = {  }
        )
    }
}