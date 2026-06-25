package com.snapwork.gcashweatherapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_history")
data class WeatherHistoryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val city: String,

    val country: String,

    val temperature: Double,

    val sunrise: Long,

    val sunset: Long,

    val weather: String,

    val createdAt: Long
)