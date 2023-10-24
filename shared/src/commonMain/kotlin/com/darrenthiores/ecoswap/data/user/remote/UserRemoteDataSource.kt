package com.darrenthiores.ecoswap.data.user.remote

import com.darrenthiores.ecoswap.data.user.remote.service.UserService
import com.darrenthiores.ecoswap.data.utils.ApiResponse
import com.darrenthiores.ecoswap.data.utils.tryCatch
import com.darrenthiores.ecoswap.domain.user.model.User
import com.darrenthiores.ecoswap.utils.dispatchers.DispatchersProvider
import kotlinx.coroutines.withContext

class UserRemoteDataSource(
    private val apiService: UserService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun getUser(): ApiResponse<User> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getUser()

                ApiResponse.Success(result)
            }
        }
    }

    suspend fun getUserById(id: String): ApiResponse<User?> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getUserById(
                    id = id
                )

                ApiResponse.Success(result)
            }
        }
    }
}