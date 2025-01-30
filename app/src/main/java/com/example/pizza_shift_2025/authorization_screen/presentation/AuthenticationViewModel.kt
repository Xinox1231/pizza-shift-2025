package com.example.pizza_shift_2025.authorization_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_shift_2025.authorization_screen.data.AuthenticationRepositoryImpl
import com.example.pizza_shift_2025.authorization_screen.domain.model.OtpRequest
import com.example.pizza_shift_2025.common.Constants
import com.example.pizza_shift_2025.common.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthenticationViewModel(
    private val repositoryImpl: AuthenticationRepositoryImpl
) : ViewModel() {

    private val _screenState =
        MutableStateFlow<AuthenticationScreenState>(AuthenticationScreenState.OtpNotRequested)
    val screenState = _screenState.asStateFlow()

    fun createOtp(phone: String) {
        val otpRequest = OtpRequest(phone)
        viewModelScope.launch {
            val resource = repositoryImpl.createOtp(otpRequest)
            when (resource) {
                is Resource.Success -> {
                    val countDown = resource.data?.retryDelayInSeconds!!
                    _screenState.value =
                        AuthenticationScreenState.OtpRequestCooldown(phone, countDown)
                }

                is Resource.Error -> {
                    val error = resource.message ?: Constants.UNKNOWN_ERROR
                    _screenState.value = AuthenticationScreenState.Error(error)
                }
            }
        }
    }

    fun startCountDown(phone: String) {
        viewModelScope.launch(context = Dispatchers.IO) {
            val currentState = screenState.value
            if (currentState is AuthenticationScreenState.OtpRequestCooldown) {
                val leftTime = currentState.secondsLeft
                for (leftSeconds in leftTime.downTo(0)) {
                    delay(1000)
                    _screenState.value = AuthenticationScreenState.OtpRequestCooldown(
                        phone = phone,
                        secondsLeft = leftSeconds
                    )
                }
                _screenState.value = AuthenticationScreenState.OtpRequestAllowed(phone)
            }
        }
    }
}