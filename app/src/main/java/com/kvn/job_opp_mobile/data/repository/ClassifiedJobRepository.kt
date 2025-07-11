package com.kvn.job_opp_mobile.data.repository

import com.kvn.job_opp_mobile.data.models.*

class ClassifiedJobRepository {
    
    fun getAllClassifiedJobs(): List<ClassifiedJob> = listOf(
        ClassifiedJob(
            id = "1",
            title = "Limpieza de hogar por horas",
            description = "Ofrezco servicios de limpieza de hogares. Experiencia de 5 años, referencias disponibles. Trabajo con mis propios productos de limpieza ecológicos.",
            category = ClassifiedCategory.DOMESTIC_SERVICES,
            type = ClassifiedType.SERVICE_OFFERED,
            location = "Las Condes, Santiago",
            contactInfo = ContactInfo(
                phone = "+56912345678",
                whatsapp = "+56912345678",
                preferredContactMethod = ContactMethod.WHATSAPP
            ),
            price = 8000.0,
            priceType = PriceType.PER_HOUR,
            postedDate = "2024-12-10",
            isUrgent = false,
            posterName = "María Fernanda",
            posterRating = 4.8f,
            verified = true,
            tags = listOf("Limpieza", "Ecológico", "Referencias", "Experiencia")
        ),
        ClassifiedJob(
            id = "2",
            title = "Clases particulares de Matemáticas",
            description = "Profesor de matemáticas con 8 años de experiencia. Especializado en preparación PSU y reforzamiento escolar. Clases presenciales y online.",
            category = ClassifiedCategory.TUTORING,
            type = ClassifiedType.SERVICE_OFFERED,
            location = "Ñuñoa, Santiago",
            contactInfo = ContactInfo(
                phone = "+56987654321",
                email = "profesor.mate@email.com",
                preferredContactMethod = ContactMethod.EMAIL
            ),
            price = 15000.0,
            priceType = PriceType.PER_HOUR,
            postedDate = "2024-12-12",
            posterName = "Carlos Mendoza",
            posterRating = 4.9f,
            verified = true,
            tags = listOf("Matemáticas", "PSU", "Profesor", "Online", "Presencial")
        ),
        ClassifiedJob(
            id = "3",
            title = "BUSCO: Plomero urgente",
            description = "Necesito plomero para reparar fuga en baño. Trabajo urgente para este fin de semana. Pago al contado.",
            category = ClassifiedCategory.TECHNICAL_SERVICES,
            type = ClassifiedType.SERVICE_NEEDED,
            location = "Providencia, Santiago",
            contactInfo = ContactInfo(
                phone = "+56911111111",
                whatsapp = "+56911111111",
                preferredContactMethod = ContactMethod.PHONE
            ),
            price = 50000.0,
            priceType = PriceType.FIXED_PRICE,
            postedDate = "2024-12-15",
            isUrgent = true,
            posterName = "Juan Pérez",
            verified = false,
            tags = listOf("Plomería", "Urgente", "Fin de semana", "Contado")
        ),
        ClassifiedJob(
            id = "4",
            title = "Diseño de logos y branding",
            description = "Diseñador gráfico freelance. Creo logos profesionales, branding completo y material promocional. Portfolio disponible.",
            category = ClassifiedCategory.CREATIVE_SERVICES,
            type = ClassifiedType.SERVICE_OFFERED,
            location = "Valparaíso",
            contactInfo = ContactInfo(
                email = "design.creative@email.com",
                whatsapp = "+56922222222",
                preferredContactMethod = ContactMethod.EMAIL
            ),
            price = 80000.0,
            priceType = PriceType.PER_PROJECT,
            postedDate = "2024-12-08",
            posterName = "Sofía Ramírez",
            posterRating = 4.7f,
            verified = true,
            tags = listOf("Diseño", "Logo", "Branding", "Freelance", "Portfolio")
        ),
        ClassifiedJob(
            id = "5",
            title = "Delivery en bicicleta",
            description = "Joven responsable con bicicleta propia busca trabajos de delivery por horas. Disponible fines de semana y tardes.",
            category = ClassifiedCategory.DELIVERY,
            type = ClassifiedType.SERVICE_OFFERED,
            location = "Maipú, Santiago",
            contactInfo = ContactInfo(
                phone = "+56933333333",
                whatsapp = "+56933333333",
                preferredContactMethod = ContactMethod.WHATSAPP
            ),
            price = 2500.0,
            priceType = PriceType.PER_HOUR,
            postedDate = "2024-12-14",
            posterName = "Diego Morales",
            posterRating = 4.5f,
            verified = false,
            tags = listOf("Delivery", "Bicicleta", "Fines de semana", "Joven")
        ),
        ClassifiedJob(
            id = "6",
            title = "Cuidado de adultos mayores",
            description = "Técnico en enfermería con experiencia en cuidado de adultos mayores. Disponible para turnos diurnos y nocturnos.",
            category = ClassifiedCategory.ELDERLY_CHILD_CARE,
            type = ClassifiedType.SERVICE_OFFERED,
            location = "San Miguel, Santiago",
            contactInfo = ContactInfo(
                phone = "+56944444444",
                email = "cuidados.professional@email.com",
                preferredContactMethod = ContactMethod.PHONE
            ),
            price = 12000.0,
            priceType = PriceType.PER_HOUR,
            postedDate = "2024-12-11",
            posterName = "Rosa Contreras",
            posterRating = 4.9f,
            verified = true,
            tags = listOf("Adultos mayores", "Enfermería", "Turnos", "Experiencia")
        ),
        ClassifiedJob(
            id = "7",
            title = "BUSCO: Fotógrafo para evento",
            description = "Necesito fotógrafo para matrimonio el 25 de enero. Ceremonia y fiesta. Presupuesto flexible según experiencia.",
            category = ClassifiedCategory.EVENTS,
            type = ClassifiedType.SERVICE_NEEDED,
            location = "Viña del Mar",
            contactInfo = ContactInfo(
                email = "boda2025@email.com",
                phone = "+56955555555",
                preferredContactMethod = ContactMethod.EMAIL
            ),
            price = 200000.0,
            priceType = PriceType.NEGOTIABLE,
            postedDate = "2024-12-13",
            availableFrom = "2025-01-25",
            posterName = "Camila Torres",
            verified = false,
            tags = listOf("Fotografía", "Matrimonio", "Evento", "Enero")
        ),
        ClassifiedJob(
            id = "8",
            title = "Paseador de perros",
            description = "Amante de los animales ofrece servicios de paseo de perros. Horarios flexibles, referencias de otros clientes disponibles.",
            category = ClassifiedCategory.PET_CARE,
            type = ClassifiedType.SERVICE_OFFERED,
            location = "La Reina, Santiago",
            contactInfo = ContactInfo(
                whatsapp = "+56966666666",
                email = "perros.paseos@email.com",
                preferredContactMethod = ContactMethod.WHATSAPP
            ),
            price = 5000.0,
            priceType = PriceType.PER_HOUR,
            postedDate = "2024-12-09",
            posterName = "Cristián López",
            posterRating = 4.6f,
            verified = true,
            tags = listOf("Perros", "Paseos", "Animales", "Flexible")
        )
    )
    
    fun getClassifiedJobById(id: String): ClassifiedJob? = getAllClassifiedJobs().find { it.id == id }
    
    fun getClassifiedJobsByCategory(category: ClassifiedCategory): List<ClassifiedJob> = 
        getAllClassifiedJobs().filter { it.category == category }
    
    fun getClassifiedJobsByType(type: ClassifiedType): List<ClassifiedJob> = 
        getAllClassifiedJobs().filter { it.type == type }
    
    fun getServicesOffered(): List<ClassifiedJob> = 
        getAllClassifiedJobs().filter { it.type == ClassifiedType.SERVICE_OFFERED }
    
    fun getServicesNeeded(): List<ClassifiedJob> = 
        getAllClassifiedJobs().filter { it.type == ClassifiedType.SERVICE_NEEDED }
    
    fun getUrgentClassifieds(): List<ClassifiedJob> = 
        getAllClassifiedJobs().filter { it.isUrgent }
    
    fun getVerifiedClassifieds(): List<ClassifiedJob> = 
        getAllClassifiedJobs().filter { it.verified }
    
    fun searchClassifiedJobs(query: String): List<ClassifiedJob> = getAllClassifiedJobs().filter { job ->
        job.title.contains(query, ignoreCase = true) ||
        job.description.contains(query, ignoreCase = true) ||
        job.tags.any { it.contains(query, ignoreCase = true) }
    }
    
    fun getRecentClassifieds(): List<ClassifiedJob> = 
        getAllClassifiedJobs().sortedByDescending { it.postedDate }
}
