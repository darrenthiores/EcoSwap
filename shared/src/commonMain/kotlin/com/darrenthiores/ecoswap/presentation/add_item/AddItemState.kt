package com.darrenthiores.ecoswap.presentation.add_item

import com.darrenthiores.ecoswap.domain.item.model.ItemCategory
import com.darrenthiores.ecoswap.domain.item.model.ItemCondition

data class AddItemState(
    val name: String = "",
    val description: String = "",
    val category: ItemCategory? = null,
    val total: String = "",
    val condition: ItemCondition? = null,
    val brand: String = "",
    val location: String = "",
    val categoryDropDownOpen: Boolean = false,
    val conditionDropDownOpen: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)
