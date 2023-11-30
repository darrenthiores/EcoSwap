package com.darrenthiores.ecoswap.domain.item.use_cases

import com.darrenthiores.ecoswap.domain.item.model.ItemCategory
import com.darrenthiores.ecoswap.domain.item.model.ItemCondition
import com.darrenthiores.ecoswap.domain.item.repository.ItemRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class AddItem(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(
        photos: List<String>,
        name: String,
        description: String,
        category: ItemCategory,
        total: Int,
        condition: ItemCondition,
        brand: String,
        location: String,
    ): Resource<Unit> {
        return repository
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
    }
}