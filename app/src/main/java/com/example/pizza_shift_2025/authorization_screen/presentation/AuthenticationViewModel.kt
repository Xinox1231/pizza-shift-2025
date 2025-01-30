package com.example.pizza_shift_2025.authorization_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_shift_2025.authorization_screen.domain.AuthenticationRepository
import com.example.pizza_shift_2025.authorization_screen.domain.model.OtpRequest
import com.example.pizza_shift_2025.authorization_screen.ui.AuthenticationScreenState
import com.example.pizza_shift_2025.common.Constants
import com.example.pizza_shift_2025.common.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthenticationViewModel @Inject constructor(
    private val repository: AuthenticationRepository
) : ViewModel() {

    private val _screenState =
        MutableStateFlow<AuthenticationScreenState>(AuthenticationScreenState.OtpNotRequested)
    val screenState = _screenState.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _screenState.value =
            AuthenticationScreenState.Error(
                message = throwable.localizedMessage ?: Constants.UNKNOWN_ERROR
            )
    }

    fun createOtp(phone: String) {
        val otpRequest = OtpRequest(phone)
        viewModelScope.launch(exceptionHandler) {
            val resource = repository.createOtp(otpRequest)
            when (resource) {
                is Resource.Success -> {
                    val countDown = resource.data?.retryDelayInSeconds!!
                    _screenState.value =
                        AuthenticationScreenState.OtpRequestCooldown(countDown)
                    startCountDown()
                }

                is Resource.Error -> {
                    val error = resource.message ?: Constants.UNKNOWN_ERROR
                    _screenState.value = AuthenticationScreenState.Error(error)
                }
            }
        }
    }

    private fun startCountDown() {
        viewModelScope.launch() {
            val currentState = screenState.value
            if (currentState is AuthenticationScreenState.OtpRequestCooldown) {
                val leftTime = currentState.secondsLeft
                for (leftSeconds in leftTime.downTo(1)) {
                    _screenState.value = AuthenticationScreenState.OtpRequestCooldown(
                        secondsLeft = leftSeconds
                    )
                    delay(1000)
                }
                _screenState.value = AuthenticationScreenState.OtpRequestAllowed
            }
        }
    }
}