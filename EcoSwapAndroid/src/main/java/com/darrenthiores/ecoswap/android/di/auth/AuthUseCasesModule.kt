package com.darrenthiores.ecoswap.android.di.auth

import com.darrenthiores.ecoswap.domain.auth.repository.AuthRepository
import com.darrenthiores.ecoswap.domain.auth.use_cases.Login
import com.darrenthiores.ecoswap.domain.auth.use_cases.Register
import com.darrenthiores.ecoswap.domain.auth.use_cases.ValidateEmail
import com.darrenthiores.ecoswap.domain.auth.use_cases.ValidatePassword
import com.darrenthiores.ecoswap.domain.core.preferences.TokenPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AuthUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideLoginUseCase(
        repository: AuthRepository,
        tokenPreferences: TokenPreferences
    ): Login {
        return Login(
            repository = repository,
            tokenPreferences = tokenPreferences
        )
    }

    @Provides
    @ViewModelScoped
    fun provideRegisterUseCase(
        repository: AuthRepository,
        tokenPreferences: TokenPreferences
    ): Register {
        return Register(
            repository = repository,
            tokenPreferences = tokenPreferences
        )
    }

    @Provides
    @ViewModelScoped
    fun provideValidateEmailUseCase(): ValidateEmail {
        return ValidateEmail()
    }

    @Provides
    @ViewModelScoped
    fun provideValidatePasswordUseCase(): ValidatePassword {
        return ValidatePassword()
    }
}