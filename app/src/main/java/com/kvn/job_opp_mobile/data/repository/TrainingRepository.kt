package com.kvn.job_opp_mobile.data.repository

import com.kvn.job_opp_mobile.data.models.*

class TrainingRepository {
    
    fun getAllTrainings(): List<Training> = listOf(
        Training(
            id = "1",
            title = "Desarrollo Android con Kotlin",
            provider = "TechAcademy Chile",
            description = "Aprende desarrollo Android desde cero usando Kotlin y las últimas tecnologías como Jetpack Compose.",
            category = TrainingCategory.TECHNOLOGY,
            level = TrainingLevel.BEGINNER,
            duration = "8 semanas",
            format = TrainingFormat.ONLINE,
            price = 150000.0,
            isFree = false,
            rating = 4.8f,
            enrolledStudents = 245,
            startDate = "2025-01-15",
            endDate = "2025-03-15",
            prerequisites = listOf("Conocimientos básicos de programación", "Familiaridad con conceptos OOP"),
            learningOutcomes = listOf(
                "Crear aplicaciones Android nativas",
                "Usar Kotlin efectivamente",
                "Implementar interfaces con Jetpack Compose",
                "Publicar apps en Google Play Store"
            ),
            instructor = "Carlos Mendoza",
            certificateProvided = true,
            tags = listOf("Android", "Kotlin", "Mobile", "Compose")
        ),
        Training(
            id = "2",
            title = "Diseño UX/UI para Principiantes",
            provider = "Design Institute",
            description = "Domina los fundamentos del diseño de experiencia de usuario y interfaces intuitivas.",
            category = TrainingCategory.DESIGN,
            level = TrainingLevel.BEGINNER,
            duration = "6 semanas",
            format = TrainingFormat.HYBRID,
            price = 120000.0,
            isFree = false,
            rating = 4.7f,
            enrolledStudents = 189,
            startDate = "2025-01-20",
            endDate = "2025-03-01",
            prerequisites = listOf("Ninguno", "Ganas de aprender"),
            learningOutcomes = listOf(
                "Principios de diseño centrado en el usuario",
                "Prototipado con Figma",
                "Investigación de usuarios",
                "Sistemas de diseño"
            ),
            instructor = "María González",
            certificateProvided = true,
            tags = listOf("UX", "UI", "Figma", "Design Thinking")
        ),
        Training(
            id = "3",
            title = "Soldadura Básica y Seguridad Industrial",
            provider = "Instituto Técnico Profesional",
            description = "Curso práctico de soldadura con enfoque en seguridad industrial para el sector construcción.",
            category = TrainingCategory.CONSTRUCTION,
            level = TrainingLevel.BEGINNER,
            duration = "4 semanas",
            format = TrainingFormat.PRESENTIAL,
            price = 80000.0,
            isFree = false,
            rating = 4.9f,
            enrolledStudents = 67,
            startDate = "2025-02-01",
            endDate = "2025-02-28",
            prerequisites = listOf("Mayor de 18 años", "Certificado médico"),
            learningOutcomes = listOf(
                "Técnicas básicas de soldadura",
                "Normativas de seguridad",
                "Uso de herramientas especializadas",
                "Certificación técnica reconocida"
            ),
            instructor = "Roberto Silva",
            certificateProvided = true,
            tags = listOf("Soldadura", "Construcción", "Seguridad", "Técnico")
        ),
        Training(
            id = "4",
            title = "Marketing Digital y Redes Sociales",
            provider = "Marketing Pro",
            description = "Estrategias efectivas de marketing digital para pequeñas y medianas empresas.",
            category = TrainingCategory.MARKETING,
            level = TrainingLevel.INTERMEDIATE,
            duration = "10 semanas",
            format = TrainingFormat.ONLINE,
            price = 0.0,
            isFree = true,
            rating = 4.5f,
            enrolledStudents = 432,
            startDate = "2025-01-10",
            endDate = "2025-03-20",
            prerequisites = listOf("Conocimientos básicos de computación"),
            learningOutcomes = listOf(
                "Estrategias de redes sociales",
                "Google Ads y Facebook Ads",
                "Email marketing",
                "Análisis de métricas"
            ),
            instructor = "Ana Rodríguez",
            certificateProvided = true,
            tags = listOf("Marketing", "Redes Sociales", "Digital", "Gratuito")
        ),
        Training(
            id = "5",
            title = "Primeros Auxilios y RCP",
            provider = "Cruz Roja Chilena",
            description = "Capacitación esencial en primeros auxilios y reanimación cardiopulmonar certificada.",
            category = TrainingCategory.HEALTHCARE,
            level = TrainingLevel.BEGINNER,
            duration = "2 semanas",
            format = TrainingFormat.PRESENTIAL,
            price = 45000.0,
            isFree = false,
            rating = 4.9f,
            enrolledStudents = 156,
            startDate = "2025-01-25",
            endDate = "2025-02-08",
            prerequisites = listOf("Ninguno"),
            learningOutcomes = listOf(
                "Técnicas de RCP",
                "Manejo de emergencias básicas",
                "Uso de desfibrilador",
                "Certificación Cruz Roja"
            ),
            instructor = "Dr. Pedro Morales",
            certificateProvided = true,
            tags = listOf("Primeros Auxilios", "RCP", "Salud", "Emergencias")
        ),
        Training(
            id = "6",
            title = "Contabilidad Básica para Emprendedores",
            provider = "Finanzas Fácil",
            description = "Aprende los fundamentos de contabilidad para manejar las finanzas de tu emprendimiento.",
            category = TrainingCategory.FINANCE,
            level = TrainingLevel.BEGINNER,
            duration = "6 semanas",
            format = TrainingFormat.ONLINE,
            price = 90000.0,
            isFree = false,
            rating = 4.6f,
            enrolledStudents = 298,
            startDate = "2025-02-10",
            endDate = "2025-03-24",
            prerequisites = listOf("Conocimientos básicos de matemáticas"),
            learningOutcomes = listOf(
                "Estados financieros básicos",
                "Manejo de flujo de caja",
                "Obligaciones tributarias",
                "Software contable básico"
            ),
            instructor = "Contador Luis Pérez",
            certificateProvided = true,
            tags = listOf("Contabilidad", "Finanzas", "Emprendimiento", "Tributario")
        )
    )
    
    fun getTrainingById(id: String): Training? = getAllTrainings().find { it.id == id }
    
    fun getTrainingsByCategory(category: TrainingCategory): List<Training> = 
        getAllTrainings().filter { it.category == category }
    
    fun getFreeTrainings(): List<Training> = getAllTrainings().filter { it.isFree }
    
    fun getTrainingsByLevel(level: TrainingLevel): List<Training> = 
        getAllTrainings().filter { it.level == level }
    
    fun searchTrainings(query: String): List<Training> = getAllTrainings().filter { training ->
        training.title.contains(query, ignoreCase = true) ||
        training.description.contains(query, ignoreCase = true) ||
        training.provider.contains(query, ignoreCase = true)
    }
    
    fun getPopularTrainings(): List<Training> = 
        getAllTrainings().sortedByDescending { it.enrolledStudents }.take(4)
    
    fun getTopRatedTrainings(): List<Training> = 
        getAllTrainings().sortedByDescending { it.rating }.take(4)
}
