package com.example.pizza_shift_2025.pizza_screen.data

import com.example.pizza_shift_2025.common.Constants
import com.example.pizza_shift_2025.pizza_screen.data.remote.PizzaService
import com.example.pizza_shift_2025.common.Resource
import com.example.pizza_shift_2025.pizza_screen.domain.PizzasRepository
import com.example.pizza_shift_2025.pizza_screen.domain.model.Pizza
import javax.inject.Inject

class PizzasRepositoryImpl @Inject constructor(
    private val apiService: PizzaService
) : PizzasRepository {
    override suspend fun loadPizzas(): Resource<List<Pizza>> {
        val response = apiService.loadPizzas()
        if (response.success) {
            val pizzas = response.pizzas.map { it.toDomain() }
            return Resource.Success(pizzas)
        } else {
            return Resource.Error(response.reason ?: Constants.UNKNOWN_ERROR)
        }
    }
}