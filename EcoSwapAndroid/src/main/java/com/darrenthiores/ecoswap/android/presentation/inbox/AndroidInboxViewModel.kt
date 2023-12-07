package com.darrenthiores.ecoswap.android.presentation.inbox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.ecoswap.domain.message.use_cases.GetInbox
import com.darrenthiores.ecoswap.presentation.inbox.InboxViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidInboxViewModel @Inject constructor(
    private val getInbox: GetInbox
): ViewModel() {
    private val viewModel by lazy {
        InboxViewModel(
            getInbox = getInbox,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
}