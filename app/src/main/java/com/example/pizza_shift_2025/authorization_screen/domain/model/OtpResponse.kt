package com.example.pizza_shift_2025.authorization_screen.domain.model

class OtpResponse(
    val success: Boolean,
    val errorReason: String,
    val retryDelayInSeconds: Long
)