package com.example.pizza_shift_2025.authorization_screen.data

import com.example.pizza_shift_2025.authorization_screen.data.remote.AuthenticationService
import com.example.pizza_shift_2025.authorization_screen.data.remote.model.OtpRequestDto
import com.example.pizza_shift_2025.authorization_screen.domain.model.OtpRequest
import com.example.pizza_shift_2025.authorization_screen.domain.model.OtpResponse
import com.example.pizza_shift_2025.common.Constants
import com.example.pizza_shift_2025.common.Resource
import com.example.pizza_shift_2025.common.data.remote.ApiFactoryImpl

class AuthenticationRepositoryImpl {

    private val apiService = ApiFactoryImpl.createApi(AuthenticationService::class.java)

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