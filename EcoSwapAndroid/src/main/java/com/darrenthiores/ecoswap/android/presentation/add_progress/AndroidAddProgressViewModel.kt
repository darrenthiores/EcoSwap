package com.darrenthiores.ecoswap.android.presentation.add_progress

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.ecoswap.domain.carbon.use_cases.GetChallengeById
import com.darrenthiores.ecoswap.domain.carbon.use_cases.GetChallenges
import com.darrenthiores.ecoswap.domain.carbon.use_cases.InsertCarbonReduction
import com.darrenthiores.ecoswap.presentation.add_progress.AddProgressEvent
import com.darrenthiores.ecoswap.presentation.add_progress.AddProgressViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidAddProgressViewModel @Inject constructor(
    private val addProgress: InsertCarbonReduction,
    private val getChallenges: GetChallenges,
    private val getChallengeById: GetChallengeById,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val viewModel by lazy {
        AddProgressViewModel(
            addProgress = addProgress,
            getChallenges = getChallenges,
            getChallengeById = getChallengeById,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    val uiEvent = viewModel
        .uiEvent

    init {
        savedStateHandle.get<String>("challengeId")?.let { id ->
            viewModel.onEvent(
                event = AddProgressEvent.InitByChallenge(
                    challengeId = id
                )
            )
        }
    }

    fun onEvent(event: AddProgressEvent) {
        viewModel.onEvent(event)
    }
}