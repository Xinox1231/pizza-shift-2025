package com.example.pizza_shift_2025.pizza_screen.data.remote.model

import com.google.gson.annotations.SerializedName

data class PizzasResponseDto(
    @SerializedName("catalog")
    val pizzas: List<PizzaDto>
)