package com.example.pizza_shift_2025.pizza_screen.data.remote.model

import com.google.gson.annotations.SerializedName

data class PizzaDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("ingredients")
    val ingredients: List<IngredientsDto>,
    @SerializedName("description")
    val description: String,
    @SerializedName("img")
    val imageUrl: String,
    @SerializedName("sizes")
    val sizes: List<PizzaSizeDto>
)