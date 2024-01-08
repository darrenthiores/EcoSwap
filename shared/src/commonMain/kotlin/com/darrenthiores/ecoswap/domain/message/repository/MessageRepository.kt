package com.darrenthiores.ecoswap.domain.message.repository

import com.darrenthiores.ecoswap.domain.message.model.Inbox
import com.darrenthiores.ecoswap.domain.message.model.Message
import com.darrenthiores.ecoswap.domain.utils.Resource
import com.darrenthiores.ecoswap.utils.flow.CommonFlow

interface MessageRepository {
    suspend fun getUnreadCount(
        userId: String,
        fetch: Boolean = false
    ): Resource<Int>

    suspend fun getMessagesById(
        sentToId: String,
        sentFromId: String,
        fetch: Boolean = false
    ): Resource<List<Message>>

    suspend fun insertMessage(
        inboxId: String,
        sentToId: String,
        sentToUsername: String,
        sentToImageUrl: String,
        sentFromId: String,
        sentFromUsername: String,
        sentFromImageUrl: String,
        message: String,
        mediaUrl: String,
        create: Boolean
    ): Resource<Message>

    suspend fun getInbox(
        userId: String,
        fetch: Boolean = false
    ): CommonFlow<Resource<List<Inbox>>>

    suspend fun updateInbox(
        inboxId: String,
        sentFromId: String,
        message: String
    ): Resource<Unit>
}