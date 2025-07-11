package com.kvn.job_opp_mobile.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kvn.job_opp_mobile.ui.viewmodel.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    themeViewModel: ThemeViewModel
) {
    val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Configuración",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        
        // Sección de Apariencia
        item {
            SettingsSection(title = "Apariencia") {
                SettingsItem(
                    icon = if (isDarkTheme) Icons.Default.DarkMode else Icons.Default.LightMode,
                    title = "Tema",
                    subtitle = if (isDarkTheme) "Modo oscuro" else "Modo claro",
                    trailing = {
                        Switch(
                            checked = isDarkTheme,
                            onCheckedChange = { themeViewModel.toggleTheme() }
                        )
                    }
                )
            }
        }
        
        // Sección de Notificaciones
        item {
            SettingsSection(title = "Notificaciones") {
                SettingsItem(
                    icon = Icons.Default.Notifications,
                    title = "Notificaciones push",
                    subtitle = "Recibir notificaciones de nuevos empleos",
                    trailing = {
                        Switch(
                            checked = true,
                            onCheckedChange = { /* TODO */ }
                        )
                    }
                )
                
                SettingsItem(
                    icon = Icons.Default.Email,
                    title = "Notificaciones por email",
                    subtitle = "Recibir resumen semanal por correo",
                    trailing = {
                        Switch(
                            checked = false,
                            onCheckedChange = { /* TODO */ }
                        )
                    }
                )
            }
        }
        
        // Sección de Privacidad
        item {
            SettingsSection(title = "Privacidad y Seguridad") {
                SettingsItem(
                    icon = Icons.Default.Lock,
                    title = "Privacidad",
                    subtitle = "Gestionar configuración de privacidad",
                    onClick = { /* TODO */ }
                )
                
                SettingsItem(
                    icon = Icons.Default.Security,
                    title = "Seguridad",
                    subtitle = "Cambiar contraseña y configuración de seguridad",
                    onClick = { /* TODO */ }
                )
            }
        }
        
        // Sección de Aplicación
        item {
            SettingsSection(title = "Aplicación") {
                SettingsItem(
                    icon = Icons.Default.Info,
                    title = "Acerca de",
                    subtitle = "Versión 1.0 - Job Opportunity App",
                    onClick = { /* TODO */ }
                )
                
                SettingsItem(
                    icon = Icons.Default.Help,
                    title = "Ayuda y soporte",
                    subtitle = "Obtener ayuda o contactar soporte",
                    onClick = { /* TODO */ }
                )
                
                SettingsItem(
                    icon = Icons.Default.Star,
                    title = "Calificar la app",
                    subtitle = "Deja tu calificación en la tienda",
                    onClick = { /* TODO */ }
                )
            }
        }
        
        // Sección de Cuenta
        item {
            SettingsSection(title = "Cuenta") {
                SettingsItem(
                    icon = Icons.Default.ExitToApp,
                    title = "Cerrar sesión",
                    subtitle = "Cerrar sesión de tu cuenta",
                    onClick = { /* TODO */ },
                    isDestructive = true
                )
            }
        }
    }
}

@Composable
fun SettingsSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
fun SettingsItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    trailing: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
    isDestructive: Boolean = false
) {
    val colors = if (isDestructive) {
        MaterialTheme.colorScheme.error
    } else {
        MaterialTheme.colorScheme.onSurface
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        ),
        onClick = onClick ?: {}
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colors,
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = colors,
                    fontWeight = FontWeight.Medium
                )
                
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.copy(alpha = 0.7f)
                )
            }
            
            if (trailing != null) {
                Spacer(modifier = Modifier.width(8.dp))
                trailing()
            } else if (onClick != null) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = colors.copy(alpha = 0.5f)
                )
            }
        }
    }
}
