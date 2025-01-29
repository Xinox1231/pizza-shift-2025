package com.example.pizza_shift_2025.pizza_screen.data

import com.example.pizza_shift_2025.pizza_screen.data.remote.PizzaService
import com.example.pizza_shift_2025.pizza_screen.domain.PizzasRepository
import com.example.pizza_shift_2025.pizza_screen.domain.model.Pizza
import javax.inject.Inject

class PizzasRepositoryImpl @Inject constructor(
    private val apiService: PizzaService
) : PizzasRepository {
    override suspend fun loadPizzas(): List<Pizza> {
        val response = apiService.loadPizzas().pizzas
        val pizzas = response.map { it.toDomain() }
        return pizzas
    }
}