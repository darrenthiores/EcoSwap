package com.darrenthiores.ecoswap.domain.message.use_cases

import com.darrenthiores.ecoswap.domain.message.repository.MessageRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class GetUnreadCount(
    private val repository: MessageRepository
) {
    suspend operator fun invoke(
        userId: String,
        fetch: Boolean = false
    ): Resource<Int> {
        return repository
            .getUnreadCount(
                userId = userId,
                fetch = fetch
            )
    }
}