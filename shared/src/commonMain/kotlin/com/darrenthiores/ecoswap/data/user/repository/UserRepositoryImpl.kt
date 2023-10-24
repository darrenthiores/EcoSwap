package com.darrenthiores.ecoswap.data.user.repository

import com.darrenthiores.ecoswap.data.user.remote.UserRemoteDataSource
import com.darrenthiores.ecoswap.data.utils.ApiResponse
import com.darrenthiores.ecoswap.domain.user.model.User
import com.darrenthiores.ecoswap.domain.user.repository.UserRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource
): UserRepository {
    override suspend fun getUser(): Resource<User> {
        val result = remoteDataSource
            .getUser()

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data)
        }
    }

    override suspend fun getUserById(id: String): Resource<User?> {
        val result = remoteDataSource
            .getUserById(
                id = id
            )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data)
        }
    }
}