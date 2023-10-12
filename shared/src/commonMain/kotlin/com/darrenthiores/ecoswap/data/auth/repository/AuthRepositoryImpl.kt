package com.darrenthiores.ecoswap.data.auth.repository

import com.darrenthiores.ecoswap.data.auth.remote.AuthRemoteDataSource
import com.darrenthiores.ecoswap.data.utils.ApiResponse
import com.darrenthiores.ecoswap.domain.auth.repository.AuthRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource
): AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Resource<Unit> {
        val result = remoteDataSource.login(
            email = email,
            password = password
        )

        return when (result) {
            ApiResponse.Empty -> {
                Resource.Error("Unknown Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(Unit)
            }
        }
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): Resource<Unit> {
        val result = remoteDataSource.register(
            username = username,
            email = email,
            password = password
        )

        return when (result) {
            ApiResponse.Empty -> {
                Resource.Error("Unknown Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(Unit)
            }
        }
    }

    override suspend fun resetToken() {
        return remoteDataSource.resetToken()
    }
}