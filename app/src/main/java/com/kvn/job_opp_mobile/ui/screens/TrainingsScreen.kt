package com.kvn.job_opp_mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kvn.job_opp_mobile.ui.components.*
import com.kvn.job_opp_mobile.ui.navigation.Screen
import com.kvn.job_opp_mobile.ui.state.UiState
import com.kvn.job_opp_mobile.ui.viewmodel.TrainingsViewModel
import com.kvn.job_opp_mobile.ui.theme.ThemeUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingsScreen(
    navController: NavController,
    viewModel: TrainingsViewModel = viewModel()
) {
    val trainingsState by viewModel.trainingsState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top bar
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 2.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Capacitaciones",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Search bar
                SearchBar(
                    query = searchQuery,
                    onQueryChange = { /* Update on text change but don't search immediately */ },
                    onSearch = { query -> viewModel.searchTrainings(query) },
                    placeholder = "Buscar capacitaciones..."
                )
            }
        }
        
        // Content based on state
        Box(modifier = Modifier.fillMaxSize()) {
            val currentState = trainingsState
            when (currentState) {
                is UiState.Loading -> {
                    LoadingIndicator(
                        message = if (searchQuery.isEmpty()) "Cargando capacitaciones..." else "Buscando capacitaciones..."
                    )
                }
                
                is UiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(currentState.data) { training ->
                            FullTrainingCard(
                                training = training,
                                onClick = {
                                    navController.navigate("${Screen.TrainingDetail.route}/${training.id}")
                                },
                                onEnroll = {
                                    viewModel.enrollInTraining(training.id)
                                }
                            )
                        }
                    }
                }
                
                is UiState.Error -> {
                    Box(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        ErrorMessage(
                            message = currentState.message,
                            onRetry = { viewModel.loadTrainings() }
                        )
                    }
                }
                
                is UiState.Empty -> {
                    EmptyState(
                        message = if (searchQuery.isEmpty()) {
                            "No hay capacitaciones disponibles"
                        } else {
                            "No se encontraron capacitaciones"
                        },
                        description = if (searchQuery.isEmpty()) {
                            "Aún no hay cursos publicados."
                        } else {
                            "Intenta con otros términos de búsqueda."
                        },
                        onAction = { viewModel.loadTrainings() },
                        actionText = "Recargar"
                    )
                }
            }
        }
    }
}
