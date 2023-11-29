package com.darrenthiores.ecoswap.presentation.store_profile

sealed class StoreProfileEvent {
    object LoadItemNextPage: StoreProfileEvent()
    object LoadReviewNextPage: StoreProfileEvent()
    data class OnPickRating(
        val rating: Int
    ): StoreProfileEvent()
    data class OnMessageChange(
        val message: String
    ): StoreProfileEvent()
    object AddReview: StoreProfileEvent()
    object Reset: StoreProfileEvent()
    object HideError: StoreProfileEvent()
}
