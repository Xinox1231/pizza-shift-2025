package com.example.pizza_shift_2025.authorization_screen.data.remote.model

import com.google.gson.annotations.SerializedName

data class OtpResponseDto(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("reason")
    val errorReason: String?,
    @SerializedName("retryDelay")
    val retryDelayInMillis: Long
)