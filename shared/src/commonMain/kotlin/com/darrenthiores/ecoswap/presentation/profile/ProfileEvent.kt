package com.darrenthiores.ecoswap.presentation.profile

sealed class ProfileEvent {
    object LoadItemNextPage: ProfileEvent()
    object LoadReviewNextPage: ProfileEvent()
}
