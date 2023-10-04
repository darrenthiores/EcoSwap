package com.darrenthiores.ecoswap.android.presentation.boarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.ecoswap.android.domain.core.preferences.Preferences
import com.darrenthiores.ecoswap.domain.utils.UiEvent
import com.darrenthiores.ecoswap.presentation.boarding.BoardingEvent
import com.darrenthiores.ecoswap.presentation.boarding.BoardingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AndroidBoardingViewModel @Inject constructor(
    private val preferences: Preferences
): ViewModel() {
    private val viewModel by lazy {
        BoardingViewModel(
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    val uiEvent = viewModel
        .uiEvent
        .onEach {
            if (it == UiEvent.Success) {
                preferences.saveShouldShowOnBoarding(
                    shouldShow = false
                )
            }
        }

    fun onEvent(event: BoardingEvent) {
        viewModel.onEvent(event)
    }
}