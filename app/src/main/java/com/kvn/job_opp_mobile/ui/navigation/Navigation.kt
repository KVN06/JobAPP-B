package com.kvn.job_opp_mobile.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kvn.job_opp_mobile.ui.screens.*
import com.kvn.job_opp_mobile.ui.viewmodel.ThemeViewModel
import com.kvn.job_opp_mobile.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobOppNavigation(themeViewModel: ThemeViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route
    
    // Obtener el estado del tema
    val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()

    // Determinar si mostrar bottom bar
    val showBottomBar = currentRoute in listOf(
        Screen.Home.route,
        Screen.Jobs.route,
        Screen.Trainings.route,
        Screen.Classifieds.route,
        Screen.Profile.route
    )

    // Determinar si mostrar botón de regreso
    val showBackButton = !showBottomBar

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Job Opportunity",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    color = if (isDarkTheme) DarkSecondary else Secondary
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "APP",
                                color = if (isDarkTheme) DarkOnSecondary else OnSecondary,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                },
                navigationIcon = {
                    if (showBackButton) {
                        IconButton(onClick = { 
                            if (!navController.popBackStack()) {
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        inclusive = true
                                    }
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver"
                            )
                        }
                    }
                },
                actions = {
                    if (currentRoute == Screen.Home.route || currentRoute == Screen.Jobs.route || 
                        currentRoute == Screen.Trainings.route || currentRoute == Screen.Classifieds.route) {
                        IconButton(onClick = { /* TODO: Implementar búsqueda */ }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Buscar"
                            )
                        }
                    }
                    
                    IconButton(onClick = { navController.navigate(Screen.Notifications.route) }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notificaciones"
                        )
                    }
                    
                    if (currentRoute == Screen.Profile.route) {
                        IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Configuración"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ) {
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Home, contentDescription = "Inicio") },
                        label = { Text("Inicio") },
                        selected = currentRoute == Screen.Home.route,
                        onClick = {
                            if (currentRoute != Screen.Home.route) {
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Work, contentDescription = "Empleos") },
                        label = { Text("Empleos") },
                        selected = currentRoute == Screen.Jobs.route,
                        onClick = {
                            if (currentRoute != Screen.Jobs.route) {
                                navController.navigate(Screen.Jobs.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.School, contentDescription = "Capacitaciones") },
                        label = { Text("Capacitaciones", style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp)) },
                        selected = currentRoute == Screen.Trainings.route,
                        onClick = {
                            if (currentRoute != Screen.Trainings.route) {
                                navController.navigate(Screen.Trainings.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.AutoMirrored.Filled.Assignment, contentDescription = "Clasificados") },
                        label = { Text("Clasificados") },
                        selected = currentRoute == Screen.Classifieds.route,
                        onClick = {
                            if (currentRoute != Screen.Classifieds.route) {
                                navController.navigate(Screen.Classifieds.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Person, contentDescription = "Perfil") },
                        label = { Text("Perfil") },
                        selected = currentRoute == Screen.Profile.route,
                        onClick = {
                            if (currentRoute != Screen.Profile.route) {
                                navController.navigate(Screen.Profile.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navController = navController)
            }
            composable(Screen.Jobs.route) {
                JobsScreen(navController = navController)
            }
            composable(Screen.Trainings.route) {
                TrainingsScreen(navController = navController)
            }
            composable(Screen.Classifieds.route) {
                ClassifiedsScreen(navController = navController)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navController = navController)
            }
            composable(Screen.Settings.route) {
                SettingsScreen(navController = navController, themeViewModel = themeViewModel)
            }
            composable(Screen.Notifications.route) {
                NotificationsScreen(navController = navController)
            }
            composable("${Screen.JobDetail.route}/{jobId}") { backStackEntry ->
                val jobId = backStackEntry.arguments?.getString("jobId") ?: ""
                JobDetailScreen(jobId = jobId, navController = navController)
            }
            composable("${Screen.TrainingDetail.route}/{trainingId}") { backStackEntry ->
                val trainingId = backStackEntry.arguments?.getString("trainingId") ?: ""
                TrainingDetailScreen(trainingId = trainingId, navController = navController)
            }
            composable("${Screen.ClassifiedDetail.route}/{classifiedId}") { backStackEntry ->
                val classifiedId = backStackEntry.arguments?.getString("classifiedId") ?: ""
                ClassifiedDetailScreen(classifiedId = classifiedId, navController = navController)
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Jobs : Screen("jobs")
    object Trainings : Screen("trainings")
    object Classifieds : Screen("classifieds")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
    object Notifications : Screen("notifications")
    object JobDetail : Screen("job_detail")
    object TrainingDetail : Screen("training_detail")
    object ClassifiedDetail : Screen("classified_detail")
}
