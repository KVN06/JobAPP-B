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
import com.kvn.job_opp_mobile.data.repository.ClassifiedJobRepository
import com.kvn.job_opp_mobile.data.models.*
import com.kvn.job_opp_mobile.ui.theme.ThemeUtils
import java.text.NumberFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassifiedDetailScreen(
    classifiedId: String,
    navController: NavController
) {
    val classifiedRepository = remember { ClassifiedJobRepository() }
    val classified = remember { classifiedRepository.getClassifiedJobById(classifiedId) }
    
    if (classified == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Trabajo clasificado no encontrado")
        }
        return
    }
    
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top bar
        TopAppBar(
            title = { Text("Detalle de Trabajo") },
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
            ClassifiedHeaderCard(classified = classified)
            
            // Description card
            ClassifiedDescriptionCard(classified = classified)
            
            // Contact card
            ClassifiedContactCard(classified = classified)
            
            // Contact button
            Button(
                onClick = { /* TODO: Implement contact logic */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3B82F6)
                )
            ) {
                Text(
                    text = when(classified.contactInfo.preferredContactMethod) {
                        ContactMethod.PHONE -> "Llamar"
                        ContactMethod.EMAIL -> "Enviar Email"
                        ContactMethod.WHATSAPP -> "Contactar por WhatsApp"
                        ContactMethod.APP_MESSAGE -> "Enviar Mensaje"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun ClassifiedHeaderCard(classified: ClassifiedJob) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // Title and type
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = classified.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    
                    Text(
                        text = "Publicado por ${classified.posterName}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                
                ClassifiedTag(
                    text = when(classified.type) {
                        ClassifiedType.SERVICE_OFFERED -> "Ofrezco"
                        ClassifiedType.SERVICE_NEEDED -> "Busco"
                        ClassifiedType.GIG_WORK -> "Trabajo"
                    },
                    backgroundColor = when(classified.type) {
                        ClassifiedType.SERVICE_OFFERED -> ThemeUtils.getSuccessColor().copy(alpha = 0.1f)
                        ClassifiedType.SERVICE_NEEDED -> ThemeUtils.getWarningColor().copy(alpha = 0.1f)
                        ClassifiedType.GIG_WORK -> ThemeUtils.getInfoColor().copy(alpha = 0.1f)
                    },
                    textColor = when(classified.type) {
                        ClassifiedType.SERVICE_OFFERED -> ThemeUtils.getSuccessColor()
                        ClassifiedType.SERVICE_NEEDED -> ThemeUtils.getWarningColor()
                        ClassifiedType.GIG_WORK -> ThemeUtils.getInfoColor()
                    }
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Price
            classified.price?.let { price ->
                val priceText = when(classified.priceType) {
                    PriceType.PER_HOUR -> "${NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL")).format(price)} por hora"
                    PriceType.PER_DAY -> "${NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL")).format(price)} por día"
                    PriceType.FIXED_PRICE -> NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL")).format(price)
                    PriceType.PER_PROJECT -> "${NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL")).format(price)} por proyecto"
                    PriceType.NEGOTIABLE -> "Precio negociable"
                }
                Text(
                    text = priceText,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = ThemeUtils.getSuccessColor()
                )
            } ?: Text(
                text = "Precio a convenir",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Location and rating
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = classified.location,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                
                classified.posterRating?.let { rating ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "⭐",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = rating.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Category and status tags
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ClassifiedTag(
                    text = when(classified.category) {
                        ClassifiedCategory.DOMESTIC_SERVICES -> "Servicios Domésticos"
                        ClassifiedCategory.TECHNICAL_SERVICES -> "Servicios Técnicos"
                        ClassifiedCategory.PROFESSIONAL_SERVICES -> "Servicios Profesionales"
                        ClassifiedCategory.CREATIVE_SERVICES -> "Servicios Creativos"
                        ClassifiedCategory.TUTORING -> "Tutorías"
                        ClassifiedCategory.DELIVERY -> "Delivery"
                        ClassifiedCategory.EVENTS -> "Eventos"
                        ClassifiedCategory.BEAUTY_WELLNESS -> "Belleza y Bienestar"
                        ClassifiedCategory.PET_CARE -> "Cuidado de Mascotas"
                        ClassifiedCategory.ELDERLY_CHILD_CARE -> "Cuidados"
                        ClassifiedCategory.OTHER -> "Otro"
                    },
                    backgroundColor = Color(0xFFE5E7EB)
                )
                
                if (classified.isUrgent) {
                    ClassifiedTag(
                        text = "Urgente",
                        backgroundColor = Color(0xFFFEE2E2),
                        textColor = Color(0xFFDC2626)
                    )
                }
                
                if (classified.verified) {
                    ClassifiedTag(
                        text = "Verificado",
                        backgroundColor = Color(0xFF059669),
                        textColor = Color.White
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Posted date and availability
            Column {
                Text(
                    text = "Publicado: ${classified.postedDate}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF9CA3AF)
                )
                
                classified.availableFrom?.let { from ->
                    Text(
                        text = "Disponible desde: $from",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF6B7280)
                    )
                }
                
                classified.availableUntil?.let { until ->
                    Text(
                        text = "Disponible hasta: $until",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF6B7280)
                    )
                }
            }
        }
    }
}

@Composable
fun ClassifiedDescriptionCard(classified: ClassifiedJob) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Descripción",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = classified.description,
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF374151),
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
            )
            
            if (classified.tags.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Etiquetas",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF111827)
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    classified.tags.take(4).forEach { tag ->
                        ClassifiedTag(
                            text = tag,
                            backgroundColor = Color(0xFFF3F4F6)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ClassifiedContactCard(classified: ClassifiedJob) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Información de Contacto",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Preferred contact method
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = when(classified.contactInfo.preferredContactMethod) {
                        ContactMethod.PHONE -> "📞"
                        ContactMethod.EMAIL -> "📧"
                        ContactMethod.WHATSAPP -> "📱"
                        ContactMethod.APP_MESSAGE -> "💬"
                    },
                    style = MaterialTheme.typography.headlineMedium
                )
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Column {
                    Text(
                        text = "Método preferido de contacto",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color(0xFF6B7280)
                    )
                    Text(
                        text = when(classified.contactInfo.preferredContactMethod) {
                            ContactMethod.PHONE -> "Teléfono"
                            ContactMethod.EMAIL -> "Email"
                            ContactMethod.WHATSAPP -> "WhatsApp"
                            ContactMethod.APP_MESSAGE -> "Mensaje en la app"
                        },
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF374151)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Available contact methods
            Text(
                text = "Métodos de contacto disponibles:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF111827)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            classified.contactInfo.phone?.let { phone ->
                ContactMethodItem(
                    icon = "📞",
                    label = "Teléfono",
                    value = phone
                )
            }
            
            classified.contactInfo.email?.let { email ->
                ContactMethodItem(
                    icon = "📧",
                    label = "Email",
                    value = email
                )
            }
            
            classified.contactInfo.whatsapp?.let { whatsapp ->
                ContactMethodItem(
                    icon = "📱",
                    label = "WhatsApp",
                    value = whatsapp
                )
            }
        }
    }
}

@Composable
fun ContactMethodItem(
    icon: String,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = icon,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF6B7280)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF374151)
            )
        }
    }
}
