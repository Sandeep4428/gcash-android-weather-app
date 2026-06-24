package com.snapwork.gcashweatherapp.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snapwork.gcashweatherapp.data.local.entity.UserEntity
import com.snapwork.gcashweatherapp.data.repository.AuthRepository
import com.snapwork.gcashweatherapp.utils.PasswordUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> =
        _uiState.asStateFlow()

    fun onNameChange(value: String) {
        _uiState.value = _uiState.value.copy(name = value)
    }

    fun onEmailChange(value: String) {
        _uiState.value = _uiState.value.copy(email = value)
    }

    fun onPasswordChange(value: String) {
        _uiState.value = _uiState.value.copy(password = value)
    }

    fun onConfirmPasswordChange(value: String) {
        _uiState.value = _uiState.value.copy(
            confirmPassword = value
        )
    }

    fun register() {

        val state = _uiState.value

        when {

            state.name.isBlank() -> {
                updateError("Name is required")
                return
            }

            state.email.isBlank() -> {
                updateError("Email is required")
                return
            }

            !android.util.Patterns.EMAIL_ADDRESS
                .matcher(state.email)
                .matches() -> {

                updateError("Invalid Email")
                return
            }

            state.password.length < 8 -> {
                updateError("Password must be 8 characters")
                return
            }

            state.password != state.confirmPassword -> {
                updateError("Passwords do not match")
                return
            }
        }

        viewModelScope.launch {

            val existingUser =
                repository.getUser(state.email)

            if (existingUser != null) {

                updateError("User already exists")
                return@launch
            }

            repository.register(
                UserEntity(
                    name = state.name,
                    email = state.email,
                    passwordHash = PasswordUtil
                        .hashPassword(state.password)
                )
            )

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