package com.darrenthiores.ecoswap.android.presentation.message.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.HeadlineB
import com.darrenthiores.ecoswap.android.theme.White

@Composable
fun MessageHeader(
    modifier: Modifier = Modifier,
    text: String,
    onBackClick: () -> Unit,
    onSettingClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MessageCircleButton(
            icon = Icons.Rounded.ArrowBack
        ) {
            onBackClick()
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Text(
            modifier = Modifier
                .weight(1f),
            text = text,
            style = HeadlineB.copy(
                color = MaterialTheme.colors.onPrimary
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.width(16.dp))

        MessageCircleButton(
            icon = Icons.Rounded.MoreHoriz
        ) {
            onSettingClick()
        }
    }
}

@Composable
private fun MessageCircleButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(34.dp)
            .clip(CircleShape)
            .background(
                color = White.copy(
                    alpha = 0.2f
                ),
                shape = CircleShape
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp),
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colors.onPrimary
        )
    }
}

@Preview
@Composable
private fun MessageHeaderPreview() {
    EcoSwapTheme {
        MessageHeader(
            text = "My Profile",
            onBackClick = {  }
        ) {

        }
    }
}

@Preview
@Composable
private fun CircleButtonPreview() {
    EcoSwapTheme {
        MessageCircleButton(
            icon = Icons.Rounded.ArrowBack
        ) {

        }
    }
}