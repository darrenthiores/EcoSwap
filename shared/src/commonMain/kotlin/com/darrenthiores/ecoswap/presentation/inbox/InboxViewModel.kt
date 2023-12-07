package com.darrenthiores.ecoswap.presentation.inbox

import com.darrenthiores.ecoswap.domain.core.utils.Dummy
import com.darrenthiores.ecoswap.domain.message.use_cases.GetInbox
import com.darrenthiores.ecoswap.domain.utils.Resource
import com.darrenthiores.ecoswap.utils.flow.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InboxViewModel(
    private val getInbox: GetInbox,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(InboxState())
    val state = _state.toCommonStateFlow()

    init {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )

            val result = getInbox(
                userId = "1"
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
                            inboxes = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }

                    if (result.data.isNullOrEmpty()) {
                        _state.update {
                            it.copy(
                                inboxes = Dummy.inboxes
                            )
                        }
                    }
                }
            }
        }
    }
}