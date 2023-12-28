package com.darrenthiores.ecoswap.android.presentation.add_progress

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.ecoswap.domain.carbon.use_cases.GetChallengeById
import com.darrenthiores.ecoswap.domain.carbon.use_cases.GetChallenges
import com.darrenthiores.ecoswap.domain.carbon.use_cases.InsertCarbonReduction
import com.darrenthiores.ecoswap.domain.core.utils.Constant
import com.darrenthiores.ecoswap.domain.utils.Resource
import com.darrenthiores.ecoswap.presentation.add_progress.AddProgressEvent
import com.darrenthiores.ecoswap.presentation.add_progress.AddProgressViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
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
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    val uiEvent = viewModel
        .uiEvent

    init {
        savedStateHandle.get<String>("challengeId")?.let { id ->
            viewModel.onEvent(
                event = AddProgressEvent.OnSelectCategory(
                    category = Constant.carbonCategoryById(
                        id = "3"
                    ) ?: return@let
                )
            )

            viewModelScope.launch {
                val result = getChallengeById(
                    challengeId = id
                )

                when (result) {
                    is Resource.Error -> {
                        Timber.d("Fetch Challenge By Id Failed: ${result.message}")
                    }
                    is Resource.Loading -> Unit
                    is Resource.Success -> {
                        viewModel.onEvent(
                            event = AddProgressEvent.OnSelectChallenge(
                                challenge = result.data ?: return@launch
                            )
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddProgressEvent) {
        viewModel.onEvent(event)
    }
}