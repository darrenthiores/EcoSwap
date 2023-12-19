package com.darrenthiores.ecoswap.data.carbon.local

import com.darrenthiores.ecoswap.data.carbon.local.dao.CarbonDao
import com.darrenthiores.ecoswap.domain.carbon.model.CarbonView
import com.darrenthiores.ecoswap.domain.carbon.model.FootPrint
import com.darrenthiores.ecoswap.domain.carbon.model.ReducedCarbon
import com.darrenthiores.ecoswap.domain.core.utils.Constant

class CarbonLocalDataSource(
    private val dao: CarbonDao
) {
    suspend fun getFootPrint(
        carbonView: CarbonView
    ): FootPrint {
        val date: Long = when(carbonView) {
            CarbonView.Daily -> 86400000
            CarbonView.Weekly -> 604800000
            CarbonView.Monthly -> 2592000000
        }

         val carbons = Constant.carbonCategories.map {
             FootPrint.Carbon(
                 categoryId = it.id,
                 total = dao
                     .getReducedCarbonByCategory(
                         categoryId = it.id,
                         date = date
                     )
             )
         }

        return FootPrint(
            total = carbons.sumOf { it.total },
            carbons = carbons
        )
    }

    suspend fun insertReducedCarbon(
        categoryId: String,
        taskId: String,
        taskTitle: String,
        total: Double
    ) {
        dao
            .insertCarbonReduction(
                categoryId = categoryId,
                taskId = taskId,
                taskTitle = taskTitle,
                total = total
            )
    }

    suspend fun saveFootPrint(
        items: List<ReducedCarbon>
    ) {
        dao
            .insertAllCarbonReduction(
                items = items
            )
    }
}