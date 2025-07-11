package com.kvn.job_opp_mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kvn.job_opp_mobile.data.models.*
import com.kvn.job_opp_mobile.ui.theme.ThemeUtils
import java.text.NumberFormat
import java.util.*

@Composable
fun JobCard(
    job: Job,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(280.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header con título y empresa
            Column {
                Text(
                    text = job.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = job.company,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Salario
            val formatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL"))
            Text(
                text = "${formatter.format(job.salaryMin)} - ${formatter.format(job.salaryMax)}",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.secondary
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Ubicación
            Text(
                text = job.location,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Tags
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                JobTag(
                    text = when(job.type) {
                        JobType.FULL_TIME -> "T. Completo"
                        JobType.PART_TIME -> "T. Parcial"
                        JobType.CONTRACT -> "Contrato"
                        JobType.INTERNSHIP -> "Práctica"
                        JobType.FREELANCE -> "Freelance"
                    },
                    backgroundColor = MaterialTheme.colorScheme.surfaceVariant
                )
                
                if (job.isRemote) {
                    JobTag(
                        text = "Remoto",
                        backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                        textColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
                
                if (job.isUrgent) {
                    JobTag(
                        text = "Urgente",
                        backgroundColor = MaterialTheme.colorScheme.errorContainer,
                        textColor = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }
        }
    }
}

@Composable
fun TrainingCard(
    training: Training,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(280.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header
            Column {
                Text(
                    text = training.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = training.provider,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Precio y rating
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (training.isFree) "Gratis" else NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL")).format(training.price),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = if (training.isFree) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface
                )
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "⭐",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = training.rating.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Duración y estudiantes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = training.duration,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                Text(
                    text = "${training.enrolledStudents} estudiantes",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Tags
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                TrainingTag(
                    text = when(training.level) {
                        TrainingLevel.BEGINNER -> "Principiante"
                        TrainingLevel.INTERMEDIATE -> "Intermedio"
                        TrainingLevel.ADVANCED -> "Avanzado"
                        TrainingLevel.EXPERT -> "Experto"
                    },
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
                
                TrainingTag(
                    text = when(training.format) {
                        TrainingFormat.ONLINE -> "Online"
                        TrainingFormat.PRESENTIAL -> "Presencial"
                        TrainingFormat.HYBRID -> "Híbrido"
                        TrainingFormat.SELF_PACED -> "A tu ritmo"
                    },
                    backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
                    textColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
    }
}

@Composable
fun ClassifiedCard(
    classified: ClassifiedJob,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(280.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header
            Column {
                Text(
                    text = classified.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Por ${classified.posterName}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Precio y rating
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                classified.price?.let { price ->
                    val priceText = when(classified.priceType) {
                        PriceType.PER_HOUR -> "${NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL")).format(price)}/hora"
                        PriceType.PER_DAY -> "${NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL")).format(price)}/día"
                        PriceType.FIXED_PRICE -> NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL")).format(price)
                        PriceType.PER_PROJECT -> "${NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL")).format(price)}/proyecto"
                        PriceType.NEGOTIABLE -> "Negociable"
                    }
                    Text(
                        text = priceText,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                } ?: Text(
                    text = "A convenir",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                
                classified.posterRating?.let { rating ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "⭐",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = rating.toString(),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Ubicación
            Text(
                text = classified.location,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF6B7280)
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Tags
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                ClassifiedTag(
                    text = when(classified.type) {
                        ClassifiedType.SERVICE_OFFERED -> "Ofrezco"
                        ClassifiedType.SERVICE_NEEDED -> "Busco"
                        ClassifiedType.GIG_WORK -> "Trabajo"
                    },
                    backgroundColor = when(classified.type) {
                        ClassifiedType.SERVICE_OFFERED -> Color(0xFFDCFCE7)
                        ClassifiedType.SERVICE_NEEDED -> Color(0xFFFEF3C7)
                        ClassifiedType.GIG_WORK -> Color(0xFFE0F2FE)
                    }
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
                        backgroundColor = Color(0xFFDDD6FE)
                    )
                }
            }
        }
    }
}

@Composable
fun FullJobCard(
    job: Job,
    onClick: () -> Unit,
    onApply: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = job.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = job.company,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        modifier = Modifier.padding(top = 4.dp)
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
            
            // Salary and location
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val formatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL"))
                Text(
                    text = "${formatter.format(job.salaryMin)} - ${formatter.format(job.salaryMax)}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF059669)
                )
                
                Text(
                    text = job.location,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF6B7280)
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Description preview
            Text(
                text = job.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF374151),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
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
                    backgroundColor = Color(0xFFE5E7EB)
                )
                
                if (job.isRemote) {
                    JobTag(
                        text = "Remoto",
                        backgroundColor = Color(0xFFDCFCE7)
                    )
                }
                
                JobTag(
                    text = when(job.experienceLevel) {
                        ExperienceLevel.ENTRY -> "Junior"
                        ExperienceLevel.MID -> "Semi-Senior"
                        ExperienceLevel.SENIOR -> "Senior"
                        ExperienceLevel.LEAD -> "Lead"
                    },
                    backgroundColor = Color(0xFFDDD6FE)
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Posted date
            Text(
                text = "Publicado: ${job.postedDate}",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF9CA3AF)
            )
            
            // Apply button if onApply is provided
            if (onApply != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onApply,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3B82F6)
                    )
                ) {
                    Text("Postular ahora")
                }
            }
        }
    }
}

@Composable
fun FullTrainingCard(
    training: Training,
    onClick: () -> Unit,
    onEnroll: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = training.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF111827)
                    )
                    Text(
                        text = training.provider,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF6B7280),
                        modifier = Modifier.padding(top = 4.dp)
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
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Price and rating
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (training.isFree) "Gratis" else NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL")).format(training.price),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = if (training.isFree) Color(0xFF059669) else Color(0xFF374151)
                )
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "⭐",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "${training.rating} (${training.enrolledStudents} estudiantes)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF6B7280)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Description
            Text(
                text = training.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF374151),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Duration and format
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Duración: ${training.duration}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF6B7280)
                )
                
                Text(
                    text = when(training.format) {
                        TrainingFormat.ONLINE -> "Online"
                        TrainingFormat.PRESENTIAL -> "Presencial"
                        TrainingFormat.HYBRID -> "Híbrido"
                        TrainingFormat.SELF_PACED -> "A tu ritmo"
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF6B7280)
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
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
                
                TrainingTag(
                    text = when(training.category) {
                        TrainingCategory.TECHNOLOGY -> "Tecnología"
                        TrainingCategory.BUSINESS -> "Negocios"
                        TrainingCategory.DESIGN -> "Diseño"
                        TrainingCategory.MARKETING -> "Marketing"
                        TrainingCategory.HEALTHCARE -> "Salud"
                        TrainingCategory.EDUCATION -> "Educación"
                        TrainingCategory.FINANCE -> "Finanzas"
                        TrainingCategory.CONSTRUCTION -> "Construcción"
                        TrainingCategory.HOSPITALITY -> "Hospitalidad"
                        TrainingCategory.OTHER -> "Otro"
                    },
                    backgroundColor = Color(0xFFE0F2FE)
                )
                
                if (training.certificateProvided) {
                    TrainingTag(
                        text = "Certificado",
                        backgroundColor = Color(0xFFFEF3C7)
                    )
                }
            }
            
            // Enroll button if onEnroll is provided
            if (onEnroll != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onEnroll,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF10B981)
                    )
                ) {
                    Text("Inscribirse")
                }
            }
        }
    }
}

@Composable
fun FullClassifiedCard(
    classified: ClassifiedJob,
    onClick: () -> Unit,
    onContact: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header
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
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF111827)
                    )
                    Text(
                        text = "Por ${classified.posterName}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF6B7280),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (classified.isUrgent) {
                        ClassifiedTag(
                            text = "Urgente",
                            backgroundColor = Color(0xFFFEE2E2),
                            textColor = Color(0xFFDC2626)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    
                    if (classified.verified) {
                        ClassifiedTag(
                            text = "✓",
                            backgroundColor = Color(0xFF059669),
                            textColor = Color.White
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Price and rating
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                classified.price?.let { price ->
                    val priceText = when(classified.priceType) {
                        PriceType.PER_HOUR -> "${NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL")).format(price)}/hora"
                        PriceType.PER_DAY -> "${NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL")).format(price)}/día"
                        PriceType.FIXED_PRICE -> NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL")).format(price)
                        PriceType.PER_PROJECT -> "${NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL")).format(price)}/proyecto"
                        PriceType.NEGOTIABLE -> "Negociable"
                    }
                    Text(
                        text = priceText,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF059669)
                    )
                } ?: Text(
                    text = "A convenir",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF6B7280)
                )
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = classified.location,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF6B7280)
                    )
                    
                    classified.posterRating?.let { rating ->
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "⭐",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = rating.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF6B7280)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Description
            Text(
                text = classified.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF374151),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Tags and contact method
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ClassifiedTag(
                        text = when(classified.type) {
                            ClassifiedType.SERVICE_OFFERED -> "Ofrezco servicio"
                            ClassifiedType.SERVICE_NEEDED -> "Busco servicio"
                            ClassifiedType.GIG_WORK -> "Trabajo por proyecto"
                        },
                        backgroundColor = when(classified.type) {
                            ClassifiedType.SERVICE_OFFERED -> Color(0xFFDCFCE7)
                            ClassifiedType.SERVICE_NEEDED -> Color(0xFFFEF3C7)
                            ClassifiedType.GIG_WORK -> Color(0xFFE0F2FE)
                        }
                    )
                    
                    ClassifiedTag(
                        text = when(classified.category) {
                            ClassifiedCategory.DOMESTIC_SERVICES -> "Dom. Servicios"
                            ClassifiedCategory.TECHNICAL_SERVICES -> "Tec. Servicios"
                            ClassifiedCategory.PROFESSIONAL_SERVICES -> "Prof. Servicios"
                            ClassifiedCategory.CREATIVE_SERVICES -> "Creativos"
                            ClassifiedCategory.TUTORING -> "Tutorías"
                            ClassifiedCategory.DELIVERY -> "Delivery"
                            ClassifiedCategory.EVENTS -> "Eventos"
                            ClassifiedCategory.BEAUTY_WELLNESS -> "Belleza"
                            ClassifiedCategory.PET_CARE -> "Mascotas"
                            ClassifiedCategory.ELDERLY_CHILD_CARE -> "Cuidados"
                            ClassifiedCategory.OTHER -> "Otro"
                        },
                        backgroundColor = Color(0xFFE5E7EB)
                    )
                }
                
                Text(
                    text = when(classified.contactInfo.preferredContactMethod) {
                        ContactMethod.PHONE -> "📞"
                        ContactMethod.EMAIL -> "📧"
                        ContactMethod.WHATSAPP -> "📱"
                        ContactMethod.APP_MESSAGE -> "💬"
                    },
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Posted date
            Text(
                text = "Publicado: ${classified.postedDate}",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF9CA3AF)
            )
            
            // Contact button if onContact is provided
            if (onContact != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onContact,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF59E0B)
                    )
                ) {
                    Text("Contactar")
                }
            }
        }
    }
}

@Composable
fun JobTag(
    text: String,
    backgroundColor: Color,
    textColor: Color = Color(0xFF374151)
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = textColor,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun TrainingTag(
    text: String,
    backgroundColor: Color,
    textColor: Color = Color(0xFF374151)
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = textColor,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ClassifiedTag(
    text: String,
    backgroundColor: Color,
    textColor: Color = Color(0xFF374151)
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = textColor,
            fontWeight = FontWeight.Medium
        )
    }
}
