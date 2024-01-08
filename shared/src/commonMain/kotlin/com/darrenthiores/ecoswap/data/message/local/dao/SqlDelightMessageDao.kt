package com.darrenthiores.ecoswap.data.message.local.dao

import com.darrenthiores.ecoswap.database.AppDatabase
import com.darrenthiores.ecoswap.domain.message.model.Inbox
import com.darrenthiores.ecoswap.domain.message.model.Message
import com.darrenthiores.ecoswap.utils.date.DateUtils
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import database.ChatEntity
import database.InboxEntity
import kotlinx.coroutines.flow.Flow

class SqlDelightMessageDao(
    db: AppDatabase
): MessageDao {
    private val chatQueries = db.chatQueries
    private val inboxQueries = db.inboxQueries

    override suspend fun getUnreadCount(userId: String): Int {
        return chatQueries
            .getUnreadCount(
                sentToId = userId
            )
            .executeAsOne()
            .toInt()
    }

    override suspend fun getMessagesById(sentToId: String, sentFromId: String): List<ChatEntity> {
        return chatQueries
            .getMessagesById(
                sentToId = sentToId,
                sentFromId = sentFromId,
                sentToId_ = sentFromId,
                sentFromId_ = sentToId
            )
            .executeAsList()
    }

    override suspend fun insertMessage(
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
        chatQueries.insertMessage(
            id = messageId,
            inboxId = inboxId,
            sentToId = sentToId,
            sentToUsername = sentToUsername,
            sentToImageUrl = sentToImageUrl,
            sentFromId = sentFromId,
            sentFromUsername = sentFromUsername,
            sentFromImageUrl = sentFromImageUrl,
            content = message,
            mediaUrl = mediaUrl,
            timestamp = timestamp,
            isRead = false
        )
    }

    override suspend fun getInbox(userId: String): Flow<List<InboxEntity>> {
        return inboxQueries
            .getInbox(
                sentFromId = userId
            )
            .asFlow()
            .mapToList()
    }

    override suspend fun createInbox(
        inboxId: String,
        sentToId: String,
        sentToUsername: String,
        sentToImageUrl: String,
        sentFromId: String,
        message: String
    ) {
        inboxQueries.createInbox(
            id = inboxId,
            sentFromId = sentFromId,
            sentToId = sentToId,
            sentToUsername = sentToUsername,
            sentToImageUrl = sentToImageUrl,
            lastMessage = message,
            lastSendUserId = sentFromId
        )
    }

    override suspend fun updateInbox(inboxId: String, sentFromId: String, message: String) {
        inboxQueries.updateInbox(
            id = inboxId,
            lastMessage = message,
            lastSendUserId = sentFromId
        )
    }

    override suspend fun saveInboxes(items: List<Inbox>) {
        inboxQueries.transaction {
            items.forEach { inbox ->
                inboxQueries.createInbox(
                    id = inbox.id,
                    sentFromId = inbox.sentFromId,
                    sentToId = inbox.sentToId,
                    sentToUsername = inbox.sentToUsername,
                    sentToImageUrl = inbox.sentToImageUrl,
                    lastMessage = inbox.lastMessage,
                    lastSendUserId = inbox.sentFromId
                )
            }
        }
    }

    override suspend fun saveMessages(items: List<Message>) {
        chatQueries.transaction {
            items.forEach { chat ->
                chatQueries.insertMessage(
                    id = chat.id,
                    inboxId = chat.inboxId,
                    sentToId = chat.sentToId,
                    sentToUsername = chat.sentToUsername,
                    sentToImageUrl = chat.sentToImageUrl,
                    sentFromId = chat.sentFromId,
                    sentFromUsername = chat.sentFromUsername,
                    sentFromImageUrl = chat.sentFromImageUrl,
                    content = chat.content,
                    mediaUrl = chat.mediaUrl,
                    timestamp = DateUtils.toEpochMillis(
                        dateTime = chat.date
                    ),
                    isRead = chat.isRead
                )
            }
        }
    }
}