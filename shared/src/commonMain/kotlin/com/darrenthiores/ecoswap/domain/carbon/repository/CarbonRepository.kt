package com.darrenthiores.ecoswap.domain.carbon.repository

import com.darrenthiores.ecoswap.domain.carbon.model.Challenge
import com.darrenthiores.ecoswap.domain.carbon.model.FootPrint
import com.darrenthiores.ecoswap.domain.utils.Resource

interface CarbonRepository {
    suspend fun getFootPrint(
        fetch: Boolean = false,
        viewId: String
    ): Resource<FootPrint>

    suspend fun insertCarbonReduction(
        categoryId: String,
        taskId: String,
        taskTitle: String,
        total: Double
    ): Resource<Unit>

    suspend fun getChallenges(
        isJoined: Boolean,
        page: Int
    ): Resource<List<Challenge>>

    suspend fun getChallengeById(
        challengeId: String
    ): Resource<Challenge>

    suspend fun joinChallenge(
        challengeId: String
    ): Resource<Unit>
}