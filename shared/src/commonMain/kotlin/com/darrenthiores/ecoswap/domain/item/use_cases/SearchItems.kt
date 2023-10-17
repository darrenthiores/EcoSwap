package com.darrenthiores.ecoswap.domain.item.use_cases

import com.darrenthiores.ecoswap.domain.item.model.Item
import com.darrenthiores.ecoswap.domain.item.repository.ItemRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class SearchItems(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(
        page: Int,
        text: String,
        categoryId: String?
    ): Resource<List<Item>> {
        return repository
            .searchItems(
                page = page,
                text = text,
                categoryId = categoryId
            )
    }
}