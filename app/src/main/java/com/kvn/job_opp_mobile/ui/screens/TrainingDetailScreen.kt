package com.kvn.job_opp_mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kvn.job_opp_mobile.data.repository.TrainingRepository
import com.kvn.job_opp_mobile.data.models.*
import com.kvn.job_opp_mobile.ui.theme.ThemeUtils
import java.text.NumberFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingDetailScreen(
    trainingId: String,
    navController: NavController
) {
    val trainingRepository = remember { TrainingRepository() }
    val training = remember { trainingRepository.getTrainingById(trainingId) }
    
    if (training == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Capacitación no encontrada")
        }
        return
    }
    
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top bar
        TopAppBar(
            title = { Text("Detalle de Capacitación") },
            navigationIcon = {
                TextButton(
                    onClick = { navController.navigateUp() }
                ) {
                    Text("← Volver")
                }
            }
        )
        
        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header card
            TrainingHeaderCard(training = training)
            
            // Description card
            TrainingDescriptionCard(training = training)
            
            // Prerequisites card
            TrainingPrerequisitesCard(training = training)
            
            // Learning outcomes card
            TrainingOutcomesCard(training = training)
            
            // Instructor card
            TrainingInstructorCard(training = training)
            
            // Enroll button
            Button(
                onClick = { /* TODO: Implement enroll logic */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = if (training.isFree) "Inscribirse Gratis" else "Inscribirse (${NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL")).format(training.price)})",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
            
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun TrainingHeaderCard(training: Training) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = training.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )
            
            Text(
                text = training.provider,
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF6B7280),
                modifier = Modifier.padding(top = 4.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Price and rating row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (training.isFree) "Gratis" else NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL")).format(training.price),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = if (training.isFree) Color(0xFF059669) else Color(0xFF374151)
                )
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "⭐",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "${training.rating}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF374151)
                    )
                    Text(
                        text = " (${training.enrolledStudents} estudiantes)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF6B7280)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Duration and format
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Duración",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color(0xFF6B7280)
                    )
                    Text(
                        text = training.duration,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF374151)
                    )
                }
                
                Column {
                    Text(
                        text = "Modalidad",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color(0xFF6B7280)
                    )
                    Text(
                        text = when(training.format) {
                            TrainingFormat.ONLINE -> "Online"
                            TrainingFormat.PRESENTIAL -> "Presencial"
                            TrainingFormat.HYBRID -> "Híbrido"
                            TrainingFormat.SELF_PACED -> "A tu ritmo"
                        },
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF374151)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Dates
            training.startDate?.let { startDate ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Inicio",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color(0xFF6B7280)
                        )
                        Text(
                            text = startDate,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF374151)
                        )
                    }
                    
                    training.endDate?.let { endDate ->
                        Column {
                            Text(
                                text = "Fin",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color(0xFF6B7280)
                            )
                            Text(
                                text = endDate,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF374151)
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Tags
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TrainingTag(
                    text = when(training.level) {
                        TrainingLevel.BEGINNER -> "Principiante"
                        TrainingLevel.INTERMEDIATE -> "Intermedio"
                        TrainingLevel.ADVANCED -> "Avanzado"
                        TrainingLevel.EXPERT -> "Experto"
                    },
                    backgroundColor = Color(0xFFDDD6FE)
                )
                
                if (training.certificateProvided) {
                    TrainingTag(
                        text = "Certificado",
                        backgroundColor = Color(0xFFFEF3C7)
                    )
                }
                
                if (training.isFree) {
                    TrainingTag(
                        text = "Gratis",
                        backgroundColor = Color(0xFFDCFCE7),
                        textColor = Color(0xFF059669)
                    )
                }
            }
        }
    }
}

@Composable
fun TrainingDescriptionCard(training: Training) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Descripción",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = training.description,
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF374151),
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
            )
        }
    }
}

@Composable
fun TrainingPrerequisitesCard(training: Training) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Requisitos Previos",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            training.prerequisites.forEach { prerequisite ->
                Row(
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Text(
                        text = "• ",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF3B82F6)
                    )
                    Text(
                        text = prerequisite,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF374151)
                    )
                }
            }
        }
    }
}

@Composable
fun TrainingOutcomesCard(training: Training) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Lo que Aprenderás",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            training.learningOutcomes.forEach { outcome ->
                Row(
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Text(
                        text = "✓ ",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF059669)
                    )
                    Text(
                        text = outcome,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF374151)
                    )
                }
            }
        }
    }
}

@Composable
fun TrainingInstructorCard(training: Training) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Instructor",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    color = Color(0xFF3B82F6)
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "👨‍🏫",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Text(
                    text = training.instructor,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF374151)
                )
            }
        }
    }
}
