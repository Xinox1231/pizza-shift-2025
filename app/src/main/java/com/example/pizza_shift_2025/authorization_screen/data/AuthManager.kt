package com.example.pizza_shift_2025.authorization_screen.data

import com.example.pizza_shift_2025.authorization_screen.data.local.TokenStorage
import com.example.pizza_shift_2025.authorization_screen.domain.model.AuthState
import com.example.pizza_shift_2025.authorization_screen.domain.model.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class AuthManager @Inject constructor(
    private val tokenStorage: TokenStorage
) {

    val checkAuthStateEvents = MutableSharedFlow<Unit>(replay = 1)
    private val scope = CoroutineScope(Dispatchers.IO)

    val authState = flow<AuthState> {
        checkAuthStateEvents.emit(Unit)
        checkAuthStateEvents.collect {
            val isLogged = isLogged()
            if (isLogged) emit(AuthState.Authorized) else emit(AuthState.NotAuthorized)
        }
    }.stateIn(
        scope = scope,
        started = SharingStarted.Lazily,
        initialValue = AuthState.Initial
    )

    private fun isLogged(): Boolean {
        val token = tokenStorage.getToken()
        return token != null
    }

    fun saveToken(token: Token) {
        tokenStorage.saveToken(token)
    }

    fun getToken(): Token? = tokenStorage.getToken()

    fun deleteToken() {
        tokenStorage.deleteToken()
    }
}