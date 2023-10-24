package com.darrenthiores.ecoswap.domain.core.utils

import com.darrenthiores.ecoswap.domain.item.model.ItemCategory
import com.darrenthiores.ecoswap.domain.item.model.ItemCondition

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

    val conditions: List<ItemCondition> = listOf(
        ItemCondition(
            id = "1",
            display = "new"
        ),
        ItemCondition(
            id = "2",
            display = "second"
        )
    )

    fun categoryById(id: String): ItemCategory? {
        return categories.firstOrNull { it.id == id }
    }

    fun conditionById(id: String): ItemCondition? {
        return conditions.firstOrNull { it.id == id }
    }
}