package com.darrenthiores.ecoswap.data.utils

import com.darrenthiores.ecoswap.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class FlowNetworkBoundResource<ResultType, RequestType> {
    protected open fun onFetchFailed() {}

    protected abstract suspend fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun result(): Flow<Resource<ResultType>> {
        return flow {
            emit(Resource.Loading())

            if (shouldFetch()) {
                when (val apiResponse = createCall().first()) {
                    is ApiResponse.Success -> {
                        saveCallResult(apiResponse.data)

                        emitAll(
                            loadFromDB().map {
                                Resource.Success(
                                    it
                                )
                            }
                        )
                    }
                    is ApiResponse.Empty -> {
                        emitAll(
                            loadFromDB().map {
                                Resource.Success(
                                    it
                                )
                            }
                        )
                    }
                    is ApiResponse.Error -> {
                        onFetchFailed()

                        emitAll(
                            loadFromDB().map {
                                Resource.Error(
                                    apiResponse.errorMessage,
                                    it
                                )
                            }
                        )
                    }
                }
            } else {
                emitAll(
                    loadFromDB().map {
                        Resource.Success(
                            it
                        )
                    }
                )
            }
        }
    }
}