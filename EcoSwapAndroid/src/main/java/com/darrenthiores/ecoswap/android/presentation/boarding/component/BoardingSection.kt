package com.darrenthiores.ecoswap.android.presentation.boarding.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.components.buttons.ButtonType
import com.darrenthiores.ecoswap.android.presentation.components.buttons.DefaultButton
import com.darrenthiores.ecoswap.android.theme.Black
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.Gray2
import com.darrenthiores.ecoswap.android.theme.Title1B
import com.darrenthiores.ecoswap.android.theme.White

@Composable
fun BoardingSection(
    modifier: Modifier = Modifier,
    page: Int,
    @DrawableRes imageId: Int,
    title: String,
    description: String,
    onNext: () -> Unit,
    onStart: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .align(Alignment.Center),
            painter = painterResource(id = imageId),
            contentDescription = stringResource(id = R.string.boarding_cd, page),
            contentScale = ContentScale.Fit
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colors.background
                        ),
                        startY = 0f,
                        endY = 200f
                    )
                )
                .padding(horizontal = 24.dp)
                .padding(vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = Title1B,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.subtitle2,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(32.dp))

            PageTab(page = page)

            Spacer(modifier = Modifier.height(16.dp))
            
            if (page == 3) {
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = stringResource(id = R.string.start),
                    onClick = onStart
                )
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    DefaultButton(
                        modifier = Modifier
                            .weight(1f),
                        label = stringResource(id = R.string.skip),
                        onClick = onStart,
                        type = ButtonType.Outline()
                    )

                    DefaultButton(
                        modifier = Modifier
                            .weight(1f),
                        label = stringResource(id = R.string.next),
                        onClick = onNext
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BoardingSectionPreview() {
    EcoSwapTheme {
        Surface {
            BoardingSection(
                page = 1,
                imageId = R.drawable.boarding_1,
                title = stringResource(id = R.string.boarding_title_1),
                description = stringResource(id = R.string.dummy_text),
                onNext = {  },
                onStart = {  }
            )
        }
    }
}

@Composable
private fun PageTab(
    modifier: Modifier = Modifier,
    page: Int
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                Black.copy(
                    alpha = 0.2f
                )
            )
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        (1..3).forEach { i ->
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(
                        if (i == page) White else Gray2
                    )
            )
        }
    }
}

@Preview
@Composable
private fun PageTabPreview() {
    EcoSwapTheme {
        PageTab(page = 1)
    }
}