package com.darrenthiores.ecoswap.android.presentation.model_components.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.components.labels.DistanceLabel
import com.darrenthiores.ecoswap.android.presentation.model_components.user.UserHorizontal
import com.darrenthiores.ecoswap.android.theme.Caption1B
import com.darrenthiores.ecoswap.android.theme.Caption2R
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.domain.core.utils.Dummy
import com.darrenthiores.ecoswap.domain.item.model.Item

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FixedItemCard(
    modifier: Modifier = Modifier,
    item: Item,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp / 2
    val cardWidth = (screenWidth - (24*2) - 10)
    val imageHeight = (cardWidth + 24 + 5).toDouble() * 0.8

    Card(
        modifier = modifier
            .width(cardWidth.dp),
        elevation = 1.dp,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 8.dp
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                    model = ImageRequest
                        .Builder(context)
                        .data(item.imgUrl.getOrNull(0))
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.ic_no_picture),
                    error = painterResource(id = R.drawable.ic_no_picture),
                    contentDescription = item.name
                )

                DistanceLabel(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.BottomStart),
                    distance = "2,6 Km"
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = item.name,
                    style = Caption1B,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(12.dp),
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = null,
                        tint = Color.LightGray
                    )

                    Text(
                        text = item.location,
                        style = Caption2R.copy(
                            color = Color.LightGray
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                UserHorizontal(
                    name = item.username,
                    imageUrl = item.userImgUrl,
                    rating = item.rating
                )
            }
        }
    }
}

@Preview
@Composable
private fun ItemCardPreview() {
    EcoSwapTheme {
        FixedItemCard(item = Dummy.items[0]) {
            
        }
    }
}