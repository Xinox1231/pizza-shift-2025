package com.example.pizza_shift_2025.authorization_screen.data.remote

import com.example.pizza_shift_2025.authorization_screen.data.remote.model.OtpRequestDto
import com.example.pizza_shift_2025.authorization_screen.data.remote.model.OtpResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    @POST("auth/otp")
    suspend fun createOtp(
        @Body otpRequestDto: OtpRequestDto
    ): OtpResponseDto
}