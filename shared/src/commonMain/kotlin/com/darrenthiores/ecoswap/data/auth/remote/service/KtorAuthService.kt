package com.darrenthiores.ecoswap.data.auth.remote.service

import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerAuthProvider
import io.ktor.client.plugins.plugin

class KtorAuthService(
    private val client: HttpClient
): AuthService {
    override suspend fun login() {
        print("login success")
    }

    override suspend fun register() {
        print("register success")
    }

    override suspend fun resetToken() {
        client.plugin(Auth)
            .providers
            .filterIsInstance<BearerAuthProvider>()
            .firstOrNull()
            ?.clearToken()
    }
}