package com.snapwork.gcashweatherapp.data.preferences


import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("session_pref")

class SessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    val isLoggedIn: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[IS_LOGGED_IN] ?: false
        }

    suspend fun saveLogin() {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = true
        }
    }

    suspend fun logout() {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = false
        }
    }
}