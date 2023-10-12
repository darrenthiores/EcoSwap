package com.darrenthiores.ecoswap.presentation.register

sealed class RegisterEvent {
    data class OnUsernameChange(val username: String): RegisterEvent()
    data class OnEmailChange(val email: String): RegisterEvent()
    data class OnPasswordChange(val password: String): RegisterEvent()
    object ToggleShowPassword: RegisterEvent()
    object Register: RegisterEvent()
}
