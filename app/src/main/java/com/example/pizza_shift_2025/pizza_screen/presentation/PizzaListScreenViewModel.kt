package com.example.pizza_shift_2025.pizza_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_shift_2025.pizza_screen.domain.PizzasRepository
import com.example.pizza_shift_2025.pizza_screen.domain.use_case.LoadPizzaListUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class PizzaListScreenViewModel @Inject constructor(
    private val loadPizzaListUseCase: LoadPizzaListUseCase
) : ViewModel() {

    private val _screenState: MutableStateFlow<PizzaListScreenState> =
        MutableStateFlow(PizzaListScreenState.Initial)
    val screenState = _screenState.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _screenState.value =
            PizzaListScreenState.Error(message = throwable.localizedMessage ?: "error")
    }

    init {
        loadPizzas()
    }

    fun loadPizzas() {
        viewModelScope.launch(exceptionHandler) {
            _screenState.value = PizzaListScreenState.Loading
            val pizza = loadPizzaListUseCase.invoke()
            _screenState.value = PizzaListScreenState.Pizzas(pizza)
        }
    }

}