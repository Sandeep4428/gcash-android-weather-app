package com.snapwork.gcashweatherapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class WeatherResponse(

    @SerializedName("name")
    val city: String,

    @SerializedName("main")
    val main: MainDto,

    @SerializedName("sys")
    val sys: SysDto,

    @SerializedName("weather")
    val weather: List<WeatherDto>
)