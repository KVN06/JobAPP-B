package com.kvn.job_opp_mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kvn.job_opp_mobile.data.repository.*
import com.kvn.job_opp_mobile.ui.navigation.Screen
import com.kvn.job_opp_mobile.ui.theme.*
import com.kvn.job_opp_mobile.ui.viewmodel.UserViewModel
import com.kvn.job_opp_mobile.ui.viewmodel.UserViewModelFactory
// Importaciones adicionales necesarias
import com.kvn.job_opp_mobile.data.models.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val jobRepository = remember { JobRepository() }
    val trainingRepository = remember { TrainingRepository() }
    val classifiedRepository = remember { ClassifiedJobRepository() }
    
    val urgentJobs = remember { jobRepository.getUrgentJobs().take(5) }
    val popularTrainings = remember { trainingRepository.getPopularTrainings().take(5) }
    val recentClassifieds = remember { classifiedRepository.getRecentClassifieds().take(5) }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Header de bienvenida mejorado
        item {
            WelcomeHeaderEnhanced(navController)
        }
        
        // Estadísticas rápidas mejoradas
        item {
            QuickStatsEnhanced(
                totalJobs = jobRepository.getAllJobs().size,
                urgentJobs = jobRepository.getUrgentJobs().size,
                remoteJobs = jobRepository.getRemoteJobs().size,
                totalTrainings = trainingRepository.getAllTrainings().size
            )
        }
        
        // Acciones rápidas
        item {
            QuickActionsGrid(navController = navController)
        }
        
        // Empleos urgentes
        item {
            SectionHeader(
                title = "Empleos Urgentes",
                subtitle = "${urgentJobs.size} oportunidades disponibles",
                onViewAllClick = { navController.navigate(Screen.Jobs.route) },
                icon = Icons.Default.Work,
                color = ThemeUtils.getUrgentJobColor()
            )
        }
        
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                items(urgentJobs) { job ->
                    JobCard(
                        job = job,
                        onClick = { 
                            navController.navigate("${Screen.JobDetail.route}/${job.id}")
                        }
                    )
                }
            }
        }
        
        // Capacitaciones populares
        item {
            SectionHeader(
                title = "Capacitaciones Populares",
                subtitle = "${popularTrainings.size} cursos destacados",
                onViewAllClick = { navController.navigate(Screen.Trainings.route) },
                icon = Icons.Default.School,
                color = ThemeUtils.getPopularTrainingColor()
            )
        }
        
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                items(popularTrainings) { training ->
                    TrainingCard(
                        training = training,
                        onClick = { 
                            navController.navigate("${Screen.TrainingDetail.route}/${training.id}")
                        }
                    )
                }
            }
        }
        
        // Trabajos clasificados recientes
        item {
            SectionHeader(
                title = "Clasificados Recientes",
                subtitle = "${recentClassifieds.size} publicaciones nuevas",
                onViewAllClick = { navController.navigate(Screen.Classifieds.route) },
                icon = Icons.Default.Assignment,
                color = ThemeUtils.getNewClassifiedColor()
            )
        }
        
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                items(recentClassifieds) { classified ->
                    ClassifiedCard(
                        classified = classified,
                        onClick = { 
                            navController.navigate("${Screen.ClassifiedDetail.route}/${classified.id}")
                        }
                    )
                }
            }
        }
        
        // Sección de consejos
        item {
            TipsSection()
        }
    }
}

@Composable
fun WelcomeHeaderEnhanced(navController: NavController) {
    val context = LocalContext.current
    val userViewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(context)
    )
    val userName by userViewModel.userName.collectAsState()
    
    // Establecer un nombre por defecto si está vacío
    LaunchedEffect(Unit) {
        if (userName == "Usuario") {
            userViewModel.setUserName("VIBEFKS TECH")
        }
    }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "¡Hola, $userName!",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Encuentra tu próxima oportunidad",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )
                }
                
                Icon(
                    imageVector = Icons.Default.WavingHand,
                    contentDescription = "Saludo",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(32.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.TrendingUp,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Tu perfil tiene un 85% de completitud",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun QuickStatsEnhanced(
    totalJobs: Int,
    urgentJobs: Int,
    remoteJobs: Int,
    totalTrainings: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Estadísticas",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Icon(
                    imageVector = Icons.Default.Analytics,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatCardEnhanced(
                    icon = Icons.Default.Work,
                    value = totalJobs.toString(),
                    label = "Empleos",
                    color = MaterialTheme.colorScheme.primary
                )
                StatCardEnhanced(
                    icon = Icons.Default.AccessTime,
                    value = urgentJobs.toString(),
                    label = "Urgentes",
                    color = ThemeUtils.getUrgentJobColor()
                )
                StatCardEnhanced(
                    icon = Icons.Default.Home,
                    value = remoteJobs.toString(),
                    label = "Remotos",
                    color = ThemeUtils.getRemoteJobColor()
                )
                StatCardEnhanced(
                    icon = Icons.Default.School,
                    value = totalTrainings.toString(),
                    label = "Cursos",
                    color = ThemeUtils.getPopularTrainingColor()
                )
            }
        }
    }
}

@Composable
fun StatCardEnhanced(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    value: String,
    label: String,
    color: Color
) {
    Card(
        modifier = Modifier.size(80.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun QuickActionsGrid(navController: NavController) {
    Column {
        Text(
            text = "Acciones Rápidas",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        val quickActions = listOf(
            QuickActionData("Ver Empleos", Icons.Default.Work, MaterialTheme.colorScheme.primary) {
                navController.navigate(Screen.Jobs.route)
            },
            QuickActionData("Capacitaciones", Icons.Default.School, ThemeUtils.getPopularTrainingColor()) {
                navController.navigate(Screen.Trainings.route)
            },
            QuickActionData("Clasificados", Icons.Default.Assignment, ThemeUtils.getNewClassifiedColor()) {
                navController.navigate(Screen.Classifieds.route)
            },
            QuickActionData("Mi Perfil", Icons.Default.Person, MaterialTheme.colorScheme.outline) {
                navController.navigate(Screen.Profile.route)
            }
        )
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(quickActions) { action ->
                QuickActionCard(action = action)
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun QuickActionCard(action: QuickActionData) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        onClick = action.onClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = action.icon,
                contentDescription = null,
                tint = action.color,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = action.title,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    subtitle: String,
    onViewAllClick: () -> Unit,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
            
            TextButton(
                onClick = onViewAllClick,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = color
                )
            ) {
                Text("Ver todo")
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun TipsSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Lightbulb,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Consejo del día",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Mantén tu perfil actualizado con tus últimas habilidades y experiencias para aumentar tus posibilidades de conseguir empleo.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

data class QuickActionData(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val color: Color,
    val onClick: () -> Unit
)

@Composable
private fun WelcomeHeader() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "¡Bienvenido!",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Encuentra tu próxima oportunidad laboral o mejora tus habilidades con nuestras capacitaciones",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
private fun QuickStatsSection(
    totalJobs: Int,
    urgentJobs: Int,
    remoteJobs: Int,
    totalTrainings: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Estadísticas",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem("Total Empleos", totalJobs.toString())
                StatItem("Urgentes", urgentJobs.toString())
                StatItem("Remotos", remoteJobs.toString())
                StatItem("Capacitaciones", totalTrainings.toString())
            }
        }
    }
}

@Composable
private fun StatItem(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun SectionHeader(
    title: String,
    subtitle: String,
    onViewAllClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        TextButton(onClick = onViewAllClick) {
            Text("Ver todo")
        }
    }
}

@Composable
private fun QuickActionsSection(navController: NavController) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Acciones Rápidas",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                QuickActionButton(
                    modifier = Modifier.weight(1f),
                    icon = "🔍",
                    text = "Buscar Empleos",
                    onClick = { navController.navigate(Screen.Jobs.route) }
                )
                QuickActionButton(
                    modifier = Modifier.weight(1f),
                    icon = "📈",
                    text = "Mis Aplicaciones",
                    onClick = { navController.navigate(Screen.Profile.route) }
                )
            }
        }
    }
}

@Composable
private fun QuickActionButton(
    modifier: Modifier = Modifier,
    icon: String,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.aspectRatio(1f)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = icon,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}
