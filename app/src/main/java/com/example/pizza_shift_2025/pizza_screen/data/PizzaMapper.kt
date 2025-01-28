package com.example.pizza_shift_2025.pizza_screen.data

import com.example.pizza_shift_2025.common.Constants
import com.example.pizza_shift_2025.pizza_screen.data.remote.model.IngredientsDto
import com.example.pizza_shift_2025.pizza_screen.data.remote.model.PizzaDto
import com.example.pizza_shift_2025.pizza_screen.data.remote.model.PizzaSizeDto
import com.example.pizza_shift_2025.pizza_screen.domain.model.Ingredients
import com.example.pizza_shift_2025.pizza_screen.domain.model.Pizza
import com.example.pizza_shift_2025.pizza_screen.domain.model.PizzaSize

fun PizzaDto.toDomain() = Pizza(
    id = this.id,
    name = this.name,
    ingredients = this.ingredients.map { it.toDomain() },
    description = this.description,
    imageUrl = Constants.BASE_URL + this.imageUrl,
    sizes = this.sizes.map { it.toDomain() }
)

fun IngredientsDto.toDomain() = Ingredients(
    name = this.name,
    cost = this.cost,
    imageUrl = this.imageUrl
)

fun PizzaSizeDto.toDomain() = PizzaSize(
    name = this.name,
    price = this.price
)