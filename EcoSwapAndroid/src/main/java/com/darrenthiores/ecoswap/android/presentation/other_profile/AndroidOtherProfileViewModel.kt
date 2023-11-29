package com.darrenthiores.ecoswap.android.presentation.other_profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.ecoswap.domain.item.use_cases.GetUserItems
import com.darrenthiores.ecoswap.domain.reviews.use_cases.AddUserReview
import com.darrenthiores.ecoswap.domain.reviews.use_cases.GetUserReviews
import com.darrenthiores.ecoswap.domain.user.use_cases.GetUserById
import com.darrenthiores.ecoswap.presentation.other_profile.OtherProfileEvent
import com.darrenthiores.ecoswap.presentation.other_profile.OtherProfileViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidOtherProfileViewModel @Inject constructor(
    private val getUserById: GetUserById,
    private val getUserItems: GetUserItems,
    private val getUserReviews: GetUserReviews,
    private val addUserReview: AddUserReview,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val viewModel by lazy {
        OtherProfileViewModel(
            getUserById = getUserById,
            getUserItems = getUserItems,
            getUserReviews = getUserReviews,
            addUserReview = addUserReview,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    val uiEvent = viewModel
        .uiEvent

    init {
        savedStateHandle.get<String>("userId")?.let { id ->
            viewModel.initialFetch(
                id = id
            )
        }
    }

    fun onEvent(event: OtherProfileEvent) {
        viewModel.onEvent(event)
    }
}