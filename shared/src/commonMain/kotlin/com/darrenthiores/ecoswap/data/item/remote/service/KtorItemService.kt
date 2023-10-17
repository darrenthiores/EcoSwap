package com.darrenthiores.ecoswap.data.item.remote.service

import com.darrenthiores.ecoswap.domain.core.utils.Dummy
import com.darrenthiores.ecoswap.domain.item.model.Item
import io.ktor.client.HttpClient

class KtorItemService(
    private val client: HttpClient
): ItemService {
    override suspend fun getItems(page: Int): List<Item> {
        return Dummy.items
    }

    override suspend fun getItemById(id: String): Item? {
        return Dummy
            .items
            .firstOrNull { it.id == id }
    }

    override suspend fun searchItems(
        page: Int,
        text: String,
        categoryId: String?
    ): List<Item> {
        return Dummy
            .items
            .filter {
                it.name.lowercase().contains(
                    text.lowercase()
                ) && it.categoryId == categoryId
            }
    }

}