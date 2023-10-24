package com.darrenthiores.ecoswap.android.presentation.item_detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.model_components.user.HorizontalUserCard
import com.darrenthiores.ecoswap.android.theme.CallOutB
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.FootnoteB
import com.darrenthiores.ecoswap.android.theme.FootnoteR
import com.darrenthiores.ecoswap.android.theme.Title3B
import com.darrenthiores.ecoswap.domain.core.utils.Dummy
import com.darrenthiores.ecoswap.domain.item.model.Item
import com.darrenthiores.ecoswap.domain.user.model.User

@Composable
fun ItemInformationSection(
    modifier: Modifier = Modifier,
    item: Item?,
    user: User?,
    isExpanded: Boolean,
    onUserClick: () -> Unit,
    onToggleExpand: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(
                horizontal = 24.dp
            ),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = item?.name ?: "",
            style = Title3B
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        HorizontalUserCard(
            user = user,
            onClick = onUserClick
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.description_prod),
            style = CallOutB
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = item?.description ?: "",
            style = FootnoteR,
            maxLines = if (isExpanded) Int.MAX_VALUE else 5
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            modifier = Modifier
                .clickable {
                    onToggleExpand()
                },
            text = if (isExpanded) stringResource(id = R.string.see_less)
            else stringResource(id = R.string.see_more),
            style = FootnoteB.copy(
                color = MaterialTheme.colors.primary
            )
        )
    }
}

@Preview
@Composable
private fun ItemInformationSectionPreview() {
    EcoSwapTheme {
        ItemInformationSection(
            item = Dummy.items[0],
            user = Dummy.user,
            isExpanded = false,
            onUserClick = {  },
            onToggleExpand = {  }
        )
    }
}