package com.darrenthiores.ecoswap.android.presentation.model_components.review

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
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
import com.darrenthiores.ecoswap.android.theme.Caption1B
import com.darrenthiores.ecoswap.android.theme.Caption2R
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.FootnoteR
import com.darrenthiores.ecoswap.android.theme.SubHeadlineB
import com.darrenthiores.ecoswap.domain.core.utils.Dummy
import com.darrenthiores.ecoswap.domain.reviews.model.StoreReview
import com.darrenthiores.ecoswap.utils.date.DateUtils

@Composable
fun StoreReviewItem(
    modifier: Modifier = Modifier,
    review: StoreReview,
    onUserClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        StoreReviewUserItem(
            review = review,
            onUserClick = onUserClick
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = review.review,
            style = FootnoteR
        )

        Spacer(modifier = Modifier.height(16.dp))

        Divider()
    }
}

@Preview
@Composable
private fun StoreReviewItemPreview() {
    EcoSwapTheme {
        StoreReviewItem(
            review = Dummy.storeReviews[0]
        ) {
            
        }
    }
}

@Composable
private fun StoreReviewUserItem(
    modifier: Modifier = Modifier,
    review: StoreReview,
    onUserClick: () -> Unit
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .clickable {
                    onUserClick()
                },
            contentScale = ContentScale.Crop,
            model = ImageRequest
                .Builder(context)
                .data(review.userImgUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_no_picture),
            fallback = painterResource(id = R.drawable.ic_no_picture),
            error = painterResource(id = R.drawable.ic_no_picture),
            contentDescription = ""
        )

        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier
                    .clickable {
                        onUserClick()
                    },
                text = review.username,
                style = Caption1B
            )

            Text(
                text = DateUtils.formatDate(review.date),
                style = Caption2R
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(20.dp),
                imageVector = Icons.Rounded.Star,
                contentDescription = stringResource(id = R.string.rating),
                tint = MaterialTheme.colors.primary
            )
            
            Text(
                text = review.rating.toString(),
                style = SubHeadlineB.copy(
                    color = MaterialTheme.colors.primary
                )
            )
        }
    }
}

@Preview
@Composable
private fun ReviewUserItemPreview() {
    EcoSwapTheme {
        StoreReviewUserItem(
            review = Dummy.storeReviews[0],
            onUserClick = {  }
        )
    }
}