package com.snapwork.gcashweatherapp.presentation.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snapwork.gcashweatherapp.R
import com.snapwork.gcashweatherapp.data.remote.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState = _uiState.asStateFlow()

    fun getWeather(
        lat: Double,
        lon: Double
    ) {

        viewModelScope.launch {

            try {

                val response = repository.getWeather(lat, lon)

                _uiState.value = WeatherUiState(

                    city = response.city,

                    country = response.sys.country,

                    temperature = "${response.main.temperature} °C",

                    sunrise = formatTime(response.sys.sunrise),

                    sunset = formatTime(response.sys.sunset),

                    weather = response.weather.first().main,

                    weatherIcon = getWeatherIcon(
                        response.weather.first().main
                    ),

                    loading = false
                )

            } catch (e: Exception) {

                _uiState.value = WeatherUiState(

                    loading = false,

                    error = e.localizedMessage ?: "Something went wrong"

                )

            }

        }

    }

    fun showLocationPermissionError() {

        _uiState.value = _uiState.value.copy(

            loading = false,

            error = "Location permission is required to fetch weather."

        )

    }

    private fun formatTime(time: Long): String {

        return SimpleDateFormat(
            "hh:mm a",
            Locale.getDefault()
        ).format(Date(time * 1000))

    }

    private fun getWeatherIcon(
        weather: String
    ): Int {

        val hour =
            Calendar.getInstance()
                .get(Calendar.HOUR_OF_DAY)

        return when {

            weather.equals("Rain", true) ->
                R.drawable.rain

            weather.equals("Drizzle", true) ->
                R.drawable.rain

            weather.equals("Thunderstorm", true) ->
                R.drawable.rain

            weather.equals("Clouds", true) ->
                R.drawable.clouds

            weather.equals("Mist", true) ->
                R.drawable.clouds

            weather.equals("Haze", true) ->
                R.drawable.clouds

            weather.equals("Fog", true) ->
                R.drawable.clouds

            weather.equals("Clear", true) && hour >= 18 ->
                R.drawable.moon

            weather.equals("Clear", true) ->
                R.drawable.sun

            else ->
                R.drawable.clouds
        }

    }

}