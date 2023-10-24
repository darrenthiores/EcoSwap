package com.darrenthiores.ecoswap.domain.user.repository

import com.darrenthiores.ecoswap.domain.user.model.User
import com.darrenthiores.ecoswap.domain.utils.Resource

interface UserRepository {
    suspend fun getUser(): Resource<User>
    suspend fun getUserById(id: String): Resource<User?>
}