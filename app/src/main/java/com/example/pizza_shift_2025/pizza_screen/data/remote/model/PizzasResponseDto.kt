package com.example.pizza_shift_2025.pizza_screen.data.remote.model

import com.google.gson.annotations.SerializedName

data class PizzasResponseDto(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("reason")
    val reason: String?,
    @SerializedName("catalog")
    val pizzas: List<PizzaDto>
)