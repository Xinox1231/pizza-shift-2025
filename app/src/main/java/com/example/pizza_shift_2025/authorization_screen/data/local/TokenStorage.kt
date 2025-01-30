package com.example.pizza_shift_2025.authorization_screen.data.local

import com.example.pizza_shift_2025.authorization_screen.domain.model.Token

interface TokenStorage {

    fun saveToken(token: Token)

    fun getToken(): Token?

    fun deleteToken()
}