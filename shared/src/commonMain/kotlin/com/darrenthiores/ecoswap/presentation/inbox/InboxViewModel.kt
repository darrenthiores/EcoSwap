package com.darrenthiores.ecoswap.presentation.inbox

import com.darrenthiores.ecoswap.domain.core.utils.Dummy
import com.darrenthiores.ecoswap.domain.message.use_cases.GetInbox
import com.darrenthiores.ecoswap.domain.utils.Resource
import com.darrenthiores.ecoswap.utils.flow.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
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
            getInbox(
                userId = "1"
            )
                .collectLatest { result ->
                    when (result) {
                        is Resource.Error -> {
                            _state.update {
                                it.copy(
                                    inboxes = result.data ?: emptyList(),
                                    isLoading = false,
                                    error = result.message
                                )
                            }
                        }
                        is Resource.Loading -> {
                            _state.update {
                                it.copy(
                                    isLoading = true
                                )
                            }
                        }
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
}