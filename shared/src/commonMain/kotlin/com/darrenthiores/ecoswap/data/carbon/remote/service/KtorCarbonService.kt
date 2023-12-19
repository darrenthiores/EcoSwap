package com.darrenthiores.ecoswap.data.carbon.remote.service

import com.darrenthiores.ecoswap.data.carbon.mapper.toFootPrint
import com.darrenthiores.ecoswap.data.carbon.remote.dto.request.GetChallengesRequest
import com.darrenthiores.ecoswap.data.carbon.remote.dto.request.IdRequest
import com.darrenthiores.ecoswap.data.carbon.remote.dto.request.InsertCarbonReductionRequest
import com.darrenthiores.ecoswap.data.carbon.remote.dto.request.ViewIdRequest
import com.darrenthiores.ecoswap.domain.carbon.model.Challenge
import com.darrenthiores.ecoswap.domain.carbon.model.FootPrint
import com.darrenthiores.ecoswap.domain.carbon.model.ReducedCarbon
import com.darrenthiores.ecoswap.domain.core.utils.Dummy
import io.ktor.client.HttpClient

class KtorCarbonService(
    private val client: HttpClient
): CarbonService  {
    override suspend fun getFootPrint(request: ViewIdRequest): FootPrint {
        return FootPrint(
            total = Dummy
                .carbonReductions
                .sumOf { it.total },
            carbons = Dummy
                .carbonReductions
                .map { it.toFootPrint() }
        )
    }

    override suspend fun getCarbonReduction(request: ViewIdRequest): List<ReducedCarbon> {
        return Dummy.carbonReductions
    }

    override suspend fun insertCarbonReduction(request: InsertCarbonReductionRequest) {
        // should insert carbon reduction
    }

    override suspend fun getChallenges(request: GetChallengesRequest, page: Int): List<Challenge> {
        return Dummy
            .challenges
            .filter {
                if (request.isJoined) {
                    it.participants.firstOrNull { participant ->
                        participant.id == "U1"
                    } != null
                } else true
            }
    }

    override suspend fun getChallengeById(request: IdRequest): Challenge? {
        return Dummy
            .challenges
            .firstOrNull { it.id == request.id }
    }
}