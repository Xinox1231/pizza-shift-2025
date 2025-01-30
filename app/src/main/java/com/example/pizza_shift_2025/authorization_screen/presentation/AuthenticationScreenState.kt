package com.example.pizza_shift_2025.authorization_screen.presentation

sealed class AuthenticationScreenState {
    data object OtpNotRequested: AuthenticationScreenState()
    data class OtpRequestAllowed(
        val phone: String
    ): AuthenticationScreenState()
    data class OtpRequestCooldown(
        val phone: String,
        val secondsLeft: Int
    ): AuthenticationScreenState()

    data class Error(
        val message: String
    ): AuthenticationScreenState()
}