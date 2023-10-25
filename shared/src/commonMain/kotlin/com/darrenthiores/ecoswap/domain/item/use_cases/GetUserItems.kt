package com.darrenthiores.ecoswap.domain.item.use_cases

import com.darrenthiores.ecoswap.domain.item.model.Item
import com.darrenthiores.ecoswap.domain.item.repository.ItemRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class GetUserItems(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(
        page: Int,
        id: String
    ): Resource<List<Item>> {
        return repository
            .getUserItems(
                page = page,
                id = id
            )
    }
}