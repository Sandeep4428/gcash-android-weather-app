package com.snapwork.gcashweatherapp.presentation.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.snapwork.gcashweatherapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel()
) {

    val history by viewModel.history.collectAsState()

    if (history.isEmpty()) {

        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "No Weather History Available",
                style = MaterialTheme.typography.titleMedium
            )

        }

    } else {

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(history) { item ->

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        // Location

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Image(
                                painter = painterResource(R.drawable.location),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "${item.city}, ${item.country}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )

                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Temperature

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Image(
                                painter = painterResource(R.drawable.temprature),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "${item.temperature} °C",
                                style = MaterialTheme.typography.bodyLarge
                            )

                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Weather : ${item.weather}",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Divider()

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            // Sunrise

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Image(
                                    painter = painterResource(R.drawable.sunrise),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )

                                Spacer(modifier = Modifier.width(6.dp))

                                Column {

                                    Text(
                                        text = "Sunrise",
                                        style = MaterialTheme.typography.labelSmall
                                    )

                                    Text(
                                        text = formatUnixTime(item.sunrise),
                                        style = MaterialTheme.typography.bodySmall
                                    )

                                }

                            }

                            // Sunset

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Image(
                                    painter = painterResource(R.drawable.sunset),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )

                                Spacer(modifier = Modifier.width(6.dp))

                                Column {

                                    Text(
                                        text = "Sunset",
                                        style = MaterialTheme.typography.labelSmall
                                    )

                                    Text(
                                        text = formatUnixTime(item.sunset),
                                        style = MaterialTheme.typography.bodySmall
                                    )

                                }

                            }

                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Divider()

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Viewed At",
                            style = MaterialTheme.typography.labelMedium
                        )

                        Text(
                            text = formatDate(item.createdAt),
                            style = MaterialTheme.typography.bodyMedium
                        )

                    }

                }

            }

        }

    }

}

private fun formatUnixTime(time: Long): String {

    return SimpleDateFormat(
        "hh:mm a",
        Locale.getDefault()
    ).format(Date(time * 1000))

}

private fun formatDate(time: Long): String {

    return SimpleDateFormat(
        "dd MMM yyyy  hh:mm a",
        Locale.getDefault()
    ).format(Date(time))

}