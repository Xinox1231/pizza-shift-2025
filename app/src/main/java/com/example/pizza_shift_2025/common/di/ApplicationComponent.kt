package com.example.pizza_shift_2025.common.di

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.pizza_shift_2025.common.presentation.PizzaApplication
import com.example.pizza_shift_2025.common.presentation.ViewModelFactory
import com.example.pizza_shift_2025.pizza_screen.di.PizzaModule
import dagger.Component


@Component(modules = [ViewModelModule::class, PizzaModule::class])
interface ApplicationComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {

        //Если понадобится что-то передать в граф
        fun create(): ApplicationComponent
    }
}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    val context = LocalContext.current
    Log.d("RECOMPOSITION_TAG", "getApplicationComponent")
    return remember(context) { (context.applicationContext as PizzaApplication).component }
}