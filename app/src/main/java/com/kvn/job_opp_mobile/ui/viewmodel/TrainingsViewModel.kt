package com.kvn.job_opp_mobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvn.job_opp_mobile.data.models.Training
import com.kvn.job_opp_mobile.data.repository.TrainingRepository
import com.kvn.job_opp_mobile.ui.state.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TrainingsViewModel : ViewModel() {
    private val repository = TrainingRepository()
    
    private val _trainingsState = MutableStateFlow<UiState<List<Training>>>(UiState.Loading)
    val trainingsState: StateFlow<UiState<List<Training>>> = _trainingsState.asStateFlow()
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    init {
        loadTrainings()
    }
    
    fun loadTrainings() {
        viewModelScope.launch {
            _trainingsState.value = UiState.Loading
            try {
                delay(1000)
                val trainings = repository.getAllTrainings()
                _trainingsState.value = if (trainings.isEmpty()) {
                    UiState.Empty
                } else {
                    UiState.Success(trainings)
                }
            } catch (e: Exception) {
                _trainingsState.value = UiState.Error("Error al cargar capacitaciones: ${e.message}")
            }
        }
    }
    
    fun searchTrainings(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            _trainingsState.value = UiState.Loading
            try {
                delay(500)
                val trainings = repository.searchTrainings(query)
                _trainingsState.value = if (trainings.isEmpty()) {
                    UiState.Empty
                } else {
                    UiState.Success(trainings)
                }
            } catch (e: Exception) {
                _trainingsState.value = UiState.Error("Error en la búsqueda: ${e.message}")
            }
        }
    }
    
    fun enrollInTraining(trainingId: String) {
        viewModelScope.launch {
            try {
                // TODO: Implement actual enrollment logic
                println("Enrolling in training: $trainingId")
            } catch (e: Exception) {
                println("Error enrolling in training: ${e.message}")
            }
        }
    }
}
