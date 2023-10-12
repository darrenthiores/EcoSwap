package com.darrenthiores.ecoswap.domain.auth.use_cases

import com.darrenthiores.ecoswap.domain.auth.repository.AuthRepository
import com.darrenthiores.ecoswap.domain.core.preferences.TokenPreferences
import com.darrenthiores.ecoswap.domain.utils.Resource

class Login(
    private val repository: AuthRepository,
    private val tokenPreferences: TokenPreferences
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Resource<Unit> {
        val result = repository
            .login(
                email = email,
                password = password
            )

        if (result is Resource.Success) {
            tokenPreferences.setToken(
                accessToken = "",
                refreshToken = "",
            )

            repository.resetToken()
        }

        return result
    }
}