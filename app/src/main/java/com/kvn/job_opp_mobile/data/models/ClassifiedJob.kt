package com.kvn.job_opp_mobile.data.models

data class ClassifiedJob(
    val id: String,
    val title: String,
    val description: String,
    val category: ClassifiedCategory,
    val type: ClassifiedType,
    val location: String,
    val contactInfo: ContactInfo,
    val price: Double? = null, // Para servicios que tienen precio fijo
    val priceType: PriceType,
    val postedDate: String,
    val isUrgent: Boolean = false,
    val imageUrls: List<String> = emptyList(),
    val tags: List<String> = emptyList(),
    val availableFrom: String? = null,
    val availableUntil: String? = null,
    val posterName: String,
    val posterRating: Float? = null,
    val verified: Boolean = false
)

data class ContactInfo(
    val phone: String? = null,
    val email: String? = null,
    val whatsapp: String? = null,
    val preferredContactMethod: ContactMethod
)

enum class ContactMethod {
    PHONE,
    EMAIL,
    WHATSAPP,
    APP_MESSAGE
}

enum class ClassifiedCategory {
    DOMESTIC_SERVICES, // Servicios domésticos (limpieza, jardinería, etc.)
    TECHNICAL_SERVICES, // Servicios técnicos (plomería, electricidad, etc.)
    PROFESSIONAL_SERVICES, // Servicios profesionales (contabilidad, legal, etc.)
    CREATIVE_SERVICES, // Servicios creativos (diseño, fotografía, etc.)
    TUTORING, // Clases particulares
    DELIVERY, // Delivery y transporte
    EVENTS, // Eventos y catering
    BEAUTY_WELLNESS, // Belleza y bienestar
    PET_CARE, // Cuidado de mascotas
    ELDERLY_CHILD_CARE, // Cuidado de niños y adultos mayores
    OTHER
}

enum class ClassifiedType {
    SERVICE_OFFERED, // Ofrezco servicio
    SERVICE_NEEDED,  // Busco servicio
    GIG_WORK        // Trabajo por horas/proyecto
}

enum class PriceType {
    PER_HOUR,
    FIXED_PRICE,
    PER_DAY,
    PER_PROJECT,
    NEGOTIABLE
}
