package com.darrenthiores.ecoswap.android.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.ecoswap.android.domain.core.preferences.Preferences
import com.darrenthiores.ecoswap.domain.auth.use_cases.Register
import com.darrenthiores.ecoswap.domain.auth.use_cases.ValidateEmail
import com.darrenthiores.ecoswap.domain.auth.use_cases.ValidatePassword
import com.darrenthiores.ecoswap.domain.utils.UiEvent
import com.darrenthiores.ecoswap.presentation.register.RegisterEvent
import com.darrenthiores.ecoswap.presentation.register.RegisterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AndroidRegisterViewModel @Inject constructor(
    private val preferences: Preferences,
    private val register: Register,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword
): ViewModel() {
    private val viewModel by lazy {
        RegisterViewModel(
            register = register,
            validateEmail = validateEmail,
            validatePassword = validatePassword,
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

    fun onEvent(event: RegisterEvent) {
        viewModel.onEvent(event)
    }
}