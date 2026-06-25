package com.snapwork.gcashweatherapp.data.remote.api


import com.snapwork.gcashweatherapp.data.remote.dto.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(

        @Query("lat")
        latitude: Double,

        @Query("lon")
        longitude: Double,

        @Query("appid")
        apiKey: String,

        @Query("units")
        units: String = "metric"

    ): WeatherResponse
}