package com.darrenthiores.ecoswap.data.core.remote.client

import com.darrenthiores.ecoswap.data.core.local.TokenPreferences
import io.ktor.client.HttpClient

expect class HttpClientFactory {
    fun create(tokenPreferences: TokenPreferences): HttpClient
}