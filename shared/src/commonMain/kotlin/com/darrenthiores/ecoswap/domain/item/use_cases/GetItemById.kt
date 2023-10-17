package com.darrenthiores.ecoswap.domain.item.use_cases

import com.darrenthiores.ecoswap.domain.item.model.Item
import com.darrenthiores.ecoswap.domain.item.repository.ItemRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class GetItemById(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(
        id: String
    ): Resource<Item?> {
        return repository
            .getItemById(
                id = id
            )
    }
}