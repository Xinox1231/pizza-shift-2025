package com.example.pizza_shift_2025.pizza_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_shift_2025.common.Constants
import com.example.pizza_shift_2025.common.Resource
import com.example.pizza_shift_2025.pizza_screen.domain.use_case.LoadPizzaListUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
            PizzaListScreenState.Error(
                message = throwable.localizedMessage ?: Constants.UNKNOWN_ERROR
            )
    }

    init {
        loadPizzas()
    }

    // потому что нужно прокинуть как-то ошибку, которую отправит сервер,
    // поэтому репозиторий возвращает теперь не список, а Result запроса
    fun loadPizzas() {
        viewModelScope.launch(exceptionHandler) {
            _screenState.value = PizzaListScreenState.Loading
            val result = loadPizzaListUseCase.invoke()
            when (result) {
                is Resource.Success -> {
                    val pizzas = result.data ?: emptyList()
                    _screenState.value = PizzaListScreenState.Pizzas(pizzas)
                }

                is Resource.Error -> {
                    val message = result.message
                    _screenState.value =
                        PizzaListScreenState.Error(message = message ?: Constants.UNKNOWN_ERROR)
                }
            }
        }
    }

}