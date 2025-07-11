package com.kvn.job_opp_mobile.data.models

data class Training(
    val id: String,
    val title: String,
    val provider: String,
    val description: String,
    val category: TrainingCategory,
    val level: TrainingLevel,
    val duration: String, // ej: "4 semanas", "2 meses"
    val format: TrainingFormat,
    val price: Double,
    val isFree: Boolean,
    val rating: Float,
    val enrolledStudents: Int,
    val imageUrl: String? = null,
    val startDate: String? = null,
    val endDate: String? = null,
    val prerequisites: List<String>,
    val learningOutcomes: List<String>,
    val instructor: String,
    val certificateProvided: Boolean,
    val tags: List<String>
)

enum class TrainingCategory {
    TECHNOLOGY,
    BUSINESS,
    DESIGN,
    MARKETING,
    HEALTHCARE,
    EDUCATION,
    FINANCE,
    CONSTRUCTION,
    HOSPITALITY,
    OTHER
}

enum class TrainingLevel {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED,
    EXPERT
}

enum class TrainingFormat {
    ONLINE,
    PRESENTIAL,
    HYBRID,
    SELF_PACED
}
