package com.darrenthiores.ecoswap.data.carbon.repository

import com.darrenthiores.ecoswap.data.carbon.local.CarbonLocalDataSource
import com.darrenthiores.ecoswap.data.carbon.remote.dto.request.GetChallengesRequest
import com.darrenthiores.ecoswap.data.carbon.remote.dto.request.IdRequest
import com.darrenthiores.ecoswap.data.carbon.remote.dto.request.InsertCarbonReductionRequest
import com.darrenthiores.ecoswap.data.carbon.remote.dto.request.ViewIdRequest
import com.darrenthiores.ecoswap.data.carbon.remote.source.CarbonRemoteDataSource
import com.darrenthiores.ecoswap.data.utils.ApiResponse
import com.darrenthiores.ecoswap.domain.carbon.model.CarbonView
import com.darrenthiores.ecoswap.domain.carbon.model.Challenge
import com.darrenthiores.ecoswap.domain.carbon.model.FootPrint
import com.darrenthiores.ecoswap.domain.carbon.repository.CarbonRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class CarbonRepositoryImpl(
    private val remoteDataSource: CarbonRemoteDataSource,
    private val localDataSource: CarbonLocalDataSource
): CarbonRepository {
    override suspend fun getFootPrint(
        fetch: Boolean,
        viewId: String
    ): Resource<FootPrint> {
        return if (fetch) {
            val request = ViewIdRequest(
                viewId = viewId
            )
            val result = remoteDataSource
                .getFootPrint(
                    request = request
                )

            when (result) {
                ApiResponse.Empty -> {
                    val fromLocal = localDataSource
                        .getFootPrint(
                            carbonView = CarbonView.getById(
                                id = viewId
                            )
                        )

                    Resource.Success(fromLocal)
                }
                is ApiResponse.Error -> {
                    val fromLocal = localDataSource
                        .getFootPrint(
                            carbonView = CarbonView.getById(
                                id = viewId
                            )
                        )

                    Resource.Success(fromLocal)
                }
                is ApiResponse.Success -> {
                    val carbonReductionRequest = ViewIdRequest(
                        viewId = "3"
                    )
                    val carbonReductionResult = remoteDataSource
                        .getCarbonReduction(
                            request = carbonReductionRequest
                        )

                    when (carbonReductionResult) {
                        ApiResponse.Empty -> {
                            Resource.Success(result.data)
                        }
                        is ApiResponse.Error -> {
                            Resource.Success(result.data)
                        }
                        is ApiResponse.Success -> {
                            localDataSource
                                .saveFootPrint(
                                    items = carbonReductionResult.data
                                )

                            val fromLocal = localDataSource
                                .getFootPrint(
                                    carbonView = CarbonView.getById(
                                        id = viewId
                                    )
                                )

                            Resource.Success(fromLocal)
                        }
                    }
                }
            }
        } else {
            val fromLocal = localDataSource
                .getFootPrint(
                    carbonView = CarbonView.getById(
                        id = viewId
                    )
                )

            Resource.Success(fromLocal)
        }
    }

    override suspend fun insertCarbonReduction(
        categoryId: String,
        taskId: String,
        taskTitle: String,
        total: Double,
    ): Resource<Unit> {
        val request = InsertCarbonReductionRequest(
            categoryId = categoryId,
            taskId = taskId,
            taskTitle = taskTitle,
            total = total
        )
        val result = remoteDataSource
            .insertCarbonReduction(
                request = request
            )

        return when (result) {
            ApiResponse.Empty -> {
                Resource.Error("Unknown Error Just Occurred!")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                localDataSource
                    .insertReducedCarbon(
                        categoryId = categoryId,
                        taskId = taskId,
                        taskTitle = taskTitle,
                        total = total
                    )

                Resource.Success(Unit)
            }
        }
    }

    override suspend fun getChallenges(isJoined: Boolean, page: Int): Resource<List<Challenge>> {
        val request = GetChallengesRequest(
            isJoined = isJoined
        )
        val result = remoteDataSource
            .getChallenges(
                request = request,
                page = page
            )

        return when (result) {
            ApiResponse.Empty -> {
                Resource.Error("Unknown Error Just Occurred!")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(result.data)
            }
        }
    }

    override suspend fun getChallengeById(challengeId: String): Resource<Challenge> {
        val request = IdRequest(
            id = challengeId
        )
        val result = remoteDataSource
            .getChallengeById(
                request = request
            )

        return when (result) {
            ApiResponse.Empty -> {
                Resource.Error("Unknown Error Just Occurred!")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(result.data)
            }
        }
    }
}