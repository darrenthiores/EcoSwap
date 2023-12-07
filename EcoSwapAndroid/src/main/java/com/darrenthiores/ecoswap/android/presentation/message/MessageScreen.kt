package com.darrenthiores.ecoswap.android.presentation.message

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.presentation.message.components.FriendMessageItem
import com.darrenthiores.ecoswap.android.presentation.message.components.MessageBottomBar
import com.darrenthiores.ecoswap.android.presentation.message.components.MessageHeader
import com.darrenthiores.ecoswap.android.presentation.message.components.PersonalMessageItem
import com.darrenthiores.ecoswap.presentation.message.MessageEvent
import com.darrenthiores.ecoswap.presentation.message.MessageState

@Composable
fun MessageScreen(
    state: MessageState,
    onEvent: (MessageEvent) -> Unit,
    onSettingClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            MessageHeader(
                text = state.user?.name ?: "",
                onBackClick = onBackClick,
                onSettingClick = onSettingClick
            )
        },
        bottomBar = {
            MessageBottomBar(
                message = state.newMessage,
                onMessageChange = {
                    onEvent(
                        MessageEvent.OnMessageChange(it)
                    )
                },
                onAddPhotoClick = {  },
                onSendClick = {
                    onEvent(MessageEvent.SendMessage)
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            reverseLayout = true
        ) {
            items(
                items = state.messages,
                key = { message -> message.id }
            ) { message ->
                if (message.sentToId == state.user?.id) {
                    PersonalMessageItem(
                        text = message.content,
                        username = message.sentFromUsername,
                        userImgUrl = message.sentFromImageUrl
                    )
                } else {
                    FriendMessageItem(
                        text = message.content,
                        username = message.sentToUsername,
                        userImgUrl = message.sentToImageUrl
                    )
                }
            }
        }
    }
}