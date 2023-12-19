package com.darrenthiores.ecoswap.domain.carbon.use_cases

import com.darrenthiores.ecoswap.domain.carbon.model.Challenge
import com.darrenthiores.ecoswap.domain.carbon.repository.CarbonRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class GetChallengeById(
    private val repository: CarbonRepository
) {
    suspend operator fun invoke(
        challengeId: String
    ): Resource<Challenge> {
        return repository
            .getChallengeById(
                challengeId = challengeId
            )
    }
}