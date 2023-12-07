package com.darrenthiores.ecoswap.android.presentation.message.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme

@Composable
fun MessageBottomBar(
    modifier: Modifier = Modifier,
    message: String,
    onMessageChange: (String) -> Unit,
    onAddPhotoClick: () -> Unit,
    onSendClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        MessageTextField(
            modifier = Modifier
                .weight(1f),
            text = message,
            onTextChange = onMessageChange
        )

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(
                    CircleShape
                )
                .background(
                    color = MaterialTheme.colors.onPrimary,
                    shape = CircleShape
                )
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(22.dp)
                    .clickable {
                        onAddPhotoClick()
                    },
                imageVector = Icons.Rounded.Image,
                contentDescription = stringResource(id = R.string.photo_button_desc),
                tint = MaterialTheme.colors.primary
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(
                    CircleShape
                )
                .background(
                    color = MaterialTheme.colors.primary,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(22.dp)
                    .clickable {
                        onSendClick()
                    }
                    .rotate(315f),
                imageVector = Icons.Rounded.Send,
                contentDescription = stringResource(id = R.string.message_button_desc),
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }
}

@Preview
@Composable
private fun MessageBottomBarPreview() {
    EcoSwapTheme {
        MessageBottomBar(
            message = "",
            onAddPhotoClick = {  },
            onMessageChange = {  }
        ) {

        }
    }
}