package com.darrenthiores.ecoswap.data.user.remote.service

import com.darrenthiores.ecoswap.domain.core.utils.Dummy
import com.darrenthiores.ecoswap.domain.user.model.User
import io.ktor.client.HttpClient

class KtorUserService(
    private val client: HttpClient
): UserService {
    override suspend fun getUser(): User {
        return Dummy.user
    }

    override suspend fun getUserById(id: String): User? {
        return Dummy
            .users
            .firstOrNull { it.id == id }
    }

}