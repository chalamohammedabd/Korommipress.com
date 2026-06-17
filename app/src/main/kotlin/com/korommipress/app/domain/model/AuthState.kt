package com.korommipress.app.domain.model

data class AuthState(
    val userId: String,
    val isAuthenticated: Boolean,
    val email: String? = null,
    val fullName: String? = null,
    val username: String? = null,
    val profileImageUrl: String? = null
)
