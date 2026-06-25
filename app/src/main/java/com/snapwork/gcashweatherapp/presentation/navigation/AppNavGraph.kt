package com.snapwork.gcashweatherapp.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.snapwork.gcashweatherapp.presentation.home.HomeScreen
import com.snapwork.gcashweatherapp.presentation.login.LoginScreen
import com.snapwork.gcashweatherapp.presentation.registration.RegisterScreen
import com.snapwork.gcashweatherapp.presentation.splash.SplashScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        composable(Routes.LOGIN) {

            LoginScreen(

                onLoginSuccess = {
                    navController.navigate(
                        Routes.HOME
                    )
                },

                onNavigateToRegister = {
                    navController.navigate(
                        Routes.REGISTER
                    )
                }
            )
        }

        composable(Routes.REGISTER) {

            RegisterScreen(

                onRegistrationSuccess = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.HOME) {

            HomeScreen(

                onLogout = {

                    navController.navigate(Routes.LOGIN) {

                        popUpTo(Routes.HOME) {

                            inclusive = true

                        }

                    }

                }

            )
        }

        composable(Routes.SPLASH) {

            SplashScreen(

                onLoggedIn = {

                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SPLASH) {
                            inclusive = true
                        }
                    }
                },

                onNotLoggedIn = {

                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.SPLASH) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}