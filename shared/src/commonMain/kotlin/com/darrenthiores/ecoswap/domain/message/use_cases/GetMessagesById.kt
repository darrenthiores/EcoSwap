package com.darrenthiores.ecoswap.domain.message.use_cases

import com.darrenthiores.ecoswap.domain.message.model.Message
import com.darrenthiores.ecoswap.domain.message.repository.MessageRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class GetMessagesById(
    private val repository: MessageRepository
) {
    suspend operator fun invoke(
        sentToId: String,
        sentFromId: String,
        fetch: Boolean = false
    ): Resource<List<Message>> {
        return repository
            .getMessagesById(
                sentToId = sentToId,
                sentFromId = sentFromId,
                fetch = fetch
            )
    }
}