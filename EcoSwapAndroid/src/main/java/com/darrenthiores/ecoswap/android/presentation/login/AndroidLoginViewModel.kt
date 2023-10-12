package com.darrenthiores.ecoswap.android.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.ecoswap.android.domain.core.preferences.Preferences
import com.darrenthiores.ecoswap.domain.auth.use_cases.Login
import com.darrenthiores.ecoswap.domain.utils.UiEvent
import com.darrenthiores.ecoswap.presentation.login.LoginEvent
import com.darrenthiores.ecoswap.presentation.login.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AndroidLoginViewModel @Inject constructor(
    private val preferences: Preferences,
    private val login: Login
): ViewModel() {
    private val viewModel by lazy {
        LoginViewModel(
            login = login,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    val uiEvent = viewModel
        .uiEvent
        .onEach {
            if (it == UiEvent.Success) {
                preferences.saveShouldShowAuth(
                    shouldShow = false
                )
            }
        }

    fun onEvent(event: LoginEvent) {
        viewModel.onEvent(event)
    }
}