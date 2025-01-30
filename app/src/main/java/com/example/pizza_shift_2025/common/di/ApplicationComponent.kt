package com.example.pizza_shift_2025.common.di

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.pizza_shift_2025.authorization_screen.di.AuthenticationModule
import com.example.pizza_shift_2025.common.data.remote.ApiFactory
import com.example.pizza_shift_2025.common.presentation.PizzaApplication
import com.example.pizza_shift_2025.common.presentation.ViewModelFactory
import com.example.pizza_shift_2025.pizza_screen.di.PizzaModule
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        ViewModelModule::class, RemoteModule::class,
        PizzaModule::class, AuthenticationModule::class
    ]
)
interface ApplicationComponent {

    fun getViewModelFactory(): ViewModelFactory

    fun apiFactory(): ApiFactory

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    val context = LocalContext.current
    Log.d("RECOMPOSITION_TAG", "getApplicationComponent")
    return remember(context) { (context.applicationContext as PizzaApplication).component }
}