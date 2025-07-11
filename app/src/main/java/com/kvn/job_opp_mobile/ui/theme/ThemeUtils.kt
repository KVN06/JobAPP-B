package com.kvn.job_opp_mobile.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Utilidades para obtener colores correctos según el tema actual
 */
object ThemeUtils {
    
    @Composable
    fun getUrgentJobColor(): Color {
        return if (isSystemInDarkTheme()) DarkUrgentJobColor else UrgentJobColor
    }
    
    @Composable
    fun getRemoteJobColor(): Color {
        return if (isSystemInDarkTheme()) DarkRemoteJobColor else RemoteJobColor
    }
    
    @Composable
    fun getPopularTrainingColor(): Color {
        return if (isSystemInDarkTheme()) DarkPopularTrainingColor else PopularTrainingColor
    }
    
    @Composable
    fun getNewClassifiedColor(): Color {
        return if (isSystemInDarkTheme()) DarkNewClassifiedColor else NewClassifiedColor
    }
    
    @Composable
    fun getSuccessColor(): Color {
        return if (isSystemInDarkTheme()) DarkSuccessColor else SuccessColor
    }
    
    @Composable
    fun getWarningColor(): Color {
        return if (isSystemInDarkTheme()) DarkWarningColor else WarningColor
    }
    
    @Composable
    fun getInfoColor(): Color {
        return if (isSystemInDarkTheme()) DarkInfoColor else InfoColor
    }
    
    @Composable
    fun getTopBarBackground(): Color {
        return if (isSystemInDarkTheme()) DarkTopBarBackground else TopBarBackground
    }
    
    @Composable
    fun getTopBarContent(): Color {
        return if (isSystemInDarkTheme()) DarkTopBarContent else TopBarContent
    }
}
