package com.example.pizza_shift_2025.pizza_screen.presentation

import androidx.lifecycle.ViewModel
import com.example.pizza_shift_2025.common.Resource
import com.example.pizza_shift_2025.pizza_screen.data.PizzasRepositoryImpl
import com.example.pizza_shift_2025.pizza_screen.domain.PizzasRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PizzaListScreenViewModel @Inject constructor(
    private val repository: PizzasRepository
): ViewModel() {


    val screenState = repository.loadPizzas().map {
        when(it){
            is Resource.Loading -> {
                PizzaListScreenState.Loading
            }

            is Resource.Error -> {
                val error = it.message ?: "error"
                PizzaListScreenState.Error(error)
            }

            is Resource.Success -> {
                val pizzas = it.data ?: emptyList()
                PizzaListScreenState.Pizzas(pizzas)
            }
        }
    }

}