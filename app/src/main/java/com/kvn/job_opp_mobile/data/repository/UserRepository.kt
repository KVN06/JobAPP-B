package com.kvn.job_opp_mobile.data.repository

import com.kvn.job_opp_mobile.data.models.User

class UserRepository {
    
    fun getCurrentUser(): User {
        // TODO: This would typically come from a local database or SharedPreferences
        return User(
            id = "demo_user_001",
            name = "Juan Pérez",
            email = "juan.perez@email.com",
            phone = "+56912345678",
            location = "Santiago, Chile",
            bio = "Desarrollador Android con 3 años de experiencia buscando nuevas oportunidades para crecer profesionalmente.",
            skills = listOf(
                "Kotlin",
                "Android",
                "Jetpack Compose",
                "Git",
                "SQL",
                "REST APIs"
            ),
            experience = "3 años como Desarrollador Android en TechStart Chile",
            education = "Ingeniería en Informática - Universidad de Chile",
            appliedJobs = listOf("1", "3", "4"),
            enrolledTrainings = listOf("1", "2"),
            favoriteJobs = listOf("2", "5"),
            notifications = true,
            isEmailVerified = true,
            joinDate = "2024-01-15"
        )
    }
    
    fun updateUser(user: User): Boolean {
        // TODO: Implement user update logic
        return true
    }
    
    fun updateNotificationSettings(enabled: Boolean): Boolean {
        // TODO: Implement notification settings update
        return true
    }
    
    fun updateProfilePicture(imageUri: String): Boolean {
        // TODO: Implement profile picture update
        return true
    }
}
