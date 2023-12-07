package com.darrenthiores.ecoswap.domain.message.use_cases

import com.darrenthiores.ecoswap.domain.message.repository.MessageRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class InsertMessage(
    private val repository: MessageRepository
) {
    suspend operator fun invoke(
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
    ): Resource<Unit> {
        return repository
            .insertMessage(
                inboxId = inboxId,
                sentToId = sentToId,
                sentToUsername = sentToUsername,
                sentToImageUrl = sentToImageUrl,
                sentFromId = sentFromId,
                sentFromUsername = sentFromUsername,
                sentFromImageUrl = sentFromImageUrl,
                message = message,
                mediaUrl = mediaUrl,
                create = create
            )
    }
}