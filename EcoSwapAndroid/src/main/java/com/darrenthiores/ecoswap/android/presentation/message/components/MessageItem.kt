package com.darrenthiores.ecoswap.android.presentation.message.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.theme.Black
import com.darrenthiores.ecoswap.android.theme.Caption1B
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.domain.core.utils.Dummy

@Composable
fun PersonalMessageItem(
    modifier: Modifier = Modifier,
    text: String,
    username: String,
    userImgUrl: String
) {
    val context = LocalContext.current
    val width = (LocalConfiguration.current.screenWidthDp * 0.75)

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier
                .width(width.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.End
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.username, username),
                    style = Caption1B.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                AsyncImage(
                    modifier = modifier
                        .size(30.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    model = ImageRequest
                        .Builder(context)
                        .data(userImgUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.ic_no_picture),
                    error = painterResource(id = R.drawable.ic_no_picture),
                    contentDescription = stringResource(id = R.string.photo_desc, username)
                )
            }

            Text(
                modifier = Modifier
                    .padding(
                        end = 38.dp
                    )
                    .widthIn(
                        min = 0.dp,
                        max = width.dp
                    )
                    .background(
                        color = Color(0xFFF1E8B8),
                        shape = RoundedCornerShape(
                            topStart = 8.dp,
                            bottomStart = 8.dp,
                            bottomEnd = 8.dp
                        )
                    )
                    .padding(8.dp),
                text = text,
                style = MaterialTheme.typography.caption.copy(
                    color = Black
                )
            )
        }
    }
}

@Preview
@Composable
private fun PersonalMessageItemPreview() {
    EcoSwapTheme {
        PersonalMessageItem(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sodales rhoncus enim in lobortis. Sed ex est, ultricies vel tempor vel, sagittis eu massa. Ut placerat, turpis vel imperdiet aliquet, lacus lectus aliquam odio, ut placerat nibh nibh eu ex. Phasellus gravida elit et mattis posuere. Cras ut ornare turpis. In nec tristique mauris, id rhoncus turpis. Duis neque odio, porta vitae felis eget, pharetra fringilla nisl. Praesent congue lacus erat, eu pharetra nulla volutpat a",
            username = Dummy.user.name,
            userImgUrl = Dummy.user.imageUrl
        )
    }
}

@Composable
fun FriendMessageItem(
    modifier: Modifier = Modifier,
    text: String,
    username: String,
    userImgUrl: String
) {
    val context = LocalContext.current
    val width = (LocalConfiguration.current.screenWidthDp * 0.75)

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .width(width.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = modifier
                        .size(30.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    model = ImageRequest
                        .Builder(context)
                        .data(userImgUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.ic_no_picture),
                    error = painterResource(id = R.drawable.ic_no_picture),
                    contentDescription = stringResource(id = R.string.photo_desc, username)
                )

                Text(
                    text = stringResource(id = R.string.username, userImgUrl),
                    style = Caption1B.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            Text(
                modifier = Modifier
                    .padding(
                        start = 38.dp
                    )
                    .widthIn(
                        min = 0.dp,
                        max = width.dp
                    )
                    .background(
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(
                            topEnd = 8.dp,
                            bottomStart = 8.dp,
                            bottomEnd = 8.dp
                        )
                    )
                    .padding(8.dp),
                text = text,
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onPrimary
                )
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
private fun FriendMessageItemPreview() {
    EcoSwapTheme {
        FriendMessageItem(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sodales rhoncus enim in lobortis. Sed ex est, ultricies vel tempor vel, sagittis eu massa. Ut placerat, turpis vel imperdiet aliquet, lacus lectus aliquam odio, ut placerat nibh nibh eu ex. Phasellus gravida elit et mattis posuere. Cras ut ornare turpis. In nec tristique mauris, id rhoncus turpis. Duis neque odio, porta vitae felis eget, pharetra fringilla nisl. Praesent congue lacus erat, eu pharetra nulla volutpat a",
            username = Dummy.user.name,
            userImgUrl = Dummy.user.imageUrl
        )
    }
}