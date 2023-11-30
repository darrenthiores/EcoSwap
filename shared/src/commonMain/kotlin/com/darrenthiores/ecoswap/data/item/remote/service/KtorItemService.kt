package com.darrenthiores.ecoswap.data.item.remote.service

import com.darrenthiores.ecoswap.domain.core.utils.Dummy
import com.darrenthiores.ecoswap.domain.item.model.Item
import com.darrenthiores.ecoswap.domain.item.model.ItemCategory
import com.darrenthiores.ecoswap.domain.item.model.ItemCondition
import com.darrenthiores.ecoswap.domain.item.model.StoreItem
import io.ktor.client.HttpClient

class KtorItemService(
    private val client: HttpClient
): ItemService {
    override suspend fun addItem(
        photos: List<String>,
        name: String,
        description: String,
        category: ItemCategory,
        total: Int,
        condition: ItemCondition,
        brand: String,
        location: String
    ) {
        // should add item
    }

    override suspend fun getItems(page: Int): List<Item> {
        return Dummy.items
    }

    override suspend fun getItemById(id: String): Item? {
        return Dummy
            .items
            .firstOrNull { it.id == id }
    }


    override suspend fun getStoreItemById(id: String): StoreItem? {
        return Dummy
            .storeItems
            .firstOrNull { it.id == id }
    }

    override suspend fun searchItems(
        page: Int,
        text: String,
        categoryId: String?
    ): List<Item> {
        if (categoryId != null) {
            return Dummy
                .items
                .filter {
                    it.name.lowercase().contains(
                        text.lowercase()
                    ) && it.categoryId == categoryId
                }
        } else {
            return Dummy
                .items
                .filter {
                    it.name.lowercase().contains(
                        text.lowercase()
                    )
                }
        }
    }

    override suspend fun getMyItems(page: Int): List<Item> {
        return Dummy
            .items
    }

    override suspend fun getUserItems(page: Int, id: String): List<Item> {
        return Dummy
            .items
            .filter {
                it.userId == id
            }
    }

    override suspend fun getStoreItems(page: Int, id: String): List<StoreItem> {
        return Dummy
            .storeItems
            .filter {
                it.storeId == id
            }
    }

}