package com.darrenthiores.ecoswap.android.presentation.model_components.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.theme.Blue
import com.darrenthiores.ecoswap.android.theme.Caption2R
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme

@Composable
fun UserHorizontal(
    modifier: Modifier = Modifier,
    name: String,
    imageUrl: String,
    rating: Double
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(14.dp)
                .clip(CircleShape),
            model = ImageRequest
                .Builder(context)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_no_picture),
            error = painterResource(id = R.drawable.ic_no_picture),
            contentDescription = name
        )

        Text(
            text = name,
            style = Caption2R.copy(
                color = Blue.copy(
                    alpha = 0.8f
                )
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(10.dp),
                imageVector = Icons.Rounded.Star,
                contentDescription = stringResource(id = R.string.rating_placeholder),
                tint = MaterialTheme.colors.primary
            )
            
            Text(
                text = rating.toString(),
                style = Caption2R.copy(
                    color = Color.Gray
                )
            )
        }
    }
}

@Preview
@Composable
private fun UserHorizontalPreview() {
    EcoSwapTheme {
        UserHorizontal(
            name = "John",
            imageUrl = "",
            rating = 4.9
        )
    }
}