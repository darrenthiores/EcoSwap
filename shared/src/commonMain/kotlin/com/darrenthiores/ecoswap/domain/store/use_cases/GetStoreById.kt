package com.darrenthiores.ecoswap.domain.store.use_cases

import com.darrenthiores.ecoswap.domain.store.model.Store
import com.darrenthiores.ecoswap.domain.store.repository.StoreRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class GetStoreById(
    private val repository: StoreRepository
) {
    suspend operator fun invoke(
        id: String
    ): Resource<Store?> {
        return repository
            .getStoreById(
                id = id
            )
    }
}