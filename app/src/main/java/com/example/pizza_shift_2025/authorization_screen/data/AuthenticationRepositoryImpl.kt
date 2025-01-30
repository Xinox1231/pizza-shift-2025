package com.example.pizza_shift_2025.authorization_screen.data

import com.example.pizza_shift_2025.authorization_screen.data.local.TokenStorage
import com.example.pizza_shift_2025.authorization_screen.data.remote.AuthenticationService
import com.example.pizza_shift_2025.authorization_screen.domain.model.AuthState
import com.example.pizza_shift_2025.authorization_screen.domain.model.OtpRequest
import com.example.pizza_shift_2025.authorization_screen.domain.model.OtpResponse
import com.example.pizza_shift_2025.common.Constants
import com.example.pizza_shift_2025.common.Resource
import com.example.pizza_shift_2025.common.data.remote.ApiFactoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val tokenStorage: TokenStorage
) {

    private val apiService = ApiFactoryImpl.createApi(AuthenticationService::class.java)

    private val checkAuthStateEvents = MutableSharedFlow<Unit>(replay = 1)
    private val scope = CoroutineScope(Dispatchers.IO)

    private val authState = flow<AuthState> {
        checkAuthStateEvents.emit(Unit)
        checkAuthStateEvents.collect {
            val token = tokenStorage.getToken()
            val isLogged = token != null
            if (isLogged) emit(AuthState.Authorized) else emit(AuthState.NotAuthorized)
        }
    }.stateIn(
        scope = scope,
        started = SharingStarted.Lazily,
        initialValue = AuthState.Initial
    )

    suspend fun createOtp(otpRequest: OtpRequest): Resource<OtpResponse> {
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
}