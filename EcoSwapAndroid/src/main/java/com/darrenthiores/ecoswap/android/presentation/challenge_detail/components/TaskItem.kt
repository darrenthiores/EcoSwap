package com.darrenthiores.ecoswap.android.presentation.challenge_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.FootnoteR
import com.darrenthiores.ecoswap.domain.carbon.model.Challenge
import com.darrenthiores.ecoswap.domain.core.utils.Dummy

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    task: Challenge.Task
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 2.dp
            ),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            modifier = Modifier
                .padding(
                    top = 6.dp
                )
                .size(4.dp),
            imageVector = Icons.Rounded.Circle,
            contentDescription = null
        )

        Text(
            text = task.task,
            style = FootnoteR
        )
    }
}

@Preview
@Composable
private fun TaskItemPreview() {
    EcoSwapTheme {
        TaskItem(
            task = Dummy.challenges[0].tasks[0]
        )
    }
}