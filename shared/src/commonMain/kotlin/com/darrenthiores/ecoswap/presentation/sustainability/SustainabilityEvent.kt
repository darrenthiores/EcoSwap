package com.darrenthiores.ecoswap.presentation.sustainability

import com.darrenthiores.ecoswap.domain.carbon.model.CarbonView

sealed class SustainabilityEvent {
    data class OnViewModeClick(
        val mode: CarbonView
    ): SustainabilityEvent()

    object LoadChallengeNextPage: SustainabilityEvent()

    data class UpdateFootPrint(
        val categoryId: String,
        val total: Double
    ): SustainabilityEvent()
    object Refresh: SustainabilityEvent()
}
