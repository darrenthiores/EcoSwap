package com.darrenthiores.ecoswap.data.message.local

import com.darrenthiores.ecoswap.data.message.local.dao.MessageDao
import com.darrenthiores.ecoswap.domain.message.model.Inbox
import com.darrenthiores.ecoswap.domain.message.model.Message
import database.ChatEntity
import database.InboxEntity

class MessageLocalDataSource(
    private val dao: MessageDao
) {
    suspend fun getUnreadCount(
        userId: String
    ): Int {
        return dao
            .getUnreadCount(
                userId = userId
            )
    }

    suspend fun getMessagesById(
        sentToId: String,
        sentFromId: String
    ): List<ChatEntity> {
        return dao
            .getMessagesById(
                sentToId = sentToId,
                sentFromId
            )
    }

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
        mediaUrl: String,
        timestamp: Long
    ) {
        dao
            .insertMessage(
                messageId = messageId,
                inboxId = inboxId,
                sentToId = sentToId,
                sentToUsername = sentToUsername,
                sentToImageUrl = sentToImageUrl,
                sentFromId = sentFromId,
                sentFromUsername = sentFromUsername,
                sentFromImageUrl = sentFromImageUrl,
                message = message,
                mediaUrl = mediaUrl,
                timestamp = timestamp
            )
    }

    suspend fun getInbox(userId: String): List<InboxEntity> {
        return dao
            .getInbox(
                userId = userId
            )
    }

    suspend fun createInbox(
        inboxId: String,
        sentToId: String,
        sentToUsername: String,
        sentToImageUrl: String,
        sentFromId: String,
        message: String
    ) {
        dao
            .createInbox(
                inboxId = inboxId,
                sentFromId = sentFromId,
                sentToId = sentToId,
                sentToUsername = sentToUsername,
                sentToImageUrl = sentToImageUrl,
                message = message
            )
    }

    suspend fun updateInbox(
        inboxId: String,
        sentFromId: String,
        message: String,
    ) {
        dao
            .updateInbox(
                inboxId = inboxId,
                sentFromId = sentFromId,
                message = message
            )
    }

    suspend fun saveInboxes(
        items: List<Inbox>
    ) {
        dao
            .saveInboxes(
                items = items
            )
    }

    suspend fun saveMessages(
        items: List<Message>
    ) {
        dao
            .saveMessages(
                items = items
            )
    }
}