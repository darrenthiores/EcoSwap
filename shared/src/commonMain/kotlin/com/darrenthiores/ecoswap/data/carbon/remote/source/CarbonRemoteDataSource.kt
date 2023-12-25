package com.darrenthiores.ecoswap.data.carbon.remote.source

import com.darrenthiores.ecoswap.data.carbon.remote.dto.request.GetChallengesRequest
import com.darrenthiores.ecoswap.data.carbon.remote.dto.request.IdRequest
import com.darrenthiores.ecoswap.data.carbon.remote.dto.request.InsertCarbonReductionRequest
import com.darrenthiores.ecoswap.data.carbon.remote.dto.request.ViewIdRequest
import com.darrenthiores.ecoswap.data.carbon.remote.service.CarbonService
import com.darrenthiores.ecoswap.data.utils.ApiResponse
import com.darrenthiores.ecoswap.data.utils.tryCatch
import com.darrenthiores.ecoswap.domain.carbon.model.Challenge
import com.darrenthiores.ecoswap.domain.carbon.model.FootPrint
import com.darrenthiores.ecoswap.domain.carbon.model.ReducedCarbon
import com.darrenthiores.ecoswap.utils.dispatchers.DispatchersProvider
import kotlinx.coroutines.withContext

class CarbonRemoteDataSource(
    private val apiService: CarbonService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun getFootPrint(
        request: ViewIdRequest
    ): ApiResponse<FootPrint> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getFootPrint(
                    request = request
                )

                ApiResponse.Success(result)
            }
        }
    }

    suspend fun getCarbonReduction(
        request: ViewIdRequest
    ): ApiResponse<List<ReducedCarbon>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getCarbonReduction(
                    request = request
                )

                ApiResponse.Success(result)
            }
        }
    }

    suspend fun insertCarbonReduction(
        request: InsertCarbonReductionRequest
    ): ApiResponse<Unit> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.insertCarbonReduction(
                    request = request
                )

                ApiResponse.Success(result)
            }
        }
    }

    suspend fun getChallenges(
        request: GetChallengesRequest,
        page: Int
    ): ApiResponse<List<Challenge>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getChallenges(
                    request = request,
                    page = page
                )

                ApiResponse.Success(result)
            }
        }
    }

    suspend fun getChallengeById(
        request: IdRequest
    ): ApiResponse<Challenge> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getChallengeById(
                    request = request
                )

                if (result == null) {
                    ApiResponse.Error("Unknown Error Just Occurred!")
                } else {
                    ApiResponse.Success(result)
                }
            }
        }
    }

    suspend fun joinChallenge(
        request: IdRequest
    ): ApiResponse<Unit> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.joinChallenge(
                    request = request
                )

                ApiResponse.Success(result)
            }
        }
    }
}