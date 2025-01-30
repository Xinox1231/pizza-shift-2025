package com.example.pizza_shift_2025.authorization_screen.di

import com.example.pizza_shift_2025.authorization_screen.data.AuthenticationRepositoryImpl
import com.example.pizza_shift_2025.authorization_screen.data.local.TokenStorage
import com.example.pizza_shift_2025.authorization_screen.data.local.TokenStorageImpl
import com.example.pizza_shift_2025.authorization_screen.data.remote.AuthenticationService
import com.example.pizza_shift_2025.authorization_screen.domain.AuthenticationRepository
import com.example.pizza_shift_2025.common.data.remote.ApiFactory
import com.example.pizza_shift_2025.pizza_screen.data.remote.PizzaService
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface AuthenticationModule {

    @Binds
    fun bindAuthenticationRepository(impl: AuthenticationRepositoryImpl): AuthenticationRepository

    @Binds
    fun bindTokenStorage(impl: TokenStorageImpl): TokenStorage

    companion object {
        @Provides
        fun provideAuthenticationService(apiFactory: ApiFactory): AuthenticationService {
            return apiFactory.createApi(AuthenticationService::class.java)
        }
    }
}