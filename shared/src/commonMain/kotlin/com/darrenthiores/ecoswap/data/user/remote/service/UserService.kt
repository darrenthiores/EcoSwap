package com.darrenthiores.ecoswap.data.user.remote.service

import com.darrenthiores.ecoswap.domain.user.model.User

interface UserService {
    suspend fun getUser(): User

    suspend fun getUserById(id: String): User?

    companion object {
        private const val BASE_URL = ""
    }
}