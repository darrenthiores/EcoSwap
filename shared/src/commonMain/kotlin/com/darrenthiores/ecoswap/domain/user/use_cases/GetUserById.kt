package com.darrenthiores.ecoswap.domain.user.use_cases

import com.darrenthiores.ecoswap.domain.user.model.User
import com.darrenthiores.ecoswap.domain.user.repository.UserRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class GetUserById(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        id: String
    ): Resource<User?> {
        return repository
            .getUserById(
                id = id
            )
    }
}