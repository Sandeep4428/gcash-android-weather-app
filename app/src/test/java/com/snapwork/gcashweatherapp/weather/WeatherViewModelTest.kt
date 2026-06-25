package com.snapwork.gcashweatherapp.weather

import com.snapwork.gcashweatherapp.data.remote.repository.WeatherRepository
import com.snapwork.gcashweatherapp.presentation.weather.WeatherViewModel
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Test

class WeatherViewModelTest {

    private val repository: WeatherRepository = mockk(relaxed = true)

    @Test
    fun weatherViewModel_isCreated() {

        val viewModel = WeatherViewModel(repository)

        assertNotNull(viewModel)

    }

}