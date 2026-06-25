package com.snapwork.gcashweatherapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.snapwork.gcashweatherapp.R
import com.snapwork.gcashweatherapp.presentation.history.HistoryScreen
import com.snapwork.gcashweatherapp.presentation.weather.WeatherScreen
import com.snapwork.gcashweatherapp.presentation.weather.location.LocationHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onLogout: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    var selectedTab by remember {
        mutableIntStateOf(0)
    }

    val context = LocalContext.current

    val locationHelper = remember(context) {
        LocationHelper(context)
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Column {

                        Text(

                            if (selectedTab == 0)
                                "Current Weather"
                            else
                                "Weather History"

                        )

                        Text(

                            if (selectedTab == 0)
                                "Live weather updates"
                            else
                                "Stored weather records",

                            style = MaterialTheme.typography.labelSmall

                        )

                    }

                },

                actions = {

                    IconButton(

                        onClick = {

                            viewModel.logout()

                            onLogout()

                        }

                    ) {

                        Image(
                            painter = painterResource(R.drawable.logout),
                            contentDescription = "Logout",
                            modifier = Modifier.size(22.dp)
                        )

                    }

                },

                colors = TopAppBarDefaults.topAppBarColors()

            )

        },

        bottomBar = {

            NavigationBar {

                NavigationBarItem(

                    selected = selectedTab == 0,

                    onClick = {

                        selectedTab = 0

                    },

                    icon = {

                        Image(
                            painter = painterResource(R.drawable.current),
                            contentDescription = "Current",
                            modifier = Modifier.size(20.dp)
                        )

                    },

                    label = {

                        Text("Current")

                    },

                    colors = NavigationBarItemDefaults.colors(

                        selectedIconColor = Color(0xFF0066CC),
                        selectedTextColor = Color(0xFF0066CC),

                        indicatorColor = Color(0xFFE3F2FD)

                    )

                )

                NavigationBarItem(

                    selected = selectedTab == 1,

                    onClick = {

                        selectedTab = 1

                    },

                    icon = {

                        Image(
                            painter = painterResource(R.drawable.history),
                            contentDescription = "History",
                            modifier = Modifier.size(20.dp)
                        )

                    },

                    label = {

                        Text("History")

                    },

                    colors = NavigationBarItemDefaults.colors(

                        selectedIconColor = Color(0xFF0066CC),
                        selectedTextColor = Color(0xFF0066CC),

                        indicatorColor = Color(0xFFE3F2FD)

                    )

                )

            }

        }

    ) { innerPadding ->

        when (selectedTab) {

            0 -> {

                WeatherScreen(

                    modifier = Modifier.padding(innerPadding),

                    locationHelper = locationHelper

                )

            }

            1 -> {

                HistoryScreen(

                    modifier = Modifier.padding(innerPadding)

                )

            }

        }

    }

}