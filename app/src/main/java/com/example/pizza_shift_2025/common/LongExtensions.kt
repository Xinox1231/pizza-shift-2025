package com.example.pizza_shift_2025.common

fun Long.millisToSeconds(): Long {
    return this / Constants.MILLIS_IN_SEC
}