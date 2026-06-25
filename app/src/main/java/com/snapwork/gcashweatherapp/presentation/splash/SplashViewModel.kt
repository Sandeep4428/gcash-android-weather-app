package com.snapwork.gcashweatherapp.presentation.splash


import androidx.lifecycle.ViewModel
import com.snapwork.gcashweatherapp.data.preferences.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import androidx.lifecycle.viewModelScope
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    sessionManager: SessionManager
) : ViewModel() {

    val isLoggedIn: StateFlow<Boolean> =
        sessionManager.isLoggedIn.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = false
        )
}