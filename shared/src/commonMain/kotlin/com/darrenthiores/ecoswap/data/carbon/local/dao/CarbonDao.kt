package com.darrenthiores.ecoswap.data.carbon.local.dao

import com.darrenthiores.ecoswap.domain.carbon.model.ReducedCarbon

interface CarbonDao {
    suspend fun getReducedCarbonByCategory(
        categoryId: String,
        date: Long
    ): Double

    suspend fun insertCarbonReduction(
        categoryId: String,
        taskId: String,
        taskTitle: String,
        total: Double
    )

    suspend fun insertAllCarbonReduction(
        items: List<ReducedCarbon>
    )
}