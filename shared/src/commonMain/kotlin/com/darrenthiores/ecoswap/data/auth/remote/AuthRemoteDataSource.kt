package com.darrenthiores.ecoswap.data.auth.remote

import com.darrenthiores.ecoswap.data.auth.remote.service.AuthService
import com.darrenthiores.ecoswap.data.utils.ApiResponse
import com.darrenthiores.ecoswap.data.utils.tryCatch
import com.darrenthiores.ecoswap.utils.dispatchers.DispatchersProvider
import kotlinx.coroutines.withContext

class AuthRemoteDataSource(
    private val apiService: AuthService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun login(
        email: String,
        password: String
    ): ApiResponse<Unit> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.login()

                ApiResponse.Success(result)
            }
        }
    }

    suspend fun register(
        username: String,
        email: String,
        password: String
    ): ApiResponse<Unit> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.register()

                ApiResponse.Success(result)
            }
        }
    }

    suspend fun resetToken() {
        apiService.resetToken()
    }
}