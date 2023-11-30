package com.darrenthiores.ecoswap.data.item.remote.service

import com.darrenthiores.ecoswap.domain.item.model.Item
import com.darrenthiores.ecoswap.domain.item.model.StoreItem

interface ItemService {
    suspend fun getItems(
        page: Int
    ): List<Item>
    suspend fun getItemById(id: String): Item?

    suspend fun getStoreItemById(id: String): StoreItem?

    suspend fun searchItems(
        page: Int,
        text: String,
        categoryId: String?
    ): List<Item>

    suspend fun getMyItems(
        page: Int
    ): List<Item>

    suspend fun getUserItems(
        page: Int,
        id: String
    ): List<Item>

    suspend fun getStoreItems(
        page: Int,
        id: String
    ): List<StoreItem>

    companion object {
        private const val BASE_URL = ""
        const val GET_ITEMS_URL = "$BASE_URL/"
        const val GET_ITEM_BY_ID_URL = "$BASE_URL/"
        const val SEARCH_ITEMS_URL = "$BASE_URL/"
    }
}