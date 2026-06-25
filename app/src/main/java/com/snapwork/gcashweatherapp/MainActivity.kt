package com.snapwork.gcashweatherapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.snapwork.gcashweatherapp.presentation.login.LoginScreen
import com.snapwork.gcashweatherapp.presentation.navigation.AppNavGraph
import com.snapwork.gcashweatherapp.presentation.registration.RegisterScreen
import com.snapwork.gcashweatherapp.ui.theme.GCashWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("WEATHER_KEY", BuildConfig.OPEN_WEATHER_API_KEY)

        setContent {
            GCashWeatherAppTheme {

//                RegisterScreen(
//                    onRegistrationSuccess = {
//                        println("Registration Success")
//                    }
//                )

                setContent {

                    GCashWeatherAppTheme {

                        AppNavGraph()

                    }
                }
                LoginScreen(
                    onLoginSuccess = {},
                    onNavigateToRegister = {}
                )

            }
        }
    }
}