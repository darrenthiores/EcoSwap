package com.darrenthiores.ecoswap.android.presentation.components.header

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.darrenthiores.ecoswap.android.theme.CallOutB
import com.darrenthiores.ecoswap.android.theme.CallOutR
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme

@Composable
fun SectionHeader(
    modifier: Modifier = Modifier,
    title: String,
    endAction: String?,
    onActionClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = CallOutB
        )

        endAction?.let { action ->
            Text(
                modifier = Modifier
                    .clickable { onActionClick() },
                text = action,
                style = CallOutR.copy(
                    color = MaterialTheme.colors.primary
                )
            )
        }
    }
}

@Preview
@Composable
private fun SectionHeaderPreview() {
    EcoSwapTheme {
        SectionHeader(
            title = "Items Category",
            endAction = "View All"
        ) {

        }
    }
}