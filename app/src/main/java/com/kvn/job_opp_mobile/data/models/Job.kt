package com.kvn.job_opp_mobile.data.models

data class Job(
    val id: String,
    val title: String,
    val company: String,
    val location: String,
    val type: JobType,
    val salaryMin: Int,
    val salaryMax: Int,
    val description: String,
    val requirements: List<String>,
    val benefits: List<String>,
    val isRemote: Boolean,
    val isUrgent: Boolean,
    val postedDate: String,
    val applicationDeadline: String? = null,
    val contactEmail: String,
    val experienceLevel: ExperienceLevel
)

enum class JobType {
    FULL_TIME,
    PART_TIME,
    CONTRACT,
    INTERNSHIP,
    FREELANCE
}

enum class ExperienceLevel {
    ENTRY,
    MID,
    SENIOR,
    LEAD
}
