package com.darrenthiores.ecoswap.android.presentation.inbox

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.components.header.DefaultHeader
import com.darrenthiores.ecoswap.android.presentation.inbox.components.InboxItem
import com.darrenthiores.ecoswap.presentation.inbox.InboxState

@Composable
fun InboxScreen(
    state: InboxState,
    onBackClick: () -> Unit,
    onMessageClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            DefaultHeader(
                modifier = Modifier
                    .padding(16.dp),
                text = stringResource(id = R.string.inbox),
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(
                vertical = 16.dp
            )
        ) {
            items(
                items = state.inboxes,
                key = { inbox -> inbox.id }
            ) { inbox ->
                InboxItem(
                    modifier = Modifier,
                    inbox = inbox,
                    onClick = {
                        onMessageClick(inbox.sentToId)
                    }
                )

                Divider(
                    startIndent = 78.dp
                )
            }
        }
    }
}