package com.darrenthiores.ecoswap.domain.store.use_cases

import com.darrenthiores.ecoswap.domain.store.model.Store
import com.darrenthiores.ecoswap.domain.store.repository.StoreRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class GetStores(
    private val repository: StoreRepository
) {
    suspend operator fun invoke(
        page: Int
    ): Resource<List<Store>> {
        return repository
            .getStores(
                page = page
            )
    }
}