package com.darrenthiores.ecoswap.domain.core.utils

import com.darrenthiores.ecoswap.domain.item.model.ItemCategory

object Constant {
    val categories: List<ItemCategory> = listOf(
        ItemCategory(
            id = "1",
            display = "Clothing"
        ),
        ItemCategory(
            id = "2",
            display = "Electronics"
        ),
        ItemCategory(
            id = "3",
            display = "Books"
        ),
        ItemCategory(
            id = "4",
            display = "Furniture"
        )
    )

    fun categoryById(id: String): ItemCategory? {
        return categories.firstOrNull { it.id == id }
    }
}