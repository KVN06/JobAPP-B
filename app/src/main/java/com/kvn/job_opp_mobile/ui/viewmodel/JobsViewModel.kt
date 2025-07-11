package com.kvn.job_opp_mobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvn.job_opp_mobile.data.models.Job
import com.kvn.job_opp_mobile.data.repository.JobRepository
import com.kvn.job_opp_mobile.ui.state.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class JobsViewModel : ViewModel() {
    private val repository = JobRepository()
    
    private val _jobsState = MutableStateFlow<UiState<List<Job>>>(UiState.Loading)
    val jobsState: StateFlow<UiState<List<Job>>> = _jobsState.asStateFlow()
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    init {
        loadJobs()
    }
    
    fun loadJobs() {
        viewModelScope.launch {
            _jobsState.value = UiState.Loading
            try {
                // Simulate network delay
                delay(1000)
                val jobs = repository.getAllJobs()
                _jobsState.value = if (jobs.isEmpty()) {
                    UiState.Empty
                } else {
                    UiState.Success(jobs)
                }
            } catch (e: Exception) {
                _jobsState.value = UiState.Error("Error al cargar empleos: ${e.message}")
            }
        }
    }
    
    fun searchJobs(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            _jobsState.value = UiState.Loading
            try {
                delay(500) // Simulate search delay
                val jobs = repository.searchJobs(query)
                _jobsState.value = if (jobs.isEmpty()) {
                    UiState.Empty
                } else {
                    UiState.Success(jobs)
                }
            } catch (e: Exception) {
                _jobsState.value = UiState.Error("Error en la búsqueda: ${e.message}")
            }
        }
    }
    
    fun applyToJob(jobId: String) {
        viewModelScope.launch {
            try {
                // TODO: Implement actual job application logic
                // This would typically involve calling an API
                println("Applying to job: $jobId")
            } catch (e: Exception) {
                // Handle error
                println("Error applying to job: ${e.message}")
            }
        }
    }
}
