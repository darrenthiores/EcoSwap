package com.darrenthiores.ecoswap.android.data.core.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import com.darrenthiores.ecoswap.android.BuildConfig
import com.darrenthiores.ecoswap.domain.core.preferences.TokenPreferences
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class EncTokenPreferences @Inject constructor(
    @Named("encTokenPreferences") private val preferences: SharedPreferences
): TokenPreferences {
    override fun getAccessToken(): String = preferences.getString(BuildConfig.USER_TOKEN_KEY, "") ?: ""

    override fun getRefreshToken(): String = preferences.getString(BuildConfig.USER_REFRESH_TOKEN_KEY, "") ?: ""

    override fun setToken(
        accessToken: String,
        refreshToken: String
    ) {
        preferences.edit {
            putString(BuildConfig.USER_TOKEN_KEY, accessToken)
            putString(BuildConfig.USER_REFRESH_TOKEN_KEY, refreshToken)
        }
    }

    override fun resetToken() {
        preferences.edit {
            putString(BuildConfig.USER_TOKEN_KEY, "")
            putString(BuildConfig.USER_REFRESH_TOKEN_KEY, "")
        }
    }
}