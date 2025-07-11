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
import com.kvn.job_opp_mobile.data.repository.JobRepository
import com.kvn.job_opp_mobile.data.models.*
import com.kvn.job_opp_mobile.ui.theme.ThemeUtils
import java.text.NumberFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobDetailScreen(
    jobId: String,
    navController: NavController
) {
    val jobRepository = remember { JobRepository() }
    val job = remember { jobRepository.getJobById(jobId) }
    
    if (job == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Empleo no encontrado")
        }
        return
    }
    
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top bar sin padding extra
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 4.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 1.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = { navController.navigateUp() },
                    modifier = Modifier.padding(0.dp)
                ) {
                    Text(
                        "← Volver",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Text(
                    "Detalle del Empleo",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        
        // Content pegado directamente
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 0.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            // Header card
            JobHeaderCard(job = job)
            
            // Description card
            JobDescriptionCard(job = job)
            
            // Requirements card
            JobRequirementsCard(job = job)
            
            // Benefits card
            JobBenefitsCard(job = job)
            
            // Apply button
            Button(
                onClick = { /* TODO: Implement apply logic */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Postular a este empleo",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun JobHeaderCard(job: Job) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = job.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Text(
                text = job.company,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 4.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Salary
            val formatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL"))
            Text(
                text = "${formatter.format(job.salaryMin)} - ${formatter.format(job.salaryMax)}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = ThemeUtils.getSuccessColor()
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = job.location,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Tags
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                JobTag(
                    text = when(job.type) {
                        JobType.FULL_TIME -> "Tiempo Completo"
                        JobType.PART_TIME -> "Tiempo Parcial"
                        JobType.CONTRACT -> "Contrato"
                        JobType.INTERNSHIP -> "Práctica"
                        JobType.FREELANCE -> "Freelance"
                    },
                    backgroundColor = MaterialTheme.colorScheme.surfaceVariant
                )
                
                if (job.isRemote) {
                    JobTag(
                        text = "Remoto",
                        backgroundColor = ThemeUtils.getRemoteJobColor().copy(alpha = 0.1f),
                        textColor = ThemeUtils.getRemoteJobColor()
                    )
                }
                
                if (job.isUrgent) {
                    JobTag(
                        text = "Urgente",
                        backgroundColor = ThemeUtils.getUrgentJobColor().copy(alpha = 0.1f),
                        textColor = ThemeUtils.getUrgentJobColor()
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Posted date and deadline
            Column {
                Text(
                    text = "Publicado: ${job.postedDate}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
                
                job.applicationDeadline?.let { deadline ->
                    Text(
                        text = "Fecha límite: $deadline",
                        style = MaterialTheme.typography.bodySmall,
                        color = ThemeUtils.getUrgentJobColor()
                    )
                }
            }
        }
    }
}

@Composable
fun JobDescriptionCard(job: Job) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Descripción del Puesto",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = job.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
            )
        }
    }
}

@Composable
fun JobRequirementsCard(job: Job) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Requisitos",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            job.requirements.forEach { requirement ->
                Row(
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Text(
                        text = "• ",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = requirement,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}

@Composable
fun JobBenefitsCard(job: Job) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Beneficios",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            job.benefits.forEach { benefit ->
                Row(
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Text(
                        text = "✓ ",
                        style = MaterialTheme.typography.bodyLarge,
                        color = ThemeUtils.getSuccessColor()
                    )
                    Text(
                        text = benefit,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}
