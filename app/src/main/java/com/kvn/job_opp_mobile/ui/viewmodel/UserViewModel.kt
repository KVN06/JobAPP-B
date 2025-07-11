package com.kvn.job_opp_mobile.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kvn.job_opp_mobile.data.preferences.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(context: Context) : ViewModel() {
    private val userPreferences = UserPreferences(context)
    
    private val _userName = MutableStateFlow("Usuario")
    val userName: StateFlow<String> = _userName.asStateFlow()
    
    init {
        viewModelScope.launch {
            try {
                userPreferences.userName.collect { name ->
                    _userName.value = name
                }
            } catch (e: Exception) {
                _userName.value = "Usuario"
                e.printStackTrace()
            }
        }
    }
    
    fun setUserName(name: String) {
        viewModelScope.launch {
            try {
                userPreferences.setUserName(name)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

class UserViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
