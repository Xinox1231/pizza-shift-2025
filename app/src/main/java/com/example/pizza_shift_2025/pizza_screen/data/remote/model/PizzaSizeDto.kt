package com.example.pizza_shift_2025.pizza_screen.data.remote.model

import com.google.gson.annotations.SerializedName

data class PizzaSizeDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)
