package com.darrenthiores.ecoswap.android.presentation.store_item_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.theme.CallOutB
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.FootnoteB
import com.darrenthiores.ecoswap.android.theme.FootnoteR
import com.darrenthiores.ecoswap.domain.core.utils.Constant
import com.darrenthiores.ecoswap.domain.core.utils.Dummy
import com.darrenthiores.ecoswap.domain.item.model.StoreItem

@Composable
fun StoreProductDetailSection(
    modifier: Modifier = Modifier,
    item: StoreItem?
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.detail_prod),
            style = CallOutB
        )

        DetailProduct(item = item)
    }
}

@Preview
@Composable
private fun StoreProductDetailSectionPreview() {
    EcoSwapTheme {
        StoreProductDetailSection(item = Dummy.storeItems[0])
    }
}

@Composable
private fun DetailProduct(
    modifier: Modifier = Modifier,
    item: StoreItem?
) {
    val category = Constant.categoryById(
        id = item?.categoryId ?: ""
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(8.dp)
            ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colors.primary.copy(
                        alpha = 0.4f
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .clip(
                    RoundedCornerShape(8.dp)
                )
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.category),
                    style = FootnoteR.copy(
                        color = Color.Gray
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = category?.display ?: "",
                    style = FootnoteB.copy(
                        color = MaterialTheme.colors.primary
                    )
                )
            }

            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )

            Row(
                modifier = Modifier
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.rating),
                    style = FootnoteR.copy(
                        color = Color.Gray
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = item?.rating.toString(),
                    style = FootnoteB.copy(
                        color = MaterialTheme.colors.primary
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun DetailProductPreview() {
    EcoSwapTheme {
        DetailProduct(item = Dummy.storeItems[0])
    }
}