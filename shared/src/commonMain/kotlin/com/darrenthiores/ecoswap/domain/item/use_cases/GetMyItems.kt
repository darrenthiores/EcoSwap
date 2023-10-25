package com.darrenthiores.ecoswap.domain.item.use_cases

import com.darrenthiores.ecoswap.domain.item.model.Item
import com.darrenthiores.ecoswap.domain.item.repository.ItemRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class GetMyItems(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(
        page: Int
    ): Resource<List<Item>> {
        return repository
            .getMyItems(
                page = page
            )
    }
}