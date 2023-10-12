package com.darrenthiores.ecoswap.presentation.register

data class RegisterState(
    val username: String = "",
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = "",
    val showPassword: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val registerSuccess: Boolean = false
)
