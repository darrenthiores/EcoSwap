package com.darrenthiores.ecoswap.presentation.other_profile

sealed class OtherProfileEvent {
    object LoadItemNextPage: OtherProfileEvent()
    object LoadReviewNextPage: OtherProfileEvent()
    data class OnPickRating(
        val rating: Int
    ): OtherProfileEvent()
    data class OnMessageChange(
        val message: String
    ): OtherProfileEvent()
    object AddReview: OtherProfileEvent()
    object Reset: OtherProfileEvent()
    object HideError: OtherProfileEvent()
}
