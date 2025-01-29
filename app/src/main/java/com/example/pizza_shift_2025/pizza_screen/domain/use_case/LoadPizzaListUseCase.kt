package com.example.pizza_shift_2025.pizza_screen.domain.use_case

import com.example.pizza_shift_2025.pizza_screen.domain.PizzasRepository
import com.example.pizza_shift_2025.pizza_screen.domain.model.Pizza
import javax.inject.Inject

class LoadPizzaListUseCase @Inject constructor(
    private val repository: PizzasRepository
) {
    suspend operator fun invoke(): List<Pizza> {
        return repository.loadPizzas()
    }
}