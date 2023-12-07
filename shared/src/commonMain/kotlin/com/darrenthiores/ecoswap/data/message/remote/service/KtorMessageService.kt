package com.darrenthiores.ecoswap.data.message.remote.service

import com.darrenthiores.ecoswap.domain.message.model.Inbox
import com.darrenthiores.ecoswap.domain.message.model.Message
import com.darrenthiores.ecoswap.utils.date.DateUtils
import com.darrenthiores.ecoswap.utils.uuid.randomUUID
import io.ktor.client.HttpClient
import kotlinx.datetime.Clock

class KtorMessageService(
    private val client: HttpClient
): MessageService  {
    override suspend fun getUnreadCount(userId: String): Int {
        return 0
    }

    override suspend fun getMessagesById(sentToId: String, sentFromId: String): List<Message> {
        return emptyList()
    }

    override suspend fun insertMessage(
        inboxId: String,
        sentToId: String,
        sentToUsername: String,
        sentToImageUrl: String,
        sentFromId: String,
        sentFromUsername: String,
        sentFromImageUrl: String,
        message: String,
        mediaUrl: String,
    ): Message {
        return Message(
            id = randomUUID(),
            inboxId = inboxId,
            sentToId = sentToId,
            sentToUsername = sentToUsername,
            sentToImageUrl = sentToImageUrl,
            sentFromId = sentFromId,
            sentFromUsername = sentFromUsername,
            sentFromImageUrl = sentFromImageUrl,
            content = message,
            mediaUrl = mediaUrl,
            date = DateUtils.toLocalDateTime(
                timestamp = Clock.System.now().toEpochMilliseconds()
            ),
            isRead = false
        )
    }

    override suspend fun getInbox(userId: String): List<Inbox> {
        return emptyList()
    }

    override suspend fun createInbox(
        sentToId: String,
        sentToUsername: String,
        sentToImageUrl: String,
        sentFromId: String,
        message: String,
    ): String {
        return randomUUID()
    }

    override suspend fun updateInbox(inboxId: String, sentFromId: String, message: String) {
        // should update inbox
    }
}