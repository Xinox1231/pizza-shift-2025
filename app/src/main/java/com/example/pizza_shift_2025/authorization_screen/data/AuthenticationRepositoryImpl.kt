package com.example.pizza_shift_2025.authorization_screen.data

import com.example.pizza_shift_2025.authorization_screen.data.remote.AuthenticationService
import com.example.pizza_shift_2025.authorization_screen.domain.AuthenticationRepository
import com.example.pizza_shift_2025.authorization_screen.domain.model.AuthState
import com.example.pizza_shift_2025.authorization_screen.domain.model.AuthorizationRequest
import com.example.pizza_shift_2025.authorization_screen.domain.model.AuthorizationResponse
import com.example.pizza_shift_2025.authorization_screen.domain.model.OtpRequest
import com.example.pizza_shift_2025.authorization_screen.domain.model.OtpResponse
import com.example.pizza_shift_2025.authorization_screen.domain.model.Token
import com.example.pizza_shift_2025.common.Constants
import com.example.pizza_shift_2025.common.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val authManager: AuthManager,
    private val apiService: AuthenticationService
) : AuthenticationRepository {



    override suspend fun createOtp(otpRequest: OtpRequest): Resource<OtpResponse> {
        val otpRequestDto = otpRequest.toDto()
        val response = apiService.createOtp(otpRequestDto)
        if (response.success) {
            val otp = response.toDomain()
            return Resource.Success(otp)
        } else {
            val error = response.errorReason ?: Constants.UNKNOWN_ERROR
            return Resource.Error(error)
        }
    }

    override suspend fun signIn(authorizationRequest: AuthorizationRequest): Resource<Unit> {
        val authorizationRequestDto = authorizationRequest.toDto()
        val response = apiService.signIn(authorizationRequestDto)
        if (response.success) {
            val authorizationResponse = response.toDomain()
            val token = authorizationResponse.token
            authManager.saveToken(Token(token))
            return Resource.Success(Unit)
        } else {
            val error = response.errorReason ?: Constants.UNKNOWN_ERROR
            return Resource.Error(error)
        }
    }

    override fun getAuthState(): StateFlow<AuthState> = authManager.authState

    override suspend fun refreshAuthState() {
        authManager.checkAuthStateEvents.emit(Unit)
    }
}