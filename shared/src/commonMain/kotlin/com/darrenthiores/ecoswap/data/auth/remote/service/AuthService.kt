package com.darrenthiores.ecoswap.data.auth.remote.service

interface AuthService {
    suspend fun login()
    suspend fun register()
    suspend fun resetToken()

    companion object {
        private const val BASE_URL = ""
        const val LOGIN_URL = "$BASE_URL/"
        const val REGISTER_URL = "$BASE_URL/"
    }
}