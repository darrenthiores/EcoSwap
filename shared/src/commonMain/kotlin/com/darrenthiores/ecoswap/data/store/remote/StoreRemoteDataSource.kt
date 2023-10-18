package com.darrenthiores.ecoswap.data.store.remote

import com.darrenthiores.ecoswap.data.store.remote.service.StoreService
import com.darrenthiores.ecoswap.data.utils.ApiResponse
import com.darrenthiores.ecoswap.data.utils.tryCatch
import com.darrenthiores.ecoswap.domain.store.model.Store
import com.darrenthiores.ecoswap.utils.dispatchers.DispatchersProvider
import kotlinx.coroutines.withContext

class StoreRemoteDataSource(
    private val apiService: StoreService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun getStores(page: Int): ApiResponse<List<Store>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getStores(
                    page = page
                )

                ApiResponse.Success(result)
            }
        }
    }

    suspend fun getStoreById(id: String): ApiResponse<Store?> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getStoreById(
                    id = id
                )

                ApiResponse.Success(result)
            }
        }
    }

    suspend fun searchStores(
        page: Int,
        text: String,
        categoryId: String?
    ): ApiResponse<List<Store>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.searchStores(
                    page = page,
                    text = text,
                    categoryId = categoryId
                )

                ApiResponse.Success(result)
            }
        }
    }
}