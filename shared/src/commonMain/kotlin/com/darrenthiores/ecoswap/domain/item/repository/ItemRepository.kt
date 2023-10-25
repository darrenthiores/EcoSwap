package com.darrenthiores.ecoswap.domain.item.repository

import com.darrenthiores.ecoswap.domain.item.model.Item
import com.darrenthiores.ecoswap.domain.item.model.StoreItem
import com.darrenthiores.ecoswap.domain.utils.Resource

interface ItemRepository {
    suspend fun getItems(page: Int): Resource<List<Item>>
    suspend fun getItemById(id: String): Resource<Item?>
    suspend fun searchItems(
        page: Int,
        text: String,
        categoryId: String?
    ): Resource<List<Item>>
    suspend fun getMyItems(page: Int): Resource<List<Item>>
    suspend fun getUserItems(
        page: Int,
        id: String
    ): Resource<List<Item>>
    suspend fun getStoreItems(
        page: Int,
        id: String
    ): Resource<List<StoreItem>>
}