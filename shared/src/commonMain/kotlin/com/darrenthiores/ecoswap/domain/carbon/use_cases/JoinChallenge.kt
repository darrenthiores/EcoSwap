package com.darrenthiores.ecoswap.domain.carbon.use_cases

import com.darrenthiores.ecoswap.domain.carbon.repository.CarbonRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class JoinChallenge(
    private val repository: CarbonRepository
) {
    suspend operator fun invoke(
        challengeId: String
    ): Resource<Unit> {
        return repository
            .joinChallenge(
                challengeId = challengeId
            )
    }
}