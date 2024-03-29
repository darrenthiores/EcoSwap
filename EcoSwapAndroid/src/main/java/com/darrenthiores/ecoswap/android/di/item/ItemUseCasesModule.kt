package com.darrenthiores.ecoswap.android.di.item

import com.darrenthiores.ecoswap.domain.item.repository.ItemRepository
import com.darrenthiores.ecoswap.domain.item.use_cases.AddItem
import com.darrenthiores.ecoswap.domain.item.use_cases.GetItemById
import com.darrenthiores.ecoswap.domain.item.use_cases.GetItems
import com.darrenthiores.ecoswap.domain.item.use_cases.GetMyItems
import com.darrenthiores.ecoswap.domain.item.use_cases.GetStoreItemById
import com.darrenthiores.ecoswap.domain.item.use_cases.GetStoreItems
import com.darrenthiores.ecoswap.domain.item.use_cases.GetUserItems
import com.darrenthiores.ecoswap.domain.item.use_cases.SearchItems
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ItemUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideAddItemUseCase(
        repository: ItemRepository
    ): AddItem {
        return AddItem(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetItemsUseCase(
        repository: ItemRepository
    ): GetItems {
        return GetItems(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetItemByIdUseCase(
        repository: ItemRepository
    ): GetItemById {
        return GetItemById(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetStoreItemByIdUseCase(
        repository: ItemRepository
    ): GetStoreItemById {
        return GetStoreItemById(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideSearchItemsUseCase(
        repository: ItemRepository
    ): SearchItems {
        return SearchItems(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetMyItemsUseCase(
        repository: ItemRepository
    ): GetMyItems {
        return GetMyItems(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetUserItemsUseCase(
        repository: ItemRepository
    ): GetUserItems {
        return GetUserItems(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetStoreItemsUseCase(
        repository: ItemRepository
    ): GetStoreItems {
        return GetStoreItems(
            repository = repository
        )
    }
}