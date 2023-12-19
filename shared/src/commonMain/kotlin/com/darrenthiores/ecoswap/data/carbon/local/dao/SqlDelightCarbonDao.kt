package com.darrenthiores.ecoswap.data.carbon.local.dao

import com.darrenthiores.ecoswap.database.AppDatabase
import com.darrenthiores.ecoswap.domain.carbon.model.ReducedCarbon
import com.darrenthiores.ecoswap.utils.date.DateUtils

class SqlDelightCarbonDao(
    db: AppDatabase
): CarbonDao {
    private val queries = db.carbonQueries

    override suspend fun getReducedCarbonByCategory(categoryId: String, date: Long): Double {
        val dateBefore = DateUtils.now() - date

        return queries
            .getCarbonByCategory(
                categoryId = categoryId,
                date = dateBefore
            )
            .executeAsOneOrNull()
            ?.Double ?: 0.0
    }

    override suspend fun insertCarbonReduction(
        categoryId: String,
        taskId: String,
        taskTitle: String,
        total: Double
    ) {
        queries
            .insertCarbon(
                categoryId = categoryId,
                taskId = taskId,
                taskTitle = taskTitle,
                date = DateUtils.now(),
                total = total
            )
    }

    override suspend fun insertAllCarbonReduction(items: List<ReducedCarbon>) {
        queries.transaction {
            items.forEach { item ->
                queries
                    .insertCarbon(
                        categoryId = item.categoryId,
                        taskId = item.taskId,
                        taskTitle = item.taskTitle,
                        date = item.date,
                        total = item.total
                    )
            }
        }
    }
}