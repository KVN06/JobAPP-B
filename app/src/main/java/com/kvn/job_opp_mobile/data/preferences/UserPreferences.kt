package com.kvn.job_opp_mobile.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPreferences(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")
    
    companion object {
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
    }
    
    val userName: Flow<String> = context.dataStore.data
        .catch { exception ->
            emit(emptyPreferences())
        }
        .map { preferences ->
            preferences[USER_NAME_KEY] ?: "Usuario"
        }
    
    suspend fun setUserName(name: String) {
        try {
            context.dataStore.edit { preferences ->
                preferences[USER_NAME_KEY] = name
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
