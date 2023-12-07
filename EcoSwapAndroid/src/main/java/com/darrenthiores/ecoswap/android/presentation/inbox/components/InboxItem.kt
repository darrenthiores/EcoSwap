package com.darrenthiores.ecoswap.android.presentation.inbox.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.theme.Caption1B
import com.darrenthiores.ecoswap.android.theme.Caption1R
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.domain.core.utils.Dummy
import com.darrenthiores.ecoswap.domain.message.model.Inbox

@Composable
fun InboxItem(
    modifier: Modifier = Modifier,
    inbox: Inbox,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val message = inbox.lastMessage

    Row(
        modifier = modifier
            .clickable {
                onClick()
            }
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = modifier
                .size(30.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            model = ImageRequest
                .Builder(context)
                .data(inbox.sentToImageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_no_picture),
            error = painterResource(id = R.drawable.ic_no_picture),
            contentDescription = inbox.sentToUsername + " photo"
        )

        Spacer(modifier = Modifier.width(32.dp))

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.username, inbox.sentToUsername),
                style = Caption1B.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )

            Text(
                text = message,
                style = Caption1R.copy(
                    color = Color.Gray
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
private fun InboxItemPreview() {
    EcoSwapTheme {
        InboxItem(
            inbox = Dummy.inboxes[0],
            onClick = {  }
        )
    }
}