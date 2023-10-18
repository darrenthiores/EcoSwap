package com.darrenthiores.ecoswap.presentation.home

import com.darrenthiores.ecoswap.domain.item.model.Item
import com.darrenthiores.ecoswap.domain.store.model.Store

data class HomeState(
    val recommendations: List<Item> = emptyList(),
    val recommendationError: String? = null,
    val isRecommendationLoading: Boolean = false,
    val nearby: List<Item> = emptyList(),
    val nearbyError: String? = null,
    val isNearbyLoading: Boolean = false,
    val stores: List<Store> = emptyList(),
    val storeError: String? = null,
    val isStoreLoading: Boolean = false,
    val viewAll: Boolean = false
)
