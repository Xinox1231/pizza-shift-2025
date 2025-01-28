package com.example.pizza_shift_2025.pizza_screen.domain

import com.example.pizza_shift_2025.common.Resource
import com.example.pizza_shift_2025.pizza_screen.domain.model.Pizza
import kotlinx.coroutines.flow.Flow

interface PizzasRepository {

    fun loadPizzas(): Flow<Resource<List<Pizza>>>
}