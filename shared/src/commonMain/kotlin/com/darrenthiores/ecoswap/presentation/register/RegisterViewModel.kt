package com.darrenthiores.ecoswap.presentation.register

import com.darrenthiores.ecoswap.domain.auth.use_cases.Register
import com.darrenthiores.ecoswap.domain.auth.use_cases.ValidateEmail
import com.darrenthiores.ecoswap.domain.auth.use_cases.ValidatePassword
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

class RegisterViewModel(
    private val register: Register,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.toCommonStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent
        .receiveAsFlow()
        .toCommonFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            RegisterEvent.Register -> {
                register()
            }
            is RegisterEvent.OnUsernameChange -> {
                _state.update {
                    it.copy(
                        username = event.username
                    )
                }
            }
            is RegisterEvent.OnEmailChange -> {
                _state.update {
                    it.copy(
                        email = event.email,
                        emailError = null
                    )
                }
            }
            is RegisterEvent.OnPasswordChange -> {
                _state.update {
                    it.copy(
                        password = event.password,
                        passwordError = null
                    )
                }
            }
            RegisterEvent.ToggleShowPassword -> {
                _state.update {
                    it.copy(
                        showPassword = !it.showPassword
                    )
                }
            }
        }
    }

    private fun register() {
        viewModelScope.launch {
            if (
                state.value.username.isEmpty() ||
                state.value.email.isEmpty() ||
                state.value.password.isEmpty()
                ) {
                return@launch
            }

            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            val isEmailValid = validateEmail(
                email = state.value.email
            )

            when (isEmailValid) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            emailError = isEmailValid.message,
                            isLoading = false
                        )
                    }

                    return@launch
                }
                is Resource.Loading -> Unit
                is Resource.Success -> Unit
            }

            val isPasswordValid = validatePassword(
                password = state.value.password
            )

            when (isPasswordValid) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            passwordError = isPasswordValid.message,
                            isLoading = false
                        )
                    }

                    return@launch
                }
                is Resource.Loading -> Unit
                is Resource.Success -> Unit
            }

            val result = register(
                username = state.value.username,
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
                            registerSuccess = true
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