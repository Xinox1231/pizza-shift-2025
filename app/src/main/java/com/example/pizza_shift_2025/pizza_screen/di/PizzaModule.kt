package com.example.pizza_shift_2025.pizza_screen.di

import com.example.pizza_shift_2025.common.data.remote.ApiFactory
import com.example.pizza_shift_2025.common.data.remote.ApiFactoryImpl
import com.example.pizza_shift_2025.pizza_screen.data.PizzasRepositoryImpl
import com.example.pizza_shift_2025.pizza_screen.data.remote.PizzaService
import com.example.pizza_shift_2025.pizza_screen.domain.PizzasRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface PizzaModule {

    @Binds
    fun bindPizzasRepository(impl: PizzasRepositoryImpl): PizzasRepository

    companion object {
        @Provides
        fun providePizzaService(apiFactory: ApiFactory): PizzaService {
            return apiFactory.createApi(PizzaService::class.java)
        }
    }
}