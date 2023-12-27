package com.darrenthiores.ecoswap.android.presentation.challenge_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Stars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.utils.ProgressColor
import com.darrenthiores.ecoswap.android.theme.CallOutR
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.FootnoteB
import com.darrenthiores.ecoswap.android.theme.FootnoteR
import com.darrenthiores.ecoswap.android.theme.SubHeadlineB

@Composable
fun AccountLeaderboard(
    modifier: Modifier = Modifier,
    name: String,
    imageUrl: String,
    reduction: Double,
    index: Int
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 8.dp
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = (index + 1).toString(),
                style = SubHeadlineB
            )

            Spacer(modifier = Modifier.width(0.dp))

            AsyncImage(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                model = ImageRequest
                    .Builder(context)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.ic_no_picture),
                error = painterResource(id = R.drawable.ic_no_picture),
                contentDescription = stringResource(id = R.string.photo_desc, name)
            )

            Text(
                modifier = Modifier
                    .weight(1f),
                text = name,
                style = CallOutR,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$reduction ",
                    style = FootnoteB.copy(
                        color = MaterialTheme.colors.primary
                    )
                )

                Text(
                    text = stringResource(id = R.string.co2),
                    style = FootnoteR
                )
            }

            if (index in 0..2) {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    imageVector = Icons.Rounded.Stars,
                    contentDescription = stringResource(id = R.string.top_leaderboards, index + 1),
                    tint = ProgressColor.getColorByIndex(
                        index = index
                    )?.color ?: ProgressColor.defaultColor
                )
            }
        }

        Divider()
    }
}

@Preview
@Composable
private fun AccountLeaderboardPreview() {
    EcoSwapTheme {
        AccountLeaderboard(
            name = "Darren",
            imageUrl = "",
            reduction = 100.0,
            index = 0
        )
    }
}