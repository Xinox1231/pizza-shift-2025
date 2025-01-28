package com.example.pizza_shift_2025.pizza_screen.data.remote

import com.example.pizza_shift_2025.pizza_screen.data.remote.model.PizzasResponseDto
import retrofit2.http.GET

interface PizzaService {

    @GET("pizza/catalog")
    suspend fun loadPizzas(): PizzasResponseDto
}