package com.example.pizza_shift_2025.authorization_screen.data.local

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.pizza_shift_2025.authorization_screen.domain.model.Token
import javax.inject.Inject

class TokenStorageImpl @Inject constructor(
    application: Application
) : TokenStorage {

    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val LOG_TAG = "AuthStorage"
        private const val PREFERENCE_NAME = "auth_storage"
        private const val KEY_ACCESS_TOKEN = "access_token"
    }

    // Сохраняем токен
    override fun saveToken(token: Token) {
        sharedPreferences.edit().apply {
            putString(KEY_ACCESS_TOKEN, token.accessToken)
            apply()
        }
        Log.i(LOG_TAG, "Tokens saved successfully. AccessToken: ${token.accessToken}")
    }

    // Получаем токен
    override fun getToken(): Token? {
        val accessToken = sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
        if (accessToken == null) {
            Log.w(LOG_TAG, "Access token not found in SharedPreferences.")
            return null
        } else {
            Log.d(LOG_TAG, "Access token retrieved successfully.")
            return Token(accessToken)
        }
    }

    // Очищаем сохранённые токены
    override fun deleteToken() {
        sharedPreferences.edit().apply {
            remove(KEY_ACCESS_TOKEN)
            apply()
        }
        Log.i(LOG_TAG, "Tokens cleared from SharedPreferences.")
    }
}
