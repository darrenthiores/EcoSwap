package com.darrenthiores.ecoswap.android.di.store

import com.darrenthiores.ecoswap.domain.store.repository.StoreRepository
import com.darrenthiores.ecoswap.domain.store.use_cases.GetStoreById
import com.darrenthiores.ecoswap.domain.store.use_cases.GetStores
import com.darrenthiores.ecoswap.domain.store.use_cases.SearchStores
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object StoreUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetStoresUseCase(
        repository: StoreRepository
    ): GetStores {
        return GetStores(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetStoreByIdUseCase(
        repository: StoreRepository
    ): GetStoreById {
        return GetStoreById(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideSearchStoresUseCase(
        repository: StoreRepository
    ): SearchStores {
        return SearchStores(
            repository = repository
        )
    }
}