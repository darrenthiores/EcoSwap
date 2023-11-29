package com.darrenthiores.ecoswap.android.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.ecoswap.domain.item.use_cases.GetMyItems
import com.darrenthiores.ecoswap.domain.reviews.use_cases.GetMyReviews
import com.darrenthiores.ecoswap.domain.user.use_cases.GetUser
import com.darrenthiores.ecoswap.presentation.profile.ProfileEvent
import com.darrenthiores.ecoswap.presentation.profile.ProfileViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidProfileViewModel @Inject constructor(
    private val getUser: GetUser,
    private val getMyItems: GetMyItems,
    private val getMyReviews: GetMyReviews
): ViewModel() {
    private val viewModel by lazy {
        ProfileViewModel(
            getUser = getUser,
            getItems = getMyItems,
            getReviews = getMyReviews,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: ProfileEvent) {
        viewModel.onEvent(event)
    }
}