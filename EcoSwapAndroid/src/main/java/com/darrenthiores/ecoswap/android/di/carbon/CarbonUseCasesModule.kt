package com.darrenthiores.ecoswap.android.di.carbon

import com.darrenthiores.ecoswap.domain.carbon.repository.CarbonRepository
import com.darrenthiores.ecoswap.domain.carbon.use_cases.GetChallengeById
import com.darrenthiores.ecoswap.domain.carbon.use_cases.GetChallenges
import com.darrenthiores.ecoswap.domain.carbon.use_cases.GetFootPrint
import com.darrenthiores.ecoswap.domain.carbon.use_cases.InsertCarbonReduction
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CarbonUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetFootPrintUseCase(
        repository: CarbonRepository
    ): GetFootPrint {
        return GetFootPrint(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideInsertCarbonReductionUseCase(
        repository: CarbonRepository
    ): InsertCarbonReduction {
        return InsertCarbonReduction(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetChallengesUseCase(
        repository: CarbonRepository
    ): GetChallenges {
        return GetChallenges(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetChallengeByIdUseCase(
        repository: CarbonRepository
    ): GetChallengeById {
        return GetChallengeById(
            repository = repository
        )
    }
}