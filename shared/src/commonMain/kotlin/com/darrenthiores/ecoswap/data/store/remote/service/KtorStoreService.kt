package com.darrenthiores.ecoswap.data.store.remote.service

import com.darrenthiores.ecoswap.domain.core.utils.Dummy
import com.darrenthiores.ecoswap.domain.store.model.Store
import io.ktor.client.HttpClient

class KtorStoreService(
    private val client: HttpClient
): StoreService {
    override suspend fun getStores(page: Int): List<Store> {
        return Dummy.stores
    }

    override suspend fun getStoreById(id: String): Store? {
        return Dummy
            .stores
            .firstOrNull { it.id == id }
    }

    override suspend fun searchStores(page: Int, text: String, categoryId: String?): List<Store> {
        if (categoryId != null) {
            return Dummy
                .stores
                .filter {
                    it.name.lowercase().contains(
                        text.lowercase()
                    ) && it.categoryId == categoryId
                }
        } else {
            return Dummy
                .stores
                .filter {
                    it.name.lowercase().contains(
                        text.lowercase()
                    )
                }
        }
    }

}