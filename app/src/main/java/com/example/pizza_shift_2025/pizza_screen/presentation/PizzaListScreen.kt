package com.example.pizza_shift_2025.pizza_screen.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pizza_shift_2025.R
import com.example.pizza_shift_2025.common.di.getApplicationComponent
import com.example.pizza_shift_2025.pizza_screen.domain.model.Pizza

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PizzaListScreen() {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.pizza_screen_pizza)) })
        }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            val component = getApplicationComponent()
            val viewModel: PizzaListScreenViewModel =
                viewModel(factory = component.getViewModelFactory())
            val pizzasList by
            viewModel.screenState.collectAsState(PizzaListScreenState.Initial)

            when (val state = pizzasList) {
                is PizzaListScreenState.Initial -> {}

                is PizzaListScreenState.Loading -> {
                    PizzaLoadingScreen()
                }

                is PizzaListScreenState.Error -> {
                    PizzaErrorScreen(state)
                }

                is PizzaListScreenState.Pizzas -> {
                    PizzaList(state)
                }
            }


        }
    }
}

@Composable
private fun PizzaLoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun PizzaList(state: PizzaListScreenState.Pizzas) {
    LazyColumn {
        items(state.pizzas) { pizza ->
            Log.d("TAG", pizza.imageUrl)
            PizzaItem(
                pizza = pizza,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 12.dp
                    )
            )
        }
    }
}

@Composable
private fun PizzaErrorScreen(state: PizzaListScreenState.Error) {
    Text(
        text = state.message,
        color = Color.Red,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun PizzaItem(pizza: Pizza, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
    ) {
        AsyncImage(
            model = pizza.imageUrl,
            contentDescription = null,
            modifier = Modifier.size(116.dp)
        )
        Spacer(modifier = Modifier.size(24.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = pizza.name,
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight(500),
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = pizza.description, fontSize = 12.sp, fontWeight = FontWeight(400))
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = String.format(
                    stringResource(id = R.string.pizza_screen_price),
                    pizza.sizes.first().price
                ),
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight(400)
            )
        }
    }
}
