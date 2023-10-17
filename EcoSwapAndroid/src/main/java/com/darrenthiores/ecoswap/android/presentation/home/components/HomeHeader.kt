package com.darrenthiores.ecoswap.android.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.components.textfields.DefaultTextField
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.White

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    searchText: String,
    onTextChange: (String) -> Unit,
    onNotificationClick: () -> Unit,
    onSearch: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .padding(
                horizontal = 24.dp,
                vertical = 12.dp
            ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier
                    .height(30.dp),
                painter = painterResource(id = R.drawable.logo_horizontal),
                contentDescription = stringResource(id = R.string.ecoswap_logo),
                contentScale = ContentScale.FillHeight
            )

            NotificationIcon(
                modifier = Modifier
                    .clickable {
                        onNotificationClick()
                    }
            )
        }

        DefaultTextField(
            text = searchText,
            onTextChange = onTextChange,
            placeholder = stringResource(id = R.string.search_placeholder),
            startIcon = Icons.Rounded.Search,
            onSubmit = {
                onSearch()
            }
        )
    }
}

@Preview
@Composable
private fun HomeHeaderPreview() {
    EcoSwapTheme {
        HomeHeader(
            searchText = "",
            onTextChange = {  },
            onNotificationClick = { },
            onSearch = {  }
        )
    }
}

@Composable
private fun NotificationIcon(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(34.dp)
            .clip(CircleShape)
            .background(
                White.copy(
                    alpha = 0.2f
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp),
            imageVector = Icons.Rounded.Notifications,
            contentDescription = stringResource(id = R.string.notification_desc),
            tint = White
        )
    }
}

@Preview
@Composable
private fun NotificationIconPreview() {
    EcoSwapTheme(darkTheme = true) {
        NotificationIcon()
    }
}