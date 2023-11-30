package com.darrenthiores.ecoswap.domain.item.use_cases

import com.darrenthiores.ecoswap.domain.item.model.StoreItem
import com.darrenthiores.ecoswap.domain.item.repository.ItemRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class GetStoreItemById(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(
        id: String
    ): Resource<StoreItem?> {
        return repository
            .getStoreItemById(
                id = id
            )
    }
}