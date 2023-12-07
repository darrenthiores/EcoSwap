package com.darrenthiores.ecoswap.presentation.message

import com.darrenthiores.ecoswap.domain.core.utils.Dummy
import com.darrenthiores.ecoswap.domain.message.use_cases.GetMessagesById
import com.darrenthiores.ecoswap.domain.message.use_cases.InsertMessage
import com.darrenthiores.ecoswap.domain.user.use_cases.GetUserById
import com.darrenthiores.ecoswap.domain.utils.Resource
import com.darrenthiores.ecoswap.utils.flow.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MessageViewModel(
    private val getMessageById: GetMessagesById,
    private val getUserById: GetUserById,
    private val insertMessage: InsertMessage,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(MessageState())
    val state = _state.toCommonStateFlow()

    fun onEvent(event: MessageEvent) {
        when(event) {
            is MessageEvent.OnMessageChange -> {
                if (!state.value.isSendingMessage) {
                    _state.value = state.value.copy(
                        newMessage = event.newText
                    )
                }
            }
            is MessageEvent.SendMessage -> {
                if (state.value.user == null) {
                    return
                }

                _state.value = state.value.copy(
                    isSendingMessage = true
                )

                val user = state.value.user!!

                viewModelScope.launch {
                    val inboxId = state.value.messages.firstOrNull()?.inboxId

                    val result = insertMessage(
                        inboxId = inboxId ?: "",
                        sentToId = user.id,
                        sentToUsername = user.name,
                        sentToImageUrl = user.imageUrl,
                        sentFromId = Dummy.user.id,
                        sentFromUsername = Dummy.user.name,
                        sentFromImageUrl = Dummy.user.imageUrl,
                        message = state.value.newMessage,
                        mediaUrl = "",
                        create = state.value.messages.isEmpty() || inboxId == null
                    )

                    when (result) {
                        is Resource.Error -> {
                            _state.update {
                                it.copy(
                                    error = result.message,
                                    isSendingMessage = false
                                )
                            }
                        }
                        is Resource.Loading -> Unit
                        is Resource.Success -> {
                            _state.update {
                                val list = it.messages.toMutableList()
                                result.data?.let { message ->
                                    list.add(0, message)
                                }

                                it.copy(
                                    isSendingMessage = false,
                                    newMessage = "",
                                    messages = list
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun init(
        userId: String
    ) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                userLoading = true
            )

            when(
                val result = getUserById(id = userId)
            ) {
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        userError = result.message,
                        userLoading = false
                    )
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        user = result.data,
                        userError = null,
                        userLoading = false
                    )
                }
            }
        }

        viewModelScope.launch {
            _state.value = state.value.copy(
                messagesLoading = true
            )

            val result = getMessageById(
                sentToId = userId,
                sentFromId = Dummy.user.id
            )

            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            messagesError = result.message,
                            messagesLoading = false
                        )
                    }
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            messages = result.data ?: emptyList(),
                            messagesLoading = false
                        )
                    }
                }
            }
        }
    }
}