package com.darrenthiores.ecoswap.data.carbon.remote.service

import com.darrenthiores.ecoswap.data.carbon.remote.dto.request.GetChallengesRequest
import com.darrenthiores.ecoswap.data.carbon.remote.dto.request.IdRequest
import com.darrenthiores.ecoswap.data.carbon.remote.dto.request.InsertCarbonReductionRequest
import com.darrenthiores.ecoswap.data.carbon.remote.dto.request.ViewIdRequest
import com.darrenthiores.ecoswap.domain.carbon.model.Challenge
import com.darrenthiores.ecoswap.domain.carbon.model.FootPrint
import com.darrenthiores.ecoswap.domain.carbon.model.ReducedCarbon

interface CarbonService {
    suspend fun getFootPrint(
        request: ViewIdRequest
    ): FootPrint

    suspend fun getCarbonReduction(
        request: ViewIdRequest
    ): List<ReducedCarbon>

    suspend fun insertCarbonReduction(
        request: InsertCarbonReductionRequest
    )

    suspend fun getChallenges(
        request: GetChallengesRequest,
        page: Int
    ): List<Challenge>

    suspend fun getChallengeById(
        request: IdRequest
    ): Challenge?

    suspend fun joinChallenge(
        request: IdRequest
    )

    companion object {
        private const val BASE_URL = ""
        const val GET_FOOTPRINT_URL = "$BASE_URL/"
        const val GET_CARBON_REDUCTION_URL = "$BASE_URL/"
        const val INSERT_CARBON_REDUCTION_URL = "$BASE_URL/"
        const val GET_CHALLENGES_URL = "$BASE_URL/"
        const val GET_CHALLENGE_BY_ID_URL = "$BASE_URL/"
        const val JOIN_CHALLENGE_URL = "$BASE_URL/"
    }
}