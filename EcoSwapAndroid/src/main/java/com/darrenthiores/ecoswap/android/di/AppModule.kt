package com.darrenthiores.ecoswap.android.di

import com.darrenthiores.ecoswap.utils.dispatchers.DispatchersProvider
import com.darrenthiores.ecoswap.utils.dispatchers.StandardDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDispatchers(): DispatchersProvider {
        return StandardDispatchers()
    }
}