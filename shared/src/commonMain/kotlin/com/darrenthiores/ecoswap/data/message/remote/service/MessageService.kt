package com.darrenthiores.ecoswap.data.message.remote.service

import com.darrenthiores.ecoswap.domain.message.model.Inbox
import com.darrenthiores.ecoswap.domain.message.model.Message

interface MessageService {
    suspend fun getUnreadCount(
        userId: String
    ): Int

    suspend fun getMessagesById(
        sentToId: String,
        sentFromId: String
    ): List<Message>

    suspend fun insertMessage(
        inboxId: String,
        sentToId: String,
        sentToUsername: String,
        sentToImageUrl: String,
        sentFromId: String,
        sentFromUsername: String,
        sentFromImageUrl: String,
        message: String,
        mediaUrl: String
    ): Message

    suspend fun getInbox(userId: String): List<Inbox>

    suspend fun createInbox(
        sentToId: String,
        sentToUsername: String,
        sentToImageUrl: String,
        sentFromId: String,
        message: String
    ): String

    suspend fun updateInbox(
        inboxId: String,
        sentFromId: String,
        message: String,
    )
}