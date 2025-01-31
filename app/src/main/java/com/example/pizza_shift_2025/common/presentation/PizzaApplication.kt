package com.example.pizza_shift_2025.common.presentation

import android.app.Application
import com.example.pizza_shift_2025.common.di.DaggerApplicationComponent

class PizzaApplication: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}