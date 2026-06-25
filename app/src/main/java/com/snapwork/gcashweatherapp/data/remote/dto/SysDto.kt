package com.snapwork.gcashweatherapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SysDto(

    @SerializedName("country")
    val country: String,

    @SerializedName("sunrise")
    val sunrise: Long,

    @SerializedName("sunset")
    val sunset: Long
)