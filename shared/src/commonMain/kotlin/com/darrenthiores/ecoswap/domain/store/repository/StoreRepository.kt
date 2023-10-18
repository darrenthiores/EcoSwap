package com.darrenthiores.ecoswap.domain.store.repository

import com.darrenthiores.ecoswap.domain.store.model.Store
import com.darrenthiores.ecoswap.domain.utils.Resource

interface StoreRepository {
    suspend fun getStores(page: Int): Resource<List<Store>>
    suspend fun getStoreById(id: String): Resource<Store?>
    suspend fun searchStores(
        page: Int,
        text: String,
        categoryId: String?
    ): Resource<List<Store>>
}