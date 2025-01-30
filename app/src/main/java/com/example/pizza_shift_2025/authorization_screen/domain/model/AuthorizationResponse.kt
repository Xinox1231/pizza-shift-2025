package com.example.pizza_shift_2025.authorization_screen.domain.model

data class AuthorizationResponse(
    val success: Boolean,
    val reason: String,
    val token: String,
)