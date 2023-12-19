package com.darrenthiores.ecoswap.data.carbon.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class InsertCarbonReductionRequest(
    val categoryId: String,
    val taskId: String,
    val taskTitle: String,
    val total: Double
)
