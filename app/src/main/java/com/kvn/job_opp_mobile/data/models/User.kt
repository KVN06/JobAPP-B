package com.kvn.job_opp_mobile.data.models

data class User(
    val id: String,
    val name: String,
    val email: String,
    val phone: String? = null,
    val profilePicture: String? = null,
    val location: String? = null,
    val bio: String? = null,
    val skills: List<String> = emptyList(),
    val experience: String? = null,
    val education: String? = null,
    val appliedJobs: List<String> = emptyList(),
    val enrolledTrainings: List<String> = emptyList(),
    val favoriteJobs: List<String> = emptyList(),
    val notifications: Boolean = true,
    val isEmailVerified: Boolean = false,
    val joinDate: String
)
