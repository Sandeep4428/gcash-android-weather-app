package com.snapwork.gcashweatherapp.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text("Home Screen")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { }) {

            Text("Logout")
        }
    }
}