package com.darrenthiores.ecoswap.android.presentation.other_profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Message
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.components.buttons.ButtonType
import com.darrenthiores.ecoswap.android.presentation.components.buttons.DefaultButton
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme

@Composable
fun OtherProfileBottomBar(
    modifier: Modifier = Modifier,
    onMessageClick: () -> Unit,
    onAddReviewClick: () -> Unit
) {
    BottomAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.primary,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DefaultButton(
                modifier = Modifier
                    .weight(1f),
                label = stringResource(id = R.string.message),
                onClick = onMessageClick,
                startIcon = Icons.Rounded.Message,
                type = ButtonType.Outline()
            )

            DefaultButton(
                modifier = Modifier
                    .weight(1f),
                label = stringResource(id = R.string.add_review),
                onClick = onAddReviewClick,
                startIcon = Icons.Rounded.Edit
            )
        }
    }
}

@Preview
@Composable
private fun OtherProfileBottomBarPreview() {
    EcoSwapTheme {
        OtherProfileBottomBar(
            onMessageClick = {  },
            onAddReviewClick = {  }
        )
    }
}