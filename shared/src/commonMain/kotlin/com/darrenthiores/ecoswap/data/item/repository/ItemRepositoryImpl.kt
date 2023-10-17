package com.darrenthiores.ecoswap.data.item.repository

import com.darrenthiores.ecoswap.data.item.remote.ItemRemoteDataSource
import com.darrenthiores.ecoswap.data.utils.ApiResponse
import com.darrenthiores.ecoswap.domain.item.model.Item
import com.darrenthiores.ecoswap.domain.item.repository.ItemRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class ItemRepositoryImpl(
    private val remoteDataSource: ItemRemoteDataSource
): ItemRepository {
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
}