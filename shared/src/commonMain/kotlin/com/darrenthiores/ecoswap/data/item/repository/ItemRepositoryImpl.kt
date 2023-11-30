package com.darrenthiores.ecoswap.data.item.repository

import com.darrenthiores.ecoswap.data.item.remote.ItemRemoteDataSource
import com.darrenthiores.ecoswap.data.utils.ApiResponse
import com.darrenthiores.ecoswap.domain.item.model.Item
import com.darrenthiores.ecoswap.domain.item.model.ItemCategory
import com.darrenthiores.ecoswap.domain.item.model.ItemCondition
import com.darrenthiores.ecoswap.domain.item.model.StoreItem
import com.darrenthiores.ecoswap.domain.item.repository.ItemRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class ItemRepositoryImpl(
    private val remoteDataSource: ItemRemoteDataSource
): ItemRepository {
    override suspend fun addItem(
        photos: List<String>,
        name: String,
        description: String,
        category: ItemCategory,
        total: Int,
        condition: ItemCondition,
        brand: String,
        location: String
    ): Resource<Unit> {
        val result = remoteDataSource
            .addItem(
                photos = photos,
                name = name,
                description = description,
                category = category,
                total = total,
                condition = condition,
                brand = brand,
                location = location
            )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data)
        }
    }

    override suspend fun getItems(page: Int): Resource<List<Item>> {
        val result = remoteDataSource
            .getItems(
                page = page
            )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data)
        }
    }

    override suspend fun getItemById(id: String): Resource<Item?> {
        val result = remoteDataSource
            .getItemById(
                id = id
            )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data)
        }
    }

    override suspend fun getStoreItemById(id: String): Resource<StoreItem?> {
        val result = remoteDataSource
            .getStoreItemById(
                id = id
            )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data)
        }
    }

    override suspend fun searchItems(
        page: Int,
        text: String,
        categoryId: String?,
    ): Resource<List<Item>> {
        val result = remoteDataSource
            .searchItems(
                page = page,
                text = text,
                categoryId = categoryId
            )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data)
        }
    }

    override suspend fun getMyItems(page: Int): Resource<List<Item>> {
        val result = remoteDataSource
            .getMyItems(
                page = page
            )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data)
        }
    }

    override suspend fun getUserItems(page: Int, id: String): Resource<List<Item>> {
        val result = remoteDataSource
            .getUserItems(
                page = page,
                id = id
            )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data)
        }
    }

    override suspend fun getStoreItems(page: Int, id: String): Resource<List<StoreItem>> {
        val result = remoteDataSource
            .getStoreItems(
                page = page,
                id = id
            )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data)
        }
    }
}