package com.darrenthiores.ecoswap.data.message.mapper

import com.darrenthiores.ecoswap.domain.message.model.Inbox
import com.darrenthiores.ecoswap.domain.message.model.Message
import com.darrenthiores.ecoswap.utils.date.DateUtils
import database.ChatEntity
import database.InboxEntity

fun InboxEntity.toInbox(): Inbox {
    return Inbox(
        id = id,
        sentFromId = sentFromId,
        sentToId = sentToId,
        sentToUsername = sentToUsername,
        sentToImageUrl = sentToImageUrl,
        lastMessage = lastMessage,
        lastSendUserId = lastSendUserId
    )
}

fun ChatEntity.toMessage(): Message {
    return Message(
        id = id,
        inboxId = inboxId,
        sentToId = sentToId,
        sentToUsername = sentToUsername,
        sentToImageUrl = sentToImageUrl,
        sentFromId = sentFromId,
        sentFromUsername = sentFromUsername,
        sentFromImageUrl = sentFromImageUrl,
        content = content,
        mediaUrl = mediaUrl,
        date = DateUtils.toLocalDateTime(
            timestamp = timestamp
        ),
        isRead = isRead
    )
}