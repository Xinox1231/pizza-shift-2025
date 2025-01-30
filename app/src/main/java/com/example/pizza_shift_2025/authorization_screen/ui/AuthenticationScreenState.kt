package com.example.pizza_shift_2025.authorization_screen.ui

sealed class AuthenticationScreenState {
    data object OtpNotRequested: AuthenticationScreenState()
    data object OtpRequestAllowed : AuthenticationScreenState()
    data class OtpRequestCooldown(
        val secondsLeft: Int
    ): AuthenticationScreenState()

    data class Error(
        val message: String
    ): AuthenticationScreenState()
}