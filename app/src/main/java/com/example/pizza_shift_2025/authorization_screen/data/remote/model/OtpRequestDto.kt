package com.example.pizza_shift_2025.authorization_screen.data.remote.model

import com.google.gson.annotations.SerializedName

data class OtpRequestDto(
    @SerializedName("phone")
    val phone: String
)