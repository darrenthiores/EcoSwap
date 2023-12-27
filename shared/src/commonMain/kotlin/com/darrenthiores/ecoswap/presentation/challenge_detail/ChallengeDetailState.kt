package com.darrenthiores.ecoswap.presentation.challenge_detail

import com.darrenthiores.ecoswap.domain.carbon.model.Challenge

data class ChallengeDetailState(
    val challenge: Challenge? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)
