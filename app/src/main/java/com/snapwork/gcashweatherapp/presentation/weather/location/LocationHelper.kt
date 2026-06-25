package com.snapwork.gcashweatherapp.presentation.weather.location


import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocationHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(
        onLocation: (Double, Double) -> Unit
    ) {

        val client =
            LocationServices.getFusedLocationProviderClient(context)

        client.lastLocation.addOnSuccessListener {

            if (it != null) {

                onLocation(
                    it.latitude,
                    it.longitude
                )
            }
        }
    }
}