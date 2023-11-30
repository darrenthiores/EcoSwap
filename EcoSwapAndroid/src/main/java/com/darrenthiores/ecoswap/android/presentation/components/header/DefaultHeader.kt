package com.darrenthiores.ecoswap.android.presentation.components.header

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.presentation.components.buttons.CircleButton
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.HeadlineB

@Composable
fun DefaultHeader(
    modifier: Modifier = Modifier,
    text: String,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        CircleButton(
            modifier = Modifier
                .align(Alignment.CenterStart),
            icon = Icons.Rounded.ArrowBack
        ) {
            onBackClick()
        }

        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(
                    horizontal = 32.dp
                ),
            text = text,
            style = HeadlineB,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun DefaultHeaderPreview() {
    EcoSwapTheme {
        DefaultHeader(
            text = "My Profile",
            onBackClick = {  }
        )
    }
}