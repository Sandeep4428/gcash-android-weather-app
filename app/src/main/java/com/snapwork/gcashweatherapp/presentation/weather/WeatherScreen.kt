package com.snapwork.gcashweatherapp.presentation.weather

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.snapwork.gcashweatherapp.R
import com.snapwork.gcashweatherapp.presentation.weather.location.LocationHelper

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    locationHelper: LocationHelper,
    viewModel: WeatherViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    val launcher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->

            if (granted) {

                locationHelper.getCurrentLocation { lat, lon ->

                    viewModel.getWeather(lat, lon)

                }

            } else {

                viewModel.showLocationPermissionError()

            }

        }

    LaunchedEffect(Unit) {

        launcher.launch(
            Manifest.permission.ACCESS_FINE_LOCATION
        )

    }

    if (state.error != null) {

        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = state.error!!,
                    color = MaterialTheme.colorScheme.error
                )

                Spacer(modifier = Modifier.height(16.dp))

                androidx.compose.material3.Button(

                    onClick = {

                        launcher.launch(
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    }

                ) {

                    Text("Retry")

                }
            }
        }
        return
    }



    if (state.loading) {

        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CircularProgressIndicator()

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Fetching weather..."
                )

            }

        }

    } else {

        Column(

            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Card(

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(20.dp),

                elevation = CardDefaults.cardElevation(8.dp)

            ) {

                Column(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),

                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Image(

                        painter = painterResource(state.weatherIcon),

                        contentDescription = null,

                        modifier = Modifier.size(120.dp)

                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(

                        text = state.weather,

                        fontSize = 26.sp,

                        fontWeight = FontWeight.Bold

                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            painter = painterResource(R.drawable.location),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(

                            text = "${state.city}, ${state.country}",

                            style = MaterialTheme.typography.titleMedium

                        )

                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            painter = painterResource(R.drawable.temprature),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(

                            text = state.temperature,

                            fontSize = 34.sp,

                            fontWeight = FontWeight.Bold

                        )

                    }

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceEvenly

            ) {

                Card(

                    modifier = Modifier.weight(1f),

                    shape = RoundedCornerShape(16.dp)

                ) {

                    Column(

                        modifier = Modifier
                            .padding(16.dp),

                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        Image(

                            painter = painterResource(R.drawable.sunrise),

                            contentDescription = null,

                            modifier = Modifier.size(50.dp)

                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(

                            "Sunrise",

                            fontWeight = FontWeight.Bold

                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(state.sunrise)

                    }

                }

                Spacer(modifier = Modifier.width(12.dp))

                Card(

                    modifier = Modifier.weight(1f),

                    shape = RoundedCornerShape(16.dp)

                ) {

                    Column(

                        modifier = Modifier
                            .padding(16.dp),

                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        Image(

                            painter = painterResource(R.drawable.sunset),

                            contentDescription = null,

                            modifier = Modifier.size(50.dp)

                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(

                            "Sunset",

                            fontWeight = FontWeight.Bold

                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(state.sunset)

                    }

                }

            }

        }

    }

}