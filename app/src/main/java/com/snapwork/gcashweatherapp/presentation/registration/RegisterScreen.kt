package com.snapwork.gcashweatherapp.presentation.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.Composable

@Composable
fun RegisterScreen(
    onRegistrationSuccess: () -> Unit,
    viewModel: RegisterViewModel =
        hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    if (state.isSuccess) {
        onRegistrationSuccess()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement =
        Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Register",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = state.name,
            onValueChange = viewModel::onNameChange,
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.email,
            onValueChange = viewModel::onEmailChange,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.password,
            onValueChange = viewModel::onPasswordChange,
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.confirmPassword,
            onValueChange =
            viewModel::onConfirmPasswordChange,
            label = {
                Text("Confirm Password")
            },
            modifier = Modifier.fillMaxWidth()
        )

        state.error?.let {

            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = {
                viewModel.register()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register")
        }
    }
}