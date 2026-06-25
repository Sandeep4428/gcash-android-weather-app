package com.snapwork.gcashweatherapp.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snapwork.gcashweatherapp.data.local.entity.WeatherHistoryEntity
import com.snapwork.gcashweatherapp.data.remote.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    val history: StateFlow<List<WeatherHistoryEntity>> =
        repository.getWeatherHistory()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun clearHistory() {

        viewModelScope.launch {

            repository.clearHistory()

        }

    }

}