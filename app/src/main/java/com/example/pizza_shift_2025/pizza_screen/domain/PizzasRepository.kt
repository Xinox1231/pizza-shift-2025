package com.example.pizza_shift_2025.pizza_screen.domain

import com.example.pizza_shift_2025.common.Resource
import com.example.pizza_shift_2025.pizza_screen.domain.model.Pizza

interface PizzasRepository {
    suspend fun loadPizzas(): Resource<List<Pizza>>
}