package com.example.pizza_shift_2025.authorization_screen.data.remote.model

import com.google.gson.annotations.SerializedName

data class AuthorizationResponseDto(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("reason")
    val errorReason: String?,
    @SerializedName("token")
    val token: String,
)