package com.example.pizza_shift_2025.common.di

import androidx.lifecycle.ViewModel
import com.example.pizza_shift_2025.pizza_screen.presentation.PizzaListScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(PizzaListScreenViewModel::class)
    @Binds
    fun bindPizzasViewModel(viewModel: PizzaListScreenViewModel): ViewModel
}