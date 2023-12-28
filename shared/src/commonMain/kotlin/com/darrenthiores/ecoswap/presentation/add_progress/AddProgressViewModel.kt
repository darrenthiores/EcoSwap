package com.darrenthiores.ecoswap.presentation.add_progress

import com.darrenthiores.ecoswap.domain.carbon.use_cases.GetChallenges
import com.darrenthiores.ecoswap.domain.carbon.use_cases.InsertCarbonReduction
import com.darrenthiores.ecoswap.domain.core.utils.Constant
import com.darrenthiores.ecoswap.domain.utils.Resource
import com.darrenthiores.ecoswap.domain.utils.UiEvent
import com.darrenthiores.ecoswap.presentation.utils.DefaultPaginator
import com.darrenthiores.ecoswap.utils.flow.toCommonFlow
import com.darrenthiores.ecoswap.utils.flow.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.max

class AddProgressViewModel(
    private val addProgress: InsertCarbonReduction,
    private val getChallenges: GetChallenges,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(AddProgressState())
    val state = _state.toCommonStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent
        .receiveAsFlow()
        .toCommonFlow()

    private val challengePaginator = DefaultPaginator(
        initialKey = state.value.challenges.page,
        onLoadUpdated = { isLoading ->
            _state.update {
                it.copy(
                    challenges = it.challenges.copy(
                        isLoading = isLoading
                    )
                )
            }
        },
        onRequest = { nextPage ->
            getChallenges(
                isJoined = true,
                page = nextPage
            )
        },
        getNextKey = {
            state.value.challenges.page + 1
        },
        onError = { error ->
            _state.update {
                it.copy(
                    challenges = it.challenges.copy(
                        error = error,
                        endReached = true
                    )
                )
            }
        },
        onSuccess = { items, key ->
            _state.update {
                it.copy(
                    challenges = it.challenges.copy(
                        items = items,
                        page = key,
                        endReached = items.isEmpty()
                    )
                )
            }
        }
    )

    init {
        viewModelScope.launch {
            challengePaginator.loadNextItems()
        }
    }

    fun onEvent(event: AddProgressEvent) {
        when (event) {
            is AddProgressEvent.OnNumberChange -> {
                _state.update {
                    it.copy(
                        number = event.number
                    )
                }
            }
            is AddProgressEvent.OnSelectActivity -> {
                _state.update {
                    it.copy(
                        activity = event.activity,
                        activityDropDownOpen = false
                    )
                }
            }
            is AddProgressEvent.OnSelectCategory -> {
                _state.update {
                    it.copy(
                        category = event.category,
                        categoryDropDownOpen = false,
                        activities = Constant.getActivitiesByCategoryId(
                            id = event.category.id
                        ),
                        challenge = null,
                        activity = null,
                        task = null
                    )
                }
            }
            is AddProgressEvent.OnSelectChallenge -> {
                _state.update {
                    it.copy(
                        challenge = event.challenge
                    )
                }
            }
            AddProgressEvent.ToggleActivityDropDown -> {
                _state.update {
                    it.copy(
                        activityDropDownOpen = !it.activityDropDownOpen
                    )
                }
            }
            AddProgressEvent.ToggleCategoryDropDown -> {
                _state.update {
                    it.copy(
                        categoryDropDownOpen = !it.categoryDropDownOpen
                    )
                }
            }
            AddProgressEvent.Upload -> {
                upload()
            }
            is AddProgressEvent.OnSelectTask -> {
                _state.update {
                    it.copy(
                        task = event.task,
                        taskDropDownOpen = false
                    )
                }
            }
            AddProgressEvent.ToggleTaskDropDown -> {
                _state.update {
                    it.copy(
                        taskDropDownOpen = !it.taskDropDownOpen
                    )
                }
            }
            AddProgressEvent.LoadChallengeNextPage -> {
                viewModelScope.launch {
                    challengePaginator.loadNextItems()
                }
            }
        }
    }

    private fun upload() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            val stateValue = state.value
            val number = stateValue.number.toIntOrNull() ?: 1
            val total = (stateValue.activity?.carbon ?: stateValue.task?.carbonReduced ?: 0.0) * max(number, 1)
            val result = addProgress(
                categoryId = stateValue.category?.id ?: return@launch,
                taskId = stateValue.activity?.id ?: stateValue.task?.id ?: return@launch,
                taskTitle = stateValue.activity?.display ?: stateValue.task?.task ?: return@launch,
                total = total
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
                            isSuccess = true
                        )
                    }

                    _uiEvent.send(
                        UiEvent.Success
                    )
                }
            }
        }
    }
}