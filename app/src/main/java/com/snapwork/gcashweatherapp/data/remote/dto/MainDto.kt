package com.snapwork.gcashweatherapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MainDto(

    @SerializedName("temp")
    val temperature: Double
)