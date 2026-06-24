package com.snapwork.gcashweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.snapwork.gcashweatherapp.presentation.login.LoginScreen
import com.snapwork.gcashweatherapp.presentation.registration.RegisterScreen
import com.snapwork.gcashweatherapp.ui.theme.GCashWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GCashWeatherAppTheme {

//                RegisterScreen(
//                    onRegistrationSuccess = {
//                        println("Registration Success")
//                    }
//                )
                LoginScreen(
                    onLoginSuccess = {},
                    onNavigateToRegister = {}
                )

            }
        }
    }
}