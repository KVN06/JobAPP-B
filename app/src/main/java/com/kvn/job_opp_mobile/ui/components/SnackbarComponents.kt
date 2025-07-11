package com.kvn.job_opp_mobile.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun AppSnackbarHost(
    snackbarHostState: SnackbarHostState,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
) {
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = modifier
    ) { data ->
        Snackbar(
            snackbarData = data,
            containerColor = when {
                data.visuals.message.contains("Error", ignoreCase = true) -> Color(0xFFDC2626)
                data.visuals.message.contains("Éxito", ignoreCase = true) -> Color(0xFF059669)
                else -> MaterialTheme.colorScheme.inverseSurface
            },
            contentColor = Color.White
        )
    }
}

object SnackbarMessages {
    const val JOB_APPLIED_SUCCESS = "¡Postulación enviada con éxito!"
    const val JOB_APPLIED_ERROR = "Error al enviar postulación"
    const val TRAINING_ENROLLED_SUCCESS = "¡Te has inscrito en el curso!"
    const val TRAINING_ENROLLED_ERROR = "Error al inscribirse en el curso"
    const val CONTACT_SUCCESS = "¡Contacto realizado con éxito!"
    const val CONTACT_ERROR = "Error al realizar contacto"
    const val PROFILE_UPDATED_SUCCESS = "Perfil actualizado correctamente"
    const val PROFILE_UPDATED_ERROR = "Error al actualizar perfil"
}
