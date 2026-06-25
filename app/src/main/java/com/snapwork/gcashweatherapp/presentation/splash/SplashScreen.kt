package com.snapwork.gcashweatherapp.presentation.splash


import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SplashScreen(
    onLoggedIn: () -> Unit,
    onNotLoggedIn: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {

    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    LaunchedEffect(isLoggedIn) {

        if (isLoggedIn)
            onLoggedIn()
        else
            onNotLoggedIn()
    }
}