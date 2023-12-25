package com.darrenthiores.ecoswap.android.presentation.sustainability

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.ecoswap.domain.carbon.use_cases.GetChallenges
import com.darrenthiores.ecoswap.domain.carbon.use_cases.GetFootPrint
import com.darrenthiores.ecoswap.presentation.sustainability.SustainabilityEvent
import com.darrenthiores.ecoswap.presentation.sustainability.SustainabilityViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidSustainabilityViewModel @Inject constructor(
    private val getFootPrint: GetFootPrint,
    private val getChallenges: GetChallenges
): ViewModel() {
    private val viewModel by lazy {
        SustainabilityViewModel(
            getFootPrint = getFootPrint,
            getChallenges = getChallenges,
            coroutineScope = viewModelScope,
        )
    }

    val state = viewModel.state

    fun onEvent(event: SustainabilityEvent) {
        viewModel.onEvent(event)
    }
}