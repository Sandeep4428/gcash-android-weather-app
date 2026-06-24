package com.snapwork.gcashweatherapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snapwork.gcashweatherapp.data.repository.AuthRepository
import com.snapwork.gcashweatherapp.utils.PasswordUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> =
        _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.value =
            _uiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _uiState.value =
            _uiState.value.copy(password = password)
    }

    fun login() {

        val state = _uiState.value

        if (state.email.isBlank()) {
            updateError("Email is required")
            return
        }

        if (state.password.isBlank()) {
            updateError("Password is required")
            return
        }

        viewModelScope.launch {

            val user =
                repository.getUser(state.email)

            if (user == null) {

                updateError("User not found")
                return@launch
            }

            val hash =
                PasswordUtil.hashPassword(
                    state.password
                )

            if (hash != user.passwordHash) {

                updateError("Invalid password")
                return@launch
            }

            _uiState.value =
                _uiState.value.copy(
                    isSuccess = true,
                    error = null
                )
        }
    }

    private fun updateError(message: String) {

        _uiState.value =
            _uiState.value.copy(
                error = message
            )
    }
}