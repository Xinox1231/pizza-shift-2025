package com.example.pizza_shift_2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pizza_shift_2025.authorization_screen.ui.AuthorizationScreen
import com.example.pizza_shift_2025.common.di.getApplicationComponent
import com.example.pizza_shift_2025.pizza_screen.presentation.PizzaListScreen
import com.example.pizza_shift_2025.ui.theme.Pizzashift2025Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            Pizzashift2025Theme(dynamicColor = false) {
                Surface(
                    color = MaterialTheme.colorScheme.primary,
                ) {
                    AuthorizationScreen()
                }
            }
        }
    }
}

