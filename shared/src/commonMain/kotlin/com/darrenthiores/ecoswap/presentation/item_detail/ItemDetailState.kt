package com.darrenthiores.ecoswap.presentation.item_detail

import com.darrenthiores.ecoswap.domain.item.model.Item
import com.darrenthiores.ecoswap.domain.user.model.User

data class ItemDetailState(
    val item: Item? = null,
    val user: User? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isExpand: Boolean = false
)
