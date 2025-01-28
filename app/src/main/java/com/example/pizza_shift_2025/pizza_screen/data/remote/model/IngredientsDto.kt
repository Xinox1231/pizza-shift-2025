package com.example.pizza_shift_2025.pizza_screen.data.remote.model

import com.google.gson.annotations.SerializedName

data class IngredientsDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("cost")
    val cost: String,
    @SerializedName("img")
    val imageUrl: String
)
