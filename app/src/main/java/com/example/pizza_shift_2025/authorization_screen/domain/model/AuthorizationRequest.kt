package com.example.pizza_shift_2025.authorization_screen.domain.model

data class AuthorizationRequest(
    val phone: String,
    val otpCode: Int
)