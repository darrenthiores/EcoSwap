package com.darrenthiores.ecoswap.android.presentation.store_profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.ecoswap.domain.item.use_cases.GetStoreItems
import com.darrenthiores.ecoswap.domain.reviews.use_cases.AddStoreReview
import com.darrenthiores.ecoswap.domain.reviews.use_cases.GetStoreReviews
import com.darrenthiores.ecoswap.domain.store.use_cases.GetStoreById
import com.darrenthiores.ecoswap.presentation.store_profile.StoreProfileEvent
import com.darrenthiores.ecoswap.presentation.store_profile.StoreProfileViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidStoreProfileViewModel @Inject constructor(
    private val getStoreById: GetStoreById,
    private val getStoreItems: GetStoreItems,
    private val getStoreReviews: GetStoreReviews,
    private val addStoreReview: AddStoreReview,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val viewModel by lazy {
        StoreProfileViewModel(
            getStoreById = getStoreById,
            getStoreItems = getStoreItems,
            getStoreReviews = getStoreReviews,
            addStoreReview = addStoreReview,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    val uiEvent = viewModel
        .uiEvent

    init {
        savedStateHandle.get<String>("storeId")?.let { id ->
            viewModel.initialFetch(
                id = id
            )
        }
    }

    fun onEvent(event: StoreProfileEvent) {
        viewModel.onEvent(event)
    }
}