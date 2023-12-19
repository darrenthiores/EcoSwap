package com.darrenthiores.ecoswap.domain.carbon.use_cases

import com.darrenthiores.ecoswap.domain.carbon.model.Challenge
import com.darrenthiores.ecoswap.domain.carbon.repository.CarbonRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class GetChallenges(
    private val repository: CarbonRepository
) {
    suspend operator fun invoke(
        isJoined: Boolean,
        page: Int
    ): Resource<List<Challenge>> {
        return repository
            .getChallenges(
                isJoined = isJoined,
                page = page
            )
    }
}