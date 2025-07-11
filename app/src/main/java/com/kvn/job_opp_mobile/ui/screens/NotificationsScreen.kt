package com.kvn.job_opp_mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(navController: NavController) {
    val notifications = remember { getSampleNotifications() }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                text = "Notificaciones",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        
        item {
            NotificationFilters()
            Spacer(modifier = Modifier.height(16.dp))
        }
        
        items(notifications) { notification ->
            NotificationItem(
                notification = notification,
                onMarkAsRead = { /* TODO */ },
                onDelete = { /* TODO */ }
            )
        }
    }
}

@Composable
fun NotificationFilters() {
    var selectedFilter by remember { mutableStateOf("Todas") }
    val filters = listOf("Todas", "Empleos", "Capacitaciones", "Clasificados")
    
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filters) { filter ->
            FilterChip(
                selected = selectedFilter == filter,
                onClick = { selectedFilter = filter },
                label = { Text(filter) }
            )
        }
    }
}

@Composable
fun NotificationItem(
    notification: NotificationData,
    onMarkAsRead: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Icono de la notificación sin fondo de color
            Icon(
                imageVector = when (notification.type) {
                    NotificationType.JOB -> Icons.Default.Work
                    NotificationType.TRAINING -> Icons.Default.School
                    NotificationType.CLASSIFIED -> Icons.AutoMirrored.Filled.Assignment
                    NotificationType.SYSTEM -> Icons.Default.Info
                },
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            // Contenido de la notificación
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = if (notification.isRead) FontWeight.Normal else FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = notification.message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = formatTimeAgo(notification.timestamp),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                    
                    if (!notification.isRead) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(MaterialTheme.colorScheme.primary)
                        )
                    }
                }
            }
            
            // Botón de opciones
            IconButton(
                onClick = { /* TODO: Mostrar menú de opciones */ }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Más opciones",
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Composable
fun formatTimeAgo(timestamp: LocalDateTime): String {
    val now = LocalDateTime.now()
    val diff = java.time.Duration.between(timestamp, now)
    
    return when {
        diff.toDays() > 0 -> "${diff.toDays()} día${if (diff.toDays() > 1) "s" else ""}"
        diff.toHours() > 0 -> "${diff.toHours()} hora${if (diff.toHours() > 1) "s" else ""}"
        diff.toMinutes() > 0 -> "${diff.toMinutes()} minuto${if (diff.toMinutes() > 1) "s" else ""}"
        else -> "Ahora"
    }
}

// Datos de ejemplo
fun getSampleNotifications(): List<NotificationData> {
    return listOf(
        NotificationData(
            id = "1",
            title = "Nueva oferta de trabajo",
            message = "Se ha publicado una nueva oferta para Desarrollador Android Senior que coincide con tu perfil.",
            type = NotificationType.JOB,
            timestamp = LocalDateTime.now().minusHours(2),
            isRead = false
        ),
        NotificationData(
            id = "2",
            title = "Curso recomendado",
            message = "Nuevo curso de Kotlin Avanzado disponible. Mejora tus habilidades con este contenido premium.",
            type = NotificationType.TRAINING,
            timestamp = LocalDateTime.now().minusHours(5),
            isRead = false
        ),
        NotificationData(
            id = "3",
            title = "Aplicación actualizada",
            message = "Tu aplicación para 'Programador Mobile' ha sido vista por el reclutador.",
            type = NotificationType.JOB,
            timestamp = LocalDateTime.now().minusHours(8),
            isRead = true
        ),
        NotificationData(
            id = "4",
            title = "Clasificado publicado",
            message = "Tu clasificado 'Servicios de Desarrollo' ha sido publicado exitosamente.",
            type = NotificationType.CLASSIFIED,
            timestamp = LocalDateTime.now().minusDays(1),
            isRead = true
        ),
        NotificationData(
            id = "5",
            title = "Recordatorio",
            message = "Completa tu perfil para aumentar tus posibilidades de conseguir empleo.",
            type = NotificationType.SYSTEM,
            timestamp = LocalDateTime.now().minusDays(2),
            isRead = false
        )
    )
}

data class NotificationData(
    val id: String,
    val title: String,
    val message: String,
    val type: NotificationType,
    val timestamp: LocalDateTime,
    val isRead: Boolean
)

enum class NotificationType {
    JOB, TRAINING, CLASSIFIED, SYSTEM
}