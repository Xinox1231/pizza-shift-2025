package com.example.pizza_shift_2025.common.data.remote

import com.example.pizza_shift_2025.common.Constants
import com.example.pizza_shift_2025.pizza_screen.data.remote.PizzaService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiFactoryImpl : ApiFactory {


    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    override fun <T> createApi(service: Class<T>): T {
        return retrofit.create(service)
    }
}