package com.example.pizza_shift_2025.common.data.remote

interface ApiFactory {
    fun <T> createApi(service: Class<T>): T
}