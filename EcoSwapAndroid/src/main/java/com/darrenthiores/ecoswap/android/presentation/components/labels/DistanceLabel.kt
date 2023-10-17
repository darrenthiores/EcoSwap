package com.darrenthiores.ecoswap.android.presentation.components.labels

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.theme.Caption2B
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme

@Composable
fun DistanceLabel(
    modifier: Modifier = Modifier,
    distance: String
) {
    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colors.background,
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(
                horizontal = 10.dp,
                vertical = 4.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(10.dp),
            imageVector = Icons.Rounded.LocationOn,
            contentDescription = null,
            tint = MaterialTheme.colors.primary
        )

        Text(
            text = distance,
            style = Caption2B.copy(
                color = MaterialTheme.colors.primary
            )
        )
    }
}

@Preview
@Composable
private fun DistanceLabelPreview() {
    EcoSwapTheme {
        DistanceLabel(distance = "2,6 Km")
    }
}