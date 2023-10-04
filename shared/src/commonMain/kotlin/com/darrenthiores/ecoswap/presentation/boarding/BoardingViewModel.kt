package com.darrenthiores.ecoswap.presentation.boarding

import com.darrenthiores.ecoswap.domain.utils.UiEvent
import com.darrenthiores.ecoswap.utils.flow.toCommonFlow
import com.darrenthiores.ecoswap.utils.flow.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BoardingViewModel(
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(BoardingState())
    val state = _state.toCommonStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent
        .receiveAsFlow()
        .toCommonFlow()

    fun onEvent(event: BoardingEvent) {
        when (event) {
            BoardingEvent.Start -> {
                _state.update {
                    it.copy(
                        done = true
                    )
                }

                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Success
                    )
                }
            }
            is BoardingEvent.UpdateBoarding -> {
                _state.update {
                    it.copy(
                        currentPage = event.page
                    )
                }
            }
        }
    }
}