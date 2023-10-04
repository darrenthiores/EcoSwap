package com.darrenthiores.ecoswap.android.di.core.local

import com.darrenthiores.ecoswap.android.data.core.preferences.DefaultPreferences
import com.darrenthiores.ecoswap.android.domain.core.preferences.Preferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {

    @Binds
    abstract fun providePreferences(
        preferences: DefaultPreferences
    ): Preferences
    
}