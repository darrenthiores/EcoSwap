package com.darrenthiores.ecoswap.android.presentation.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme

@Composable
fun HorizontalItemPlaceholder(
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(3) {
            Card(
                modifier = Modifier
                    .width(170.dp)
                    .height(200.dp),
                elevation = 1.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = 8.dp
                        ),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp)
                            .background(
                                brush = shimmerBrush()
                            )
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(fraction = 0.8f)
                                .height(15.dp)
                                .background(
                                    brush = shimmerBrush()
                                )
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth(fraction = 0.8f)
                                .height(15.dp)
                                .background(
                                    brush = shimmerBrush()
                                )
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(15.dp)
                                .background(
                                    brush = shimmerBrush()
                                )
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HorizontalItemPlaceholderPreview() {
    EcoSwapTheme {
        HorizontalItemPlaceholder()
    }
}