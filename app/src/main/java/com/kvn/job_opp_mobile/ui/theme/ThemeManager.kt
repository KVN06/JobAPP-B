package com.kvn.job_opp_mobile.ui.theme

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class ThemeManager(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "theme_preferences")
    
    companion object {
        private val DARK_THEME_KEY = booleanPreferencesKey("dark_theme")
    }
    
    val isDarkTheme: Flow<Boolean> = context.dataStore.data
        .catch { exception ->
            // En caso de error, emitir el valor por defecto
            emit(androidx.datastore.preferences.core.emptyPreferences())
        }
        .map { preferences ->
            preferences[DARK_THEME_KEY] ?: false
        }
    
    suspend fun setDarkTheme(isDark: Boolean) {
        try {
            context.dataStore.edit { preferences ->
                preferences[DARK_THEME_KEY] = isDark
            }
        } catch (e: Exception) {
            // Manejar error silenciosamente o loguearlo
            e.printStackTrace()
        }
    }
}
