package com.example.pizza_shift_2025.authorization_screen.domain.model

sealed class AuthState {

    data object Authorized: AuthState()

    data object NotAuthorized: AuthState()

    data object Initial: AuthState()
}
