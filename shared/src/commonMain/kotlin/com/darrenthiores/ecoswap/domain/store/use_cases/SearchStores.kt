package com.darrenthiores.ecoswap.domain.store.use_cases

import com.darrenthiores.ecoswap.domain.store.model.Store
import com.darrenthiores.ecoswap.domain.store.repository.StoreRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class SearchStores(
    private val repository: StoreRepository
) {
    suspend operator fun invoke(
        page: Int,
        text: String,
        categoryId: String?
    ): Resource<List<Store>> {
        return repository
            .searchStores(
                page = page,
                text = text,
                categoryId = categoryId
            )
    }
}