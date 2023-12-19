package com.darrenthiores.ecoswap.data.carbon.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class GetChallengesRequest(
    val isJoined: Boolean
)
