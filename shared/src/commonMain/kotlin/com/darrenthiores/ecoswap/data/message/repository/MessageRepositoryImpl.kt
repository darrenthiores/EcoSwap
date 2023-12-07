package com.darrenthiores.ecoswap.data.message.repository

import com.darrenthiores.ecoswap.data.message.local.MessageLocalDataSource
import com.darrenthiores.ecoswap.data.message.mapper.toInbox
import com.darrenthiores.ecoswap.data.message.mapper.toMessage
import com.darrenthiores.ecoswap.data.message.remote.MessageRemoteDataSource
import com.darrenthiores.ecoswap.data.utils.ApiResponse
import com.darrenthiores.ecoswap.data.utils.NetworkBoundResource
import com.darrenthiores.ecoswap.domain.message.model.Inbox
import com.darrenthiores.ecoswap.domain.message.model.Message
import com.darrenthiores.ecoswap.domain.message.repository.MessageRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class MessageRepositoryImpl(
    private val remoteDataSource: MessageRemoteDataSource,
    private val localDataSource: MessageLocalDataSource
): MessageRepository {
    override suspend fun getUnreadCount(
        userId: String,
        fetch: Boolean
    ): Resource<Int> {
        return if (fetch) {
            val result = remoteDataSource
                .getUnreadCount(
                    userId = userId
                )

            when (result) {
                ApiResponse.Empty -> {
                    val fromLocal = localDataSource
                        .getUnreadCount(
                            userId = userId
                        )

                    Resource.Success(fromLocal)
                }
                is ApiResponse.Error -> {
                    val fromLocal = localDataSource
                        .getUnreadCount(
                            userId = userId
                        )

                    Resource.Success(fromLocal)
                }
                is ApiResponse.Success -> {
                    Resource.Success(result.data)
                }
            }
        } else {
            val fromLocal = localDataSource
                .getUnreadCount(
                    userId = userId
                )

            Resource.Success(fromLocal)
        }
    }

    override suspend fun getMessagesById(
        sentToId: String,
        sentFromId: String,
        fetch: Boolean,
    ): Resource<List<Message>> =
        object : NetworkBoundResource<List<Message>, List<Message>>() {
            override suspend fun loadFromDB(): List<Message> {
                return localDataSource
                    .getMessagesById(
                        sentToId = sentToId,
                        sentFromId = sentFromId
                    )
                    .map {
                        it.toMessage()
                    }
            }

            override fun shouldFetch(): Boolean {
                return fetch
            }

            override suspend fun createCall(): ApiResponse<List<Message>> {
                return remoteDataSource
                    .getMessagesById(
                        sentToId = sentToId,
                        sentFromId = sentFromId
                    )
            }

            override suspend fun saveCallResult(data: List<Message>) {
                localDataSource
                    .saveMessages(
                        items = data
                    )
            }

        }.result()

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
        create: Boolean,
    ): Resource<Unit> {
        if (create) {
            val result = remoteDataSource
                .createInbox(
                    sentToId = sentToId,
                    sentToUsername = sentToUsername,
                    sentToImageUrl = sentToImageUrl,
                    sentFromId = sentFromId,
                    message = message
                )

            when (result) {
                ApiResponse.Empty -> {
                    return Resource.Error("Unknown Error Just Occurred!")
                }
                is ApiResponse.Error -> {
                    return Resource.Error(result.errorMessage)
                }
                is ApiResponse.Success -> {
                    localDataSource
                        .createInbox(
                            inboxId = result.data,
                            sentToId = sentToId,
                            sentToUsername = sentToUsername,
                            sentToImageUrl = sentToImageUrl,
                            sentFromId = sentFromId,
                            message = message
                        )

                    val messageResult = remoteDataSource
                        .insertMessage(
                            inboxId = result.data,
                            sentToId = sentToId,
                            sentToUsername = sentToUsername,
                            sentToImageUrl = sentToImageUrl,
                            sentFromId = sentFromId,
                            sentFromUsername = sentFromUsername,
                            sentFromImageUrl = sentFromImageUrl,
                            message = message,
                            mediaUrl = mediaUrl
                        )

                    when (messageResult) {
                        ApiResponse.Empty -> {
                            return Resource.Error("Unknown Error Just Occurred!")
                        }
                        is ApiResponse.Error -> {
                            return Resource.Error(messageResult.errorMessage)
                        }
                        is ApiResponse.Success -> {
                            localDataSource
                                .insertMessage(
                                    messageId = messageResult.data,
                                    inboxId = result.data,
                                    sentToId = sentToId,
                                    sentToUsername = sentToUsername,
                                    sentToImageUrl = sentToImageUrl,
                                    sentFromId = sentFromId,
                                    sentFromUsername = sentFromUsername,
                                    sentFromImageUrl = sentFromImageUrl,
                                    message = message,
                                    mediaUrl = mediaUrl
                                )

                            return Resource.Success(Unit)
                        }
                    }
                }
            }
        } else {
            val messageResult = remoteDataSource
                .insertMessage(
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

            when (messageResult) {
                ApiResponse.Empty -> {
                    return Resource.Error("Unknown Error Just Occurred!")
                }
                is ApiResponse.Error -> {
                    return Resource.Error(messageResult.errorMessage)
                }
                is ApiResponse.Success -> {
                    localDataSource
                        .insertMessage(
                            messageId = messageResult.data,
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

                    val updateResult = remoteDataSource
                        .updateInbox(
                            inboxId = inboxId,
                            sentFromId = sentFromId,
                            message = message
                        )

                    when (updateResult) {
                        ApiResponse.Empty -> {
                            return Resource.Error("Unknown Error Just Occurred!")
                        }
                        is ApiResponse.Error -> {
                            return Resource.Error(updateResult.errorMessage)
                        }
                        is ApiResponse.Success -> {
                            localDataSource
                                .updateInbox(
                                    inboxId = inboxId,
                                    sentFromId = sentFromId,
                                    message = message
                                )

                            return Resource.Success(Unit)
                        }
                    }
                }
            }
        }
    }

    override suspend fun getInbox(
        userId: String,
        fetch: Boolean
    ): Resource<List<Inbox>> =
        object : NetworkBoundResource<List<Inbox>, List<Inbox>>() {
            override suspend fun loadFromDB(): List<Inbox> {
                return localDataSource
                    .getInbox(
                        userId = userId
                    )
                    .map {
                        it.toInbox()
                    }
            }

            override fun shouldFetch(): Boolean {
                return fetch
            }

            override suspend fun createCall(): ApiResponse<List<Inbox>> {
                return remoteDataSource
                    .getInbox(
                        userId = userId
                    )
            }

            override suspend fun saveCallResult(data: List<Inbox>) {
                localDataSource
                    .saveInboxes(
                        items = data
                    )
            }

        }.result()

    override suspend fun updateInbox(
        inboxId: String,
        sentFromId: String,
        message: String,
    ): Resource<Unit> {
        val result = remoteDataSource
            .updateInbox(
                inboxId = inboxId,
                sentFromId = sentFromId,
                message = message
            )

        return when (result) {
            ApiResponse.Empty -> {
                Resource.Error("Unknown Error Just Occurred!")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                localDataSource
                    .updateInbox(
                        inboxId = inboxId,
                        sentFromId = sentFromId,
                        message = message
                    )

                Resource.Success(Unit)
            }
        }
    }
}