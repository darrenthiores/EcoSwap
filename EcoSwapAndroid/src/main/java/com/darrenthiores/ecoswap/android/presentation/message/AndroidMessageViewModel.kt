package com.darrenthiores.ecoswap.android.presentation.message

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.ecoswap.domain.message.use_cases.GetMessagesById
import com.darrenthiores.ecoswap.domain.message.use_cases.InsertMessage
import com.darrenthiores.ecoswap.domain.user.use_cases.GetUserById
import com.darrenthiores.ecoswap.presentation.message.MessageEvent
import com.darrenthiores.ecoswap.presentation.message.MessageViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidMessageViewModel @Inject constructor(
    private val getMessagesById: GetMessagesById,
    private val getUserById: GetUserById,
    private val insertMessage: InsertMessage,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val viewModel by lazy {
        MessageViewModel(
            getMessageById = getMessagesById,
            getUserById = getUserById,
            insertMessage = insertMessage,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: MessageEvent) {
        viewModel.onEvent(event)
    }

    init {
        val userId = savedStateHandle.get<String>("userId")

        userId?.let {
            viewModel.init(
                userId = it
            )
        }
    }
}