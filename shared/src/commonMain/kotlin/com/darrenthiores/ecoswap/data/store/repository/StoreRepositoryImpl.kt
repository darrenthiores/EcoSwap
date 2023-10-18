package com.darrenthiores.ecoswap.data.store.repository

import com.darrenthiores.ecoswap.data.store.remote.StoreRemoteDataSource
import com.darrenthiores.ecoswap.data.utils.ApiResponse
import com.darrenthiores.ecoswap.domain.store.model.Store
import com.darrenthiores.ecoswap.domain.store.repository.StoreRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class StoreRepositoryImpl(
    private val remoteDataSource: StoreRemoteDataSource
): StoreRepository {
    override suspend fun getStores(page: Int): Resource<List<Store>> {
        val result = remoteDataSource
            .getStores(
                page = page
            )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data)
        }
    }

    override suspend fun getStoreById(id: String): Resource<Store?> {
        val result = remoteDataSource
            .getStoreById(
                id = id
            )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data)
        }
    }

    override suspend fun searchStores(
        page: Int,
        text: String,
        categoryId: String?,
    ): Resource<List<Store>> {
        val result = remoteDataSource
            .searchStores(
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