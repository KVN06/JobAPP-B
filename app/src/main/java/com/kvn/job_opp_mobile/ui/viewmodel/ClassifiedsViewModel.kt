package com.kvn.job_opp_mobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvn.job_opp_mobile.data.models.ClassifiedJob
import com.kvn.job_opp_mobile.data.repository.ClassifiedJobRepository
import com.kvn.job_opp_mobile.ui.state.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClassifiedsViewModel : ViewModel() {
    private val repository = ClassifiedJobRepository()
    
    private val _classifiedsState = MutableStateFlow<UiState<List<ClassifiedJob>>>(UiState.Loading)
    val classifiedsState: StateFlow<UiState<List<ClassifiedJob>>> = _classifiedsState.asStateFlow()
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    init {
        loadClassifieds()
    }
    
    fun loadClassifieds() {
        viewModelScope.launch {
            _classifiedsState.value = UiState.Loading
            try {
                delay(1000)
                val classifieds = repository.getAllClassifiedJobs()
                _classifiedsState.value = if (classifieds.isEmpty()) {
                    UiState.Empty
                } else {
                    UiState.Success(classifieds)
                }
            } catch (e: Exception) {
                _classifiedsState.value = UiState.Error("Error al cargar clasificados: ${e.message}")
            }
        }
    }
    
    fun searchClassifieds(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            _classifiedsState.value = UiState.Loading
            try {
                delay(500)
                val classifieds = repository.searchClassifiedJobs(query)
                _classifiedsState.value = if (classifieds.isEmpty()) {
                    UiState.Empty
                } else {
                    UiState.Success(classifieds)
                }
            } catch (e: Exception) {
                _classifiedsState.value = UiState.Error("Error en la búsqueda: ${e.message}")
            }
        }
    }
    
    fun contactForClassified(classifiedId: String) {
        viewModelScope.launch {
            try {
                // TODO: Implement actual contact logic
                println("Contacting for classified: $classifiedId")
            } catch (e: Exception) {
                println("Error contacting for classified: ${e.message}")
            }
        }
    }
}
