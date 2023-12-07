package com.darrenthiores.ecoswap.domain.message.use_cases

import com.darrenthiores.ecoswap.domain.message.model.Inbox
import com.darrenthiores.ecoswap.domain.message.repository.MessageRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class GetInbox(
    private val repository: MessageRepository
) {
    suspend operator fun invoke(
        userId: String,
        fetch: Boolean = false
    ): Resource<List<Inbox>> {
        return repository
            .getInbox(
                userId = userId,
                fetch = fetch
            )
    }
}