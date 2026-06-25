package com.snapwork.gcashweatherapp.presentation.weather

import androidx.annotation.DrawableRes
import com.snapwork.gcashweatherapp.R

data class WeatherUiState(

    val city: String = "",

    val country: String = "",

    val temperature: String = "",

    val sunrise: String = "",

    val sunset: String = "",

    val weather: String = "",

    @DrawableRes
    val weatherIcon: Int = R.drawable.clouds,

    val loading: Boolean = true,

    val error: String? = null

)