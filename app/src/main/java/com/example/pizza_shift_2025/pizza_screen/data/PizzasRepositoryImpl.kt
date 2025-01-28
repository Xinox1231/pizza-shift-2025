package com.example.pizza_shift_2025.pizza_screen.data

import com.example.pizza_shift_2025.common.Resource
import com.example.pizza_shift_2025.common.data.remote.ApiFactory
import com.example.pizza_shift_2025.pizza_screen.data.remote.PizzaService
import com.example.pizza_shift_2025.pizza_screen.domain.PizzasRepository
import com.example.pizza_shift_2025.pizza_screen.domain.model.Pizza
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class PizzasRepositoryImpl @Inject constructor(
    private val apiService: PizzaService
) : PizzasRepository {
    override fun loadPizzas(): Flow<Resource<List<Pizza>>> = flow<Resource<List<Pizza>>> {
        val response = apiService.loadPizzas().pizzas
        val pizzas = response.map { it.toDomain() }
        emit(Resource.Success(pizzas))
    }.onStart {
        emit(Resource.Loading())
    }.catch { throwable ->
        emit(Resource.Error(message = throwable.localizedMessage ?: "error"))
    }
}