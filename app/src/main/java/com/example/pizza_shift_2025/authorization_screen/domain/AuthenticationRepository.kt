package com.example.pizza_shift_2025.authorization_screen.domain

import com.example.pizza_shift_2025.authorization_screen.domain.model.AuthState
import com.example.pizza_shift_2025.authorization_screen.domain.model.OtpRequest
import com.example.pizza_shift_2025.authorization_screen.domain.model.OtpResponse
import com.example.pizza_shift_2025.common.Resource
import kotlinx.coroutines.flow.StateFlow

interface AuthenticationRepository {

    suspend fun createOtp(otpRequest: OtpRequest): Resource<OtpResponse>

    suspend fun getAuthState(): StateFlow<AuthState>

    suspend fun refreshAuthState()
}