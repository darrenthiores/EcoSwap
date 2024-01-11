package com.darrenthiores.ecoswap.android.presentation.add_item.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.White

@Composable
fun PhotoSection(
    modifier: Modifier = Modifier,
    photos: List<Uri>,
    onAdd: () -> Unit,
    onSelect: (Uri) -> Unit
) {
    val stroke = Stroke(
        width = 2f,
        pathEffect = PathEffect
            .dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )
    val primary = MaterialTheme.colors.primary

    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(
            horizontal = 24.dp
        )
    ) {
        item {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(
                        RoundedCornerShape(5.dp)
                    )
                    .background(
                        color = MaterialTheme.colors.primary.copy(
                            alpha = 0.3f
                        ),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .drawBehind {
                        drawRoundRect(
                            color = primary,
                            style = stroke,
                            cornerRadius = CornerRadius(
                                x = 5.dp.toPx(),
                                y = 5.dp.toPx()
                            )
                        )
                    }
                    .clickable {
                        onAdd()
                    }
                    .padding(
                        horizontal = 1.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(32.dp),
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(id = R.string.add),
                    tint = MaterialTheme.colors.primary
                )
            }
        }

        items(
            items = photos
        ) { photo ->
            ClickableImage(
                imageUri = photo,
                onClick = {
                    onSelect(photo)
                }
            )
        }
    }
}

@Preview
@Composable
private fun PhotoSectionPreview() {
    EcoSwapTheme {
        PhotoSection(
            photos = emptyList(),
            onAdd = {  },
            onSelect = {  }
        )
    }
}

@Composable
private fun ClickableImage(
    modifier: Modifier = Modifier,
    imageUri: Uri,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .size(80.dp)
            .clickable {
                onClick()
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .size(80.dp)
                .clip(
                    RoundedCornerShape(5.dp)
                ),
            contentScale = ContentScale.Crop,
            model = ImageRequest
                .Builder(context)
                .data(imageUri)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_no_picture),
            error = painterResource(id = R.drawable.ic_no_picture),
            contentDescription = null
        )

        Icon(
            modifier = Modifier
                .padding(4.dp)
                .size(10.dp)
                .align(
                    Alignment.TopEnd
                ),
            imageVector = Icons.Rounded.Cancel,
            contentDescription = null,
            tint = White
        )
    }
}

@Preview
@Composable
private fun ClickableImagePreview() {
    EcoSwapTheme {
        ClickableImage(imageUri = Uri.EMPTY) {
            
        }
    }
}