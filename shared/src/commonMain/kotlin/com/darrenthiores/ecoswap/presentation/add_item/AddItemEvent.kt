package com.darrenthiores.ecoswap.presentation.add_item

import com.darrenthiores.ecoswap.domain.item.model.ItemCategory
import com.darrenthiores.ecoswap.domain.item.model.ItemCondition

sealed class AddItemEvent {
    data class OnNameChange(
        val name: String
    ): AddItemEvent()
    data class OnDescriptionChange(
        val description: String
    ): AddItemEvent()
    data class OnSelectCategory(
        val category: ItemCategory
    ): AddItemEvent()
    data class OnTotalChange(
        val total: String
    ): AddItemEvent()
    data class OnSelectCondition(
        val condition: ItemCondition
    ): AddItemEvent()
    data class OnBrandChange(
        val brand: String
    ): AddItemEvent()
    data class OnLocationChange(
        val location: String
    ): AddItemEvent()
    data class Upload(
        val photos: List<String>
    ): AddItemEvent()

    object ToggleCategoryDropDown: AddItemEvent()
    object ToggleConditionDropDown: AddItemEvent()
}
