package com.kvn.job_opp_mobile.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kvn.job_opp_mobile.ui.theme.ThemeManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ThemeViewModel(context: Context) : ViewModel() {
    private val themeManager = ThemeManager(context)
    
    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()
    
    init {
        viewModelScope.launch {
            try {
                themeManager.isDarkTheme.collect { darkTheme ->
                    _isDarkTheme.value = darkTheme
                }
            } catch (e: Exception) {
                // En caso de error, mantener el valor por defecto
                _isDarkTheme.value = false
                e.printStackTrace()
            }
        }
    }
    
    fun toggleTheme() {
        viewModelScope.launch {
            try {
                themeManager.setDarkTheme(!_isDarkTheme.value)
            } catch (e: Exception) {
                // Manejar error silenciosamente
                e.printStackTrace()
            }
        }
    }
}

class ThemeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThemeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ThemeViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
