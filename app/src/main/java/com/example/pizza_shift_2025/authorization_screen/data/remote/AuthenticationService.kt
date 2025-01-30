package com.example.pizza_shift_2025.authorization_screen.data.remote

import com.example.pizza_shift_2025.authorization_screen.data.remote.model.AuthorizationRequestDto
import com.example.pizza_shift_2025.authorization_screen.data.remote.model.AuthorizationResponseDto
import com.example.pizza_shift_2025.authorization_screen.data.remote.model.OtpRequestDto
import com.example.pizza_shift_2025.authorization_screen.data.remote.model.OtpResponseDto
import com.example.pizza_shift_2025.authorization_screen.domain.model.AuthorizationRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    @POST("auth/otp")
    suspend fun createOtp(
        @Body otpRequestDto: OtpRequestDto
    ): OtpResponseDto

    @POST("users/signin")
    suspend fun signIn(
        @Body authorizationRequest: AuthorizationRequestDto
    ): AuthorizationResponseDto
}