package com.darrenthiores.ecoswap.presentation.challenge_detail

import com.darrenthiores.ecoswap.domain.carbon.use_cases.GetChallengeById
import com.darrenthiores.ecoswap.domain.carbon.use_cases.JoinChallenge
import com.darrenthiores.ecoswap.domain.utils.Resource
import com.darrenthiores.ecoswap.utils.flow.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChallengeDetailViewModel(
    private val getChallengeById: GetChallengeById,
    private val joinChallenge: JoinChallenge,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(ChallengeDetailState())
    val state = _state.toCommonStateFlow()

    fun onEvent(event: ChallengeDetailEvent) {
        when (event) {
            ChallengeDetailEvent.Join -> {
                join()
            }
        }
    }

    private fun join() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            val result = joinChallenge(
                challengeId = state.value.challenge?.id ?: return@launch
            )

            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            challenge = it.challenge?.copy(
                                isJoined = true
                            )
                        )
                    }
                }
            }
        }
    }

    fun initChallenge(id: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            val result = getChallengeById(
                challengeId = id
            )

            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            challenge = result.data
                        )
                    }
                }
            }
        }
    }
}
