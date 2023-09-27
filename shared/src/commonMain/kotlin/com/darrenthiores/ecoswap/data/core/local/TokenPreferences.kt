package com.darrenthiores.ecoswap.data.core.local

interface TokenPreferences {
    fun getAccessToken(): String

    fun getRefreshToken(): String

    fun setToken(
        accessToken: String,
        refreshToken: String
    )

    fun resetToken()
}