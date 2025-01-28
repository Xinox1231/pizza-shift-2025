package com.example.pizza_shift_2025.pizza_screen.domain.model

data class Pizza(
    val id: String,
    val name: String,
    val ingredients: List<Ingredients>,
    val description: String,
    val imageUrl: String,
    val sizes: List<PizzaSize>
)