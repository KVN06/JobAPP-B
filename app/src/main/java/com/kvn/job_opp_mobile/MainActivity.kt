package com.kvn.job_opp_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kvn.job_opp_mobile.ui.navigation.JobOppNavigation
import com.kvn.job_opp_mobile.ui.theme.Job_Opp_MobileTheme
import com.kvn.job_opp_mobile.ui.viewmodel.ThemeViewModel
import com.kvn.job_opp_mobile.ui.viewmodel.ThemeViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Configurar para edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)
        
        setContent {
            val themeViewModel: ThemeViewModel = viewModel(
                factory = ThemeViewModelFactory(this@MainActivity)
            )
            val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()
            
            Job_Opp_MobileTheme(darkTheme = isDarkTheme) {
                JobOppNavigation(themeViewModel = themeViewModel)
            }
        }
    }
}
