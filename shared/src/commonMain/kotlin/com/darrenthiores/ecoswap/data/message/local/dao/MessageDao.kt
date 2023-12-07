package com.darrenthiores.ecoswap.data.message.local.dao

import com.darrenthiores.ecoswap.domain.message.model.Inbox
import com.darrenthiores.ecoswap.domain.message.model.Message
import database.ChatEntity
import database.InboxEntity

interface MessageDao {
    suspend fun getUnreadCount(
        userId: String
    ): Int

    suspend fun getMessagesById(
        sentToId: String,
        sentFromId: String
    ): List<ChatEntity>

    suspend fun insertMessage(
        messageId: String,
        inboxId: String,
        sentToId: String,
        sentToUsername: String,
        sentToImageUrl: String,
        sentFromId: String,
        sentFromUsername: String,
        sentFromImageUrl: String,
        message: String,
        mediaUrl: String
    )

    suspend fun getInbox(userId: String): List<InboxEntity>

    suspend fun createInbox(
        inboxId: String,
        sentToId: String,
        sentToUsername: String,
        sentToImageUrl: String,
        sentFromId: String,
        message: String
    )

    suspend fun updateInbox(
        inboxId: String,
        sentFromId: String,
        message: String,
    )

    suspend fun saveInboxes(
        items: List<Inbox>
    )

    suspend fun saveMessages(
        items: List<Message>
    )
}