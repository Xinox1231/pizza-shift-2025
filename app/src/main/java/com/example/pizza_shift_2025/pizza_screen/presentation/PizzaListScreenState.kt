package com.example.pizza_shift_2025.pizza_screen.presentation

import com.example.pizza_shift_2025.pizza_screen.domain.model.Pizza

sealed class PizzaListScreenState {
    data object Initial : PizzaListScreenState()

    data object Loading: PizzaListScreenState()

    data class Error(
        val message: String
    ) : PizzaListScreenState()

    data class Pizzas(
        val pizzas: List<Pizza>
    ) : PizzaListScreenState()
}