package com.korommipress.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korommipress.app.data.repository.AuthRepository
import com.korommipress.app.domain.model.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState?>(null)
    val authState: StateFlow<AuthState?> = _authState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun login(email: String, password: String) {
        if (!validateLoginInput(email, password)) return

        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val result = authRepository.login(email, password)
                if (result.isSuccess) {
                    _authState.value = AuthState(
                        userId = result.getOrNull() ?: "",
                        isAuthenticated = true
                    )
                } else {
                    _errorMessage.value = result.exceptionOrNull()?.message
                        ?: "Login failed. Please try again."
                }
            } catch (e: Exception) {
                _errorMessage.value = getErrorMessage(e.message)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun signup(
        email: String,
        password: String,
        fullName: String,
        username: String
    ) {
        if (!validateSignupInput(email, password, fullName, username)) return

        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val result = authRepository.signup(
                    email = email,
                    password = password,
                    fullName = fullName,
                    username = username
                )
                if (result.isSuccess) {
                    _authState.value = AuthState(
                        userId = result.getOrNull() ?: "",
                        isAuthenticated = true
                    )
                } else {
                    _errorMessage.value = result.exceptionOrNull()?.message
                        ?: "Signup failed. Please try again."
                }
            } catch (e: Exception) {
                _errorMessage.value = getErrorMessage(e.message)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
            _authState.value = null
            _errorMessage.value = null
        }
    }

    private fun validateLoginInput(email: String, password: String): Boolean {
        return when {
            email.isBlank() -> {
                _errorMessage.value = "Please enter your email"
                false
            }
            !isValidEmail(email) -> {
                _errorMessage.value = "Invalid email address"
                false
            }
            password.isBlank() -> {
                _errorMessage.value = "Please enter your password"
                false
            }
            else -> true
        }
    }

    private fun validateSignupInput(
        email: String,
        password: String,
        fullName: String,
        username: String
    ): Boolean {
        return when {
            fullName.isBlank() -> {
                _errorMessage.value = "Please enter your full name"
                false
            }
            username.isBlank() -> {
                _errorMessage.value = "Please enter a username"
                false
            }
            email.isBlank() -> {
                _errorMessage.value = "Please enter your email"
                false
            }
            !isValidEmail(email) -> {
                _errorMessage.value = "Invalid email address"
                false
            }
            password.isBlank() -> {
                _errorMessage.value = "Please enter a password"
                false
            }
            !isStrongPassword(password) -> {
                _errorMessage.value = "Password must be at least 8 characters with uppercase, lowercase, number and special character"
                false
            }
            else -> true
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isStrongPassword(password: String): Boolean {
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { !it.isLetterOrDigit() }
        val isLengthValid = password.length >= 8

        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar && isLengthValid
    }

    private fun getErrorMessage(errorMsg: String?): String {
        return when {
            errorMsg?.contains("EMAIL_EXISTS") == true -> "Email already exists"
            errorMsg?.contains("INVALID_LOGIN_CREDENTIALS") == true -> "Invalid email or password"
            errorMsg?.contains("USER_DISABLED") == true -> "User account has been disabled"
            errorMsg?.contains("TOO_MANY_ATTEMPTS_LOGIN_RETRY_ACCOUNT") == true ->
                "Too many login attempts. Please try again later."
            errorMsg?.contains("weak password") == true -> "Password is too weak"
            errorMsg?.contains("network") == true -> "Network error. Please check your connection."
            else -> errorMsg ?: "An error occurred. Please try again."
        }
    }
}
