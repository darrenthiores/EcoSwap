package com.darrenthiores.ecoswap.data.utils

import com.darrenthiores.ecoswap.domain.utils.Resource

abstract class NetworkBoundResource<ResultType, RequestType> {
    protected open fun onFetchFailed() {}

    protected abstract suspend fun loadFromDB(): ResultType

    protected abstract fun shouldFetch(): Boolean

    protected abstract suspend fun createCall(): ApiResponse<RequestType>

    protected abstract suspend fun saveCallResult(data: RequestType)

    suspend fun result(): Resource<ResultType> {
        return if (shouldFetch()) {
            when (val apiResponse = createCall()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)

                    Resource.Success(
                       loadFromDB()
                    )
                }
                is ApiResponse.Empty -> {
                    Resource.Success(
                        loadFromDB()
                    )
                }
                is ApiResponse.Error -> {
                    onFetchFailed()

                    Resource.Success(
                        loadFromDB()
                    )
                }
            }
        } else {
            Resource.Success(
                loadFromDB()
            )
        }
    }
}