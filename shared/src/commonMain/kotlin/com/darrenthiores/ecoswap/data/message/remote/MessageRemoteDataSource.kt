package com.darrenthiores.ecoswap.data.message.remote

import com.darrenthiores.ecoswap.data.message.remote.service.MessageService
import com.darrenthiores.ecoswap.data.utils.ApiResponse
import com.darrenthiores.ecoswap.data.utils.tryCatch
import com.darrenthiores.ecoswap.domain.message.model.Inbox
import com.darrenthiores.ecoswap.domain.message.model.Message
import com.darrenthiores.ecoswap.utils.dispatchers.DispatchersProvider
import kotlinx.coroutines.withContext

class MessageRemoteDataSource(
    private val apiService: MessageService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun getUnreadCount(
        userId: String
    ): ApiResponse<Int> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getUnreadCount(
                    userId = userId
                )

                ApiResponse.Success(result)
            }
        }
    }

    suspend fun getMessagesById(
        sentToId: String,
        sentFromId: String
    ): ApiResponse<List<Message>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getMessagesById(
                    sentToId = sentToId,
                    sentFromId = sentFromId
                )

                ApiResponse.Success(result)
            }
        }
    }

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
    ): ApiResponse<String> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.insertMessage(
                    inboxId = inboxId,
                    sentToId = sentToId,
                    sentToUsername = sentToUsername,
                    sentToImageUrl = sentToImageUrl,
                    sentFromId = sentFromId,
                    sentFromUsername = sentFromUsername,
                    sentFromImageUrl = sentFromImageUrl,
                    message = message,
                    mediaUrl = mediaUrl
                )

                ApiResponse.Success(result)
            }
        }
    }

    suspend fun getInbox(userId: String): ApiResponse<List<Inbox>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getInbox(
                    userId = userId
                )

                ApiResponse.Success(result)
            }
        }
    }

    suspend fun createInbox(
        sentToId: String,
        sentToUsername: String,
        sentToImageUrl: String,
        sentFromId: String,
        message: String
    ): ApiResponse<String> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.createInbox(
                    sentFromId = sentFromId,
                    sentToId = sentToId,
                    sentToUsername = sentToUsername,
                    sentToImageUrl = sentToImageUrl,
                    message = message
                )

                ApiResponse.Success(result)
            }
        }
    }

    suspend fun updateInbox(
        inboxId: String,
        sentFromId: String,
        message: String,
    ): ApiResponse<Unit> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.updateInbox(
                    inboxId = inboxId,
                    sentFromId = sentFromId,
                    message = message
                )

                ApiResponse.Success(result)
            }
        }
    }
}