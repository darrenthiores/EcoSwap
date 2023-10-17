package com.darrenthiores.ecoswap.data.item.remote

import com.darrenthiores.ecoswap.data.item.remote.service.ItemService
import com.darrenthiores.ecoswap.data.utils.ApiResponse
import com.darrenthiores.ecoswap.data.utils.tryCatch
import com.darrenthiores.ecoswap.domain.item.model.Item
import com.darrenthiores.ecoswap.utils.dispatchers.DispatchersProvider
import kotlinx.coroutines.withContext

class ItemRemoteDataSource(
    private val apiService: ItemService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun getItems(page: Int): ApiResponse<List<Item>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getItems(
                    page = page
                )

                ApiResponse.Success(result)
            }
        }
    }

    suspend fun getItemById(id: String): ApiResponse<Item?> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getItemById(
                    id = id
                )

                ApiResponse.Success(result)
            }
        }
    }

    suspend fun searchItems(
        page: Int,
        text: String,
        categoryId: String?
    ): ApiResponse<List<Item>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.searchItems(
                    page = page,
                    text = text,
                    categoryId = categoryId
                )

                ApiResponse.Success(result)
            }
        }
    }
}