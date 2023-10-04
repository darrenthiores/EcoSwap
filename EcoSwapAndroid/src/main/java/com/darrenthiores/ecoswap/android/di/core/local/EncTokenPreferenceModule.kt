package com.darrenthiores.ecoswap.android.di.core.local

import com.darrenthiores.ecoswap.android.data.core.preferences.EncTokenPreferences
import com.darrenthiores.ecoswap.data.core.local.TokenPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class EncTokenPreferenceModule {

    @Binds
    abstract fun providePreferences(
        preferences: EncTokenPreferences
    ): TokenPreferences
}