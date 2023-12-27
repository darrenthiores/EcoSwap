package com.darrenthiores.ecoswap.android.presentation.model_components.challenge

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme

@Composable
fun AccountImageRow(
    modifier: Modifier = Modifier,
    images: List<String>,
    size: Int = 24
) {
    val context = LocalContext.current
    val width = (images.size * size) - ((images.size - 1) * (size/3))

    Box(
        modifier = modifier
            .width(width.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        images.getOrNull(0)?.let { image ->
            AsyncImage(
                modifier = Modifier
                    .size(size.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.background,
                        shape = CircleShape
                    ),
                contentScale = ContentScale.Crop,
                model = ImageRequest
                    .Builder(context)
                    .data(image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.ic_no_picture),
                error = painterResource(id = R.drawable.ic_no_picture),
                contentDescription = image
            )
        }

        images.getOrNull(1)?.let { image ->
            AsyncImage(
                modifier = Modifier
                    .offset(
                        x = size.dp - (size/3).dp
                    )
                    .size(size.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color =MaterialTheme.colors.background,
                        shape = CircleShape
                    ),
                contentScale = ContentScale.Crop,
                model = ImageRequest
                    .Builder(context)
                    .data(image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.ic_no_picture),
                error = painterResource(id = R.drawable.ic_no_picture),
                contentDescription = image
            )
        }

        images.getOrNull(2)?.let { image ->
            AsyncImage(
                modifier = Modifier
                    .offset(
                        x = size.dp + (size/3).dp
                    )
                    .size(size.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.background,
                        shape = CircleShape
                    ),
                contentScale = ContentScale.Crop,
                model = ImageRequest
                    .Builder(context)
                    .data(image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.ic_no_picture),
                error = painterResource(id = R.drawable.ic_no_picture),
                contentDescription = image
            )
        }
    }
}

@Preview
@Composable
private fun AccountImageRowPreview() {
    EcoSwapTheme {
        AccountImageRow(
            images = listOf(
                "",
                "",
                ""
            )
        )
    }
}