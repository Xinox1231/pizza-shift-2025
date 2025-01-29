package com.example.pizza_shift_2025.common.di

import com.example.pizza_shift_2025.common.data.remote.ApiFactory
import com.example.pizza_shift_2025.common.data.remote.ApiFactoryImpl
import dagger.Module
import dagger.Provides

@Module
interface RemoteModule {

    companion object {
        @Provides
        @ApplicationScope
        fun bindApiFactory(): ApiFactory = ApiFactoryImpl
    }
}