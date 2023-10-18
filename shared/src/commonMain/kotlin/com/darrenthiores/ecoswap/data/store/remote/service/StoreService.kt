package com.darrenthiores.ecoswap.data.store.remote.service

import com.darrenthiores.ecoswap.domain.store.model.Store

interface StoreService {
    suspend fun getStores(page: Int): List<Store>

    suspend fun getStoreById(id: String): Store?

    suspend fun searchStores(
        page: Int,
        text: String,
        categoryId: String?
    ): List<Store>

    companion object {
        private const val BASE_URL = ""
    }
}