package com.darrenthiores.ecoswap.android.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.theme.Caption1R
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.SubHeadlineB
import com.darrenthiores.ecoswap.domain.core.utils.Dummy
import com.darrenthiores.ecoswap.domain.user.model.User

@Composable
fun ProfileTopSection(
    modifier: Modifier = Modifier,
    user: User?
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            model = ImageRequest
                .Builder(context)
                .data(user?.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_no_picture),
            fallback = painterResource(id = R.drawable.ic_no_picture),
            error = painterResource(id = R.drawable.ic_no_picture),
            contentDescription = ""
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = user?.name ?: "null",
            style = SubHeadlineB
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(12.dp),
                imageVector = Icons.Rounded.Star,
                contentDescription = stringResource(id = R.string.rating),
                tint = MaterialTheme.colors.primary
            )

            Text(
                text = user?.rating.toString(),
                style = Caption1R.copy(
                    color = MaterialTheme.colors.primary
                )
            )

            Text(
                text = stringResource(id = R.string.total_reviews, (user?.totalReview ?: 0)),
                style = Caption1R
            )
        }
    }
}

@Preview
@Composable
fun ProfileTopSectionPreview() {
    EcoSwapTheme {
        ProfileTopSection(user = Dummy.user)
    }
}