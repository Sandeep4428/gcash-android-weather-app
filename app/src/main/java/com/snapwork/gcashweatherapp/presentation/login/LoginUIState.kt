package com.snapwork.gcashweatherapp.presentation.login


data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val error: String? = null,
    val isSuccess: Boolean = false
)