package com.darrenthiores.ecoswap.domain.user.use_cases

import com.darrenthiores.ecoswap.domain.user.model.User
import com.darrenthiores.ecoswap.domain.user.repository.UserRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class GetUser(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): Resource<User> {
        return repository.getUser()
    }
}