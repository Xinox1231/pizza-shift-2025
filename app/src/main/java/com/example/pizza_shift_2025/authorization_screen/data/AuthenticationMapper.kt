package com.example.pizza_shift_2025.authorization_screen.data

import com.example.pizza_shift_2025.authorization_screen.data.remote.model.OtpRequestDto
import com.example.pizza_shift_2025.authorization_screen.data.remote.model.OtpResponseDto
import com.example.pizza_shift_2025.authorization_screen.domain.model.OtpRequest
import com.example.pizza_shift_2025.authorization_screen.domain.model.OtpResponse
import com.example.pizza_shift_2025.common.millisToSeconds

fun OtpResponseDto.toDomain() = OtpResponse(
    success = this.success,
    errorReason = this.errorReason,
    retryDelayInSeconds = this.retryDelayInMillis.millisToSeconds()
)

fun OtpRequest.toDto() = OtpRequestDto(
    phone = this.phone
)