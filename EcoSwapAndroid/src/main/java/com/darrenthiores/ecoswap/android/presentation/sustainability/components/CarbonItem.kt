package com.darrenthiores.ecoswap.android.presentation.sustainability.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.utils.ProgressColor
import com.darrenthiores.ecoswap.android.theme.Caption1B
import com.darrenthiores.ecoswap.android.theme.Caption1R
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.SubHeadlineB
import com.darrenthiores.ecoswap.android.theme.White
import com.darrenthiores.ecoswap.domain.carbon.model.FootPrint
import com.darrenthiores.ecoswap.domain.core.utils.Constant

@Composable
fun CarbonItem(
    modifier: Modifier = Modifier,
    carbon: FootPrint.Carbon
) {
    val carbonCategory = Constant.carbonCategoryById(
        id = carbon.categoryId
    )
    val color = ProgressColor.getColorByCategoryId(
        categoryId = carbon.categoryId
    )?.color ?: ProgressColor.defaultColor

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Card(
            modifier = Modifier
                .size(12.dp),
            shape = CircleShape,
            elevation = 1.dp
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(
                        color = color,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = White,
                        shape = CircleShape
                    )
            )
        }

        Text(
            modifier = Modifier
                .weight(1f),
            text = carbonCategory?.display ?: "",
            style = SubHeadlineB
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = carbon.total.toString() + " ",
                style = Caption1B.copy(
                    color = MaterialTheme.colors.primary
                )
            )

            Text(
                text = stringResource(id = R.string.co2),
                style = Caption1R
            )
        }
    }
}

@Preview
@Composable
private fun CarbonItemPreview() {
    EcoSwapTheme {
        CarbonItem(
            carbon = FootPrint.Carbon(
                categoryId = "1",
                total = 100.0
            )
        )
    }
}