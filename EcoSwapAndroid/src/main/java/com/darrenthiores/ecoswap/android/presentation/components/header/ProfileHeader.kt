package com.darrenthiores.ecoswap.android.presentation.components.header

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreHoriz
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
fun ProfileHeader(
    modifier: Modifier = Modifier,
    text: String,
    onBackClick: () -> Unit,
    onSettingClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleButton(
            icon = Icons.Rounded.ArrowBack
        ) {
            onBackClick()
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Text(
            modifier = Modifier
                .weight(1f),
            text = text,
            style = HeadlineB,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.width(16.dp))

        CircleButton(
            icon = Icons.Rounded.MoreHoriz
        ) {
            onSettingClick()
        }
    }
}

@Preview
@Composable
private fun ProfileHeaderPreview() {
    EcoSwapTheme {
        ProfileHeader(
            text = "My Profile",
            onBackClick = {  }
        ) {

        }
    }
}