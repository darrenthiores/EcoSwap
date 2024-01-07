package com.darrenthiores.ecoswap.domain.core.utils

import com.darrenthiores.ecoswap.domain.carbon.model.CarbonActivity
import com.darrenthiores.ecoswap.domain.carbon.model.CarbonCategory
import com.darrenthiores.ecoswap.domain.item.model.ItemCategory
import com.darrenthiores.ecoswap.domain.item.model.ItemCondition

object Constant {
    val categories: List<ItemCategory> = listOf(
        ItemCategory(
            id = "1",
            display = "Clothing"
        ),
        ItemCategory(
            id = "2",
            display = "Electronics"
        ),
        ItemCategory(
            id = "3",
            display = "Books"
        ),
        ItemCategory(
            id = "4",
            display = "Furniture"
        )
    )

    val conditions: List<ItemCondition> = listOf(
        ItemCondition(
            id = "1",
            display = "new"
        ),
        ItemCondition(
            id = "2",
            display = "second"
        )
    )

    val carbonCategories: List<CarbonCategory> = listOf(
        CarbonCategory(
            id = "1",
            display = "Transport"
        ),
        CarbonCategory(
            id = "2",
            display = "Energy Consumption"
        ),
        CarbonCategory(
            id = "3",
            display = "Challenge"
        )
    )

    val transportActivities: List<CarbonActivity> = listOf(
        CarbonActivity(
            id = "1",
            display = "Walking",
            carbon = 100.0
        ),
        CarbonActivity(
            id = "2",
            display = "Public Transport (Bus)",
            carbon = 50.0
        ),
        CarbonActivity(
            id = "3",
            display = "Public Transport (Electric Bus)",
            carbon = 80.0
        ),
        CarbonActivity(
            id = "4",
            display = "Public Transport (Train)",
            carbon = 30.0
        ),
        CarbonActivity(
            id = "5",
            display = "Public Transport (Electric Train)",
            carbon = 90.0
        ),
        CarbonActivity(
            id = "6",
            display = "Bike",
            carbon = 100.0
        ),
        CarbonActivity(
            id = "7",
            display = "Electric Car",
            carbon = 70.0
        )
    )

    val energyActivities: List<CarbonActivity> = listOf(
        CarbonActivity(
            id = "1",
            display = "Switch Off Lights",
            carbon = 30.0
        ),
        CarbonActivity(
            id = "2",
            display = "Unplug Unused Electronics",
            carbon = 20.0
        )
    )

    fun categoryById(id: String): ItemCategory? {
        return categories.firstOrNull { it.id == id }
    }

    fun conditionById(id: String): ItemCondition? {
        return conditions.firstOrNull { it.id == id }
    }

    fun carbonCategoryById(id: String): CarbonCategory? {
        return carbonCategories.firstOrNull { it.id == id }
    }

    fun getActivitiesByCategoryId(id: String): List<CarbonActivity> {
        return when(id) {
            "1" -> transportActivities
            "2" -> energyActivities
            else -> emptyList()
        }
    }
}