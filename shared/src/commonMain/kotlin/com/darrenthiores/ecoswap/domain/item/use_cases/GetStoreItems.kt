package com.darrenthiores.ecoswap.domain.item.use_cases

import com.darrenthiores.ecoswap.domain.item.model.StoreItem
import com.darrenthiores.ecoswap.domain.item.repository.ItemRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class GetStoreItems(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(
        page: Int,
        id: String
    ): Resource<List<StoreItem>> {
        return repository
            .getStoreItems(
                page = page,
                id = id
            )
    }
}