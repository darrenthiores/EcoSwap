package com.darrenthiores.ecoswap.android.presentation.challenge_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.ecoswap.domain.carbon.use_cases.GetChallengeById
import com.darrenthiores.ecoswap.domain.carbon.use_cases.JoinChallenge
import com.darrenthiores.ecoswap.presentation.challenge_detail.ChallengeDetailEvent
import com.darrenthiores.ecoswap.presentation.challenge_detail.ChallengeDetailViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidChallengeDetailViewModel @Inject constructor(
    private val getChallengeById: GetChallengeById,
    private val joinChallenge: JoinChallenge,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val viewModel by lazy {
        ChallengeDetailViewModel(
            getChallengeById = getChallengeById,
            joinChallenge = joinChallenge,
            coroutineScope = viewModelScope,
        )
    }

    val state = viewModel.state

    init {
        savedStateHandle.get<String>("challengeId")?.let { id ->
            viewModel.initChallenge(
                id = id
            )
        }
    }

    fun onEvent(event: ChallengeDetailEvent) {
        viewModel.onEvent(event)
    }
}