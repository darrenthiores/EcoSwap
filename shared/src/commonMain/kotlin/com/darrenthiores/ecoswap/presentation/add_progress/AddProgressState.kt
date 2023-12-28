package com.darrenthiores.ecoswap.presentation.add_progress

import com.darrenthiores.ecoswap.domain.carbon.model.CarbonActivity
import com.darrenthiores.ecoswap.domain.carbon.model.CarbonCategory
import com.darrenthiores.ecoswap.domain.carbon.model.Challenge
import com.darrenthiores.ecoswap.presentation.utils.PagingState

data class AddProgressState(
    val category: CarbonCategory? = null,
    val activity: CarbonActivity? = null,
    val activities: List<CarbonActivity> = emptyList(),
    val number: String = "",
    val numberError: String? = null,
    val challenges: PagingState<Challenge> = PagingState(),
    val challenge: Challenge? = null,
    val task: Challenge.Task? = null,
    val categoryDropDownOpen: Boolean = false,
    val activityDropDownOpen: Boolean = false,
    val taskDropDownOpen: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)
