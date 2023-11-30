package com.darrenthiores.ecoswap.data.item.remote

import com.darrenthiores.ecoswap.data.item.remote.service.ItemService
import com.darrenthiores.ecoswap.data.utils.ApiResponse
import com.darrenthiores.ecoswap.data.utils.tryCatch
import com.darrenthiores.ecoswap.domain.item.model.Item
import com.darrenthiores.ecoswap.domain.item.model.ItemCategory
import com.darrenthiores.ecoswap.domain.item.model.ItemCondition
import com.darrenthiores.ecoswap.domain.item.model.StoreItem
import com.darrenthiores.ecoswap.utils.dispatchers.DispatchersProvider
import kotlinx.coroutines.withContext

class ItemRemoteDataSource(
    private val apiService: ItemService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun addItem(
        photos: List<String>,
        name: String,
        description: String,
        category: ItemCategory,
        total: Int,
        condition: ItemCondition,
        brand: String,
        location: String
    ): ApiResponse<Unit> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.addItem(
                    photos = photos,
                    name = name,
                    description = description,
                    category = category,
                    total = total,
                    condition = condition,
                    brand = brand,
                    location = location
                )

                ApiResponse.Success(result)
            }
        }
    }

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

    suspend fun getStoreItemById(id: String): ApiResponse<StoreItem?> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getStoreItemById(
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

    suspend fun getMyItems(page: Int): ApiResponse<List<Item>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getMyItems(
                    page = page
                )

                ApiResponse.Success(result)
            }
        }
    }

    suspend fun getUserItems(
        page: Int,
        id: String
    ): ApiResponse<List<Item>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getUserItems(
                    page = page,
                    id = id
                )

                ApiResponse.Success(result)
            }
        }
    }

    suspend fun getStoreItems(
        page: Int,
        id: String
    ): ApiResponse<List<StoreItem>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getStoreItems(
                    page = page,
                    id = id
                )

                ApiResponse.Success(result)
            }
        }
    }
}