package com.darrenthiores.ecoswap.domain.message.use_cases

import com.darrenthiores.ecoswap.domain.message.repository.MessageRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class UpdateInbox(
    private val repository: MessageRepository
) {
    suspend operator fun invoke(
        inboxId: String,
        sentFromId: String,
        message: String
    ): Resource<Unit> {
        return repository
            .updateInbox(
                inboxId = inboxId,
                sentFromId = sentFromId,
                message = message
            )
    }
}