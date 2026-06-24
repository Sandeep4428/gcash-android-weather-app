package com.snapwork.gcashweatherapp.presentation.registration


data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val error: String? = null,
    val isSuccess: Boolean = false
)