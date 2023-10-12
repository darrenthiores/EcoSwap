package com.darrenthiores.ecoswap.domain.auth.repository

import com.darrenthiores.ecoswap.domain.utils.Resource

interface AuthRepository {
    suspend fun login(
        email: String,
        password: String
    ): Resource<Unit>

    suspend fun register(
        username: String,
        email: String,
        password: String
    ): Resource<Unit>

    suspend fun resetToken()
}