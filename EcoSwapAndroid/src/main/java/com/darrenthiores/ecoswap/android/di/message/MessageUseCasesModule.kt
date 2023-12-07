package com.darrenthiores.ecoswap.android.di.message

import com.darrenthiores.ecoswap.domain.message.repository.MessageRepository
import com.darrenthiores.ecoswap.domain.message.use_cases.GetInbox
import com.darrenthiores.ecoswap.domain.message.use_cases.GetMessagesById
import com.darrenthiores.ecoswap.domain.message.use_cases.GetUnreadCount
import com.darrenthiores.ecoswap.domain.message.use_cases.InsertMessage
import com.darrenthiores.ecoswap.domain.message.use_cases.UpdateInbox
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MessageUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetInboxUseCase(
        repository: MessageRepository
    ): GetInbox {
        return GetInbox(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetMessagesByIdUseCase(
        repository: MessageRepository
    ): GetMessagesById {
        return GetMessagesById(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetUnreadCountUseCase(
        repository: MessageRepository
    ): GetUnreadCount {
        return GetUnreadCount(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideInsertMessageUseCase(
        repository: MessageRepository
    ): InsertMessage {
        return InsertMessage(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideUpdateInboxUseCase(
        repository: MessageRepository
    ): UpdateInbox {
        return UpdateInbox(
            repository = repository
        )
    }
}