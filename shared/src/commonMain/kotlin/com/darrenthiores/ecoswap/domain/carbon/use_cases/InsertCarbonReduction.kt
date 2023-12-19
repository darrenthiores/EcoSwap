package com.darrenthiores.ecoswap.domain.carbon.use_cases

import com.darrenthiores.ecoswap.domain.carbon.repository.CarbonRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class InsertCarbonReduction(
    private val repository: CarbonRepository
) {
    suspend operator fun invoke(
        categoryId: String,
        taskId: String,
        taskTitle: String,
        total: Double
    ): Resource<Unit> {
        return repository
            .insertCarbonReduction(
                categoryId = categoryId,
                taskId = taskId,
                taskTitle = taskTitle,
                total = total
            )
    }
}