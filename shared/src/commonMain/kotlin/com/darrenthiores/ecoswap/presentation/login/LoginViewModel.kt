package com.darrenthiores.ecoswap.presentation.login

import com.darrenthiores.ecoswap.domain.auth.use_cases.Login
import com.darrenthiores.ecoswap.domain.utils.Resource
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

class LoginViewModel(
    private val login: Login,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(LoginState())
    val state = _state.toCommonStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent
        .receiveAsFlow()
        .toCommonFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.Login -> {
                login()
            }
            is LoginEvent.OnEmailChange -> {
                _state.update {
                    it.copy(
                        email = event.email
                    )
                }
            }
            is LoginEvent.OnPasswordChange -> {
                _state.update {
                    it.copy(
                        password = event.password
                    )
                }
            }
            LoginEvent.ToggleShowPassword -> {
                _state.update {
                    it.copy(
                        showPassword = !it.showPassword
                    )
                }
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            if (state.value.email.isEmpty() || state.value.password.isEmpty()) {
                return@launch
            }

            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            val result = login(
                email = state.value.email,
                password = state.value.password
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
                            loginSuccess = true
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