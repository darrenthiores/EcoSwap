package com.darrenthiores.ecoswap.data.utils

import com.darrenthiores.ecoswap.utils.dispatchers.DispatchersProvider
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T> flowResponse(
    dispatcher: DispatchersProvider,
    httpCall: suspend () -> ApiResponse<T>
): Flow<ApiResponse<T>> = flow {
    try {
        emit(httpCall())
    } catch (e: RedirectResponseException) {
        emit(ApiResponse.Error("Error: ${e.response.status.description}"))
    } catch (e: ClientRequestException) {
        emit(ApiResponse.Error("Error: ${e.response.status.description}"))
    } catch (e: ServerResponseException) {
        emit(ApiResponse.Error("Error: ${e.response.status.description}"))
    } catch (e: Exception) {
        emit(ApiResponse.Error("Error: ${e.message}"))
    }
}.flowOn(dispatcher.io)

suspend fun <T> tryCatch(
    httpCall: suspend () -> ApiResponse<T>
): ApiResponse<T> =
    try {
        httpCall()
    } catch (e: RedirectResponseException) {
        ApiResponse.Error("Error: ${e.response.status.description}")
    } catch (e: ClientRequestException) {
        ApiResponse.Error("Error: ${e.response.status.description}")
    } catch (e: ServerResponseException) {
        ApiResponse.Error("Error: ${e.response.status.description}")
    } catch (e: Exception) {
        ApiResponse.Error("Error: ${e.message}")
    }