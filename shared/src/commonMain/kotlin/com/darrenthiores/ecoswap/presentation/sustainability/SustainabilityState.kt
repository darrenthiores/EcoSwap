package com.darrenthiores.ecoswap.presentation.sustainability

import com.darrenthiores.ecoswap.domain.carbon.model.CarbonView
import com.darrenthiores.ecoswap.domain.carbon.model.Challenge
import com.darrenthiores.ecoswap.domain.carbon.model.FootPrint
import com.darrenthiores.ecoswap.presentation.utils.PagingState

data class SustainabilityState(
    val footPrint: FootPrint? = null,
    val challenges: PagingState<Challenge> = PagingState(),
    val error: String? = null,
    val isLoading: Boolean = false,
    val viewMode: CarbonView = CarbonView.Daily
)
