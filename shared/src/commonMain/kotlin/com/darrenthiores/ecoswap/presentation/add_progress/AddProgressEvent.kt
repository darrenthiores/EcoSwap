package com.darrenthiores.ecoswap.presentation.add_progress

import com.darrenthiores.ecoswap.domain.carbon.model.CarbonActivity
import com.darrenthiores.ecoswap.domain.carbon.model.CarbonCategory
import com.darrenthiores.ecoswap.domain.carbon.model.Challenge

sealed class AddProgressEvent {
    data class OnSelectCategory(
        val category: CarbonCategory
    ): AddProgressEvent()
    data class OnSelectActivity(
        val activity: CarbonActivity
    ): AddProgressEvent()
    object LoadChallengeNextPage: AddProgressEvent()
    data class OnSelectChallenge(
        val challenge: Challenge
    ): AddProgressEvent()
    data class OnSelectTask(
        val task: Challenge.Task
    ): AddProgressEvent()
    data class OnNumberChange(
        val number: String
    ): AddProgressEvent()
    object ToggleCategoryDropDown: AddProgressEvent()
    object ToggleActivityDropDown: AddProgressEvent()
    object ToggleTaskDropDown: AddProgressEvent()
    object Upload: AddProgressEvent()
}
