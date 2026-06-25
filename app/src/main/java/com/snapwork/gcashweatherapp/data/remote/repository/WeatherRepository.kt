package com.snapwork.gcashweatherapp.data.remote.repository

import com.snapwork.gcashweatherapp.BuildConfig
import com.snapwork.gcashweatherapp.data.local.dao.WeatherHistoryDao
import com.snapwork.gcashweatherapp.data.local.entity.WeatherHistoryEntity
import com.snapwork.gcashweatherapp.data.remote.api.WeatherApi
import com.snapwork.gcashweatherapp.data.remote.dto.WeatherResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApi,
    private val dao: WeatherHistoryDao
) {

    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherResponse {

        val response = api.getCurrentWeather(
            latitude = latitude,
            longitude = longitude,
            apiKey = BuildConfig.OPEN_WEATHER_API_KEY
        )

        dao.insert(
            WeatherHistoryEntity(
                city = response.city,
                country = response.sys.country,
                temperature = response.main.temperature,
                sunrise = response.sys.sunrise,
                sunset = response.sys.sunset,
                weather = response.weather.first().main,
                createdAt = System.currentTimeMillis()
            )
        )

        return response
    }

    fun getWeatherHistory(): Flow<List<WeatherHistoryEntity>> {
        return dao.getHistory()
    }
}