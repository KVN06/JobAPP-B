package com.kvn.job_opp_mobile.data.repository

import com.kvn.job_opp_mobile.data.models.*

class JobRepository {
    
    fun getAllJobs(): List<Job> = listOf(
        Job(
            id = "1",
            title = "Desarrollador Android Senior",
            company = "TechCorp Solutions",
            location = "Santiago, Chile",
            type = JobType.FULL_TIME,
            salaryMin = 2000000,
            salaryMax = 2800000,
            description = "Buscamos un desarrollador Android senior con experiencia en Kotlin y Jetpack Compose para unirse a nuestro equipo de desarrollo móvil.",
            requirements = listOf(
                "5+ años de experiencia en Android",
                "Dominio de Kotlin y Java",
                "Experiencia con Jetpack Compose",
                "Conocimiento de arquitecturas MVVM/MVP",
                "Git y metodologías ágiles"
            ),
            benefits = listOf(
                "Trabajo remoto opcional",
                "Seguro médico complementario",
                "Capacitaciones pagadas",
                "14to sueldo",
                "Ambiente laboral flexible"
            ),
            isRemote = true,
            isUrgent = true,
            postedDate = "2024-12-15",
            applicationDeadline = "2025-01-15",
            contactEmail = "jobs@techcorp.cl",
            experienceLevel = ExperienceLevel.SENIOR
        ),
        Job(
            id = "2",
            title = "Diseñador UX/UI",
            company = "Creative Studio",
            location = "Valparaíso, Chile",
            type = JobType.PART_TIME,
            salaryMin = 800000,
            salaryMax = 1200000,
            description = "Estamos buscando un diseñador UX/UI creativo para trabajar en proyectos innovadores de aplicaciones móviles y web.",
            requirements = listOf(
                "3+ años de experiencia en UX/UI",
                "Dominio de Figma y Adobe Creative Suite",
                "Portfolio demostrando proyectos móviles",
                "Conocimiento en Design Systems",
                "Inglés intermedio"
            ),
            benefits = listOf(
                "Horarios flexibles",
                "Día libre de cumpleaños",
                "Descuentos en cursos",
                "Ambiente creativo"
            ),
            isRemote = false,
            isUrgent = false,
            postedDate = "2024-12-10",
            contactEmail = "talent@creativestudio.cl",
            experienceLevel = ExperienceLevel.MID
        ),
        Job(
            id = "3",
            title = "Analista de Datos Jr",
            company = "DataTech Chile",
            location = "Remoto",
            type = JobType.FULL_TIME,
            salaryMin = 900000,
            salaryMax = 1300000,
            description = "Oportunidad para analista junior en el área de ciencia de datos. Trabajarás con grandes volúmenes de datos y machine learning.",
            requirements = listOf(
                "Título en Ingeniería, Estadística o afín",
                "Python y R",
                "SQL avanzado",
                "Conocimientos básicos de ML",
                "Excel avanzado"
            ),
            benefits = listOf(
                "100% remoto",
                "Capacitación en nuevas tecnologías",
                "Plan de carrera definido",
                "Bonos por desempeño"
            ),
            isRemote = true,
            isUrgent = false,
            postedDate = "2024-12-12",
            contactEmail = "rrhh@datatech.cl",
            experienceLevel = ExperienceLevel.ENTRY
        ),
        Job(
            id = "4",
            title = "Desarrollador Full Stack",
            company = "StartupTech",
            location = "Concepción, Chile",
            type = JobType.CONTRACT,
            salaryMin = 1500000,
            salaryMax = 2200000,
            description = "Únete a nuestra startup en crecimiento como desarrollador full stack. Trabajarás en tecnologías modernas y tendrás impacto directo en el producto.",
            requirements = listOf(
                "React.js y Node.js",
                "PostgreSQL o MongoDB",
                "API REST y GraphQL",
                "Docker básico",
                "Experiencia en startups (deseable)"
            ),
            benefits = listOf(
                "Equity en la empresa",
                "Tecnología de punta",
                "Ambiente startup dinámico",
                "Posibilidad de trabajo remoto"
            ),
            isRemote = true,
            isUrgent = true,
            postedDate = "2024-12-14",
            applicationDeadline = "2025-01-10",
            contactEmail = "founders@startuptech.cl",
            experienceLevel = ExperienceLevel.MID
        ),
        Job(
            id = "5",
            title = "Especialista en Ciberseguridad",
            company = "SecureTech",
            location = "Santiago, Chile",
            type = JobType.FULL_TIME,
            salaryMin = 2500000,
            salaryMax = 3500000,
            description = "Buscamos especialista en ciberseguridad para proteger la infraestructura digital de nuestros clientes empresariales.",
            requirements = listOf(
                "Certificaciones en seguridad (CISSP, CEH, etc.)",
                "Experiencia en pentesting",
                "Conocimiento de frameworks de seguridad",
                "Análisis de vulnerabilidades",
                "Respuesta a incidentes"
            ),
            benefits = listOf(
                "Salario competitivo",
                "Certificaciones pagadas",
                "Tecnología especializada",
                "Proyectos desafiantes"
            ),
            isRemote = false,
            isUrgent = true,
            postedDate = "2024-12-13",
            contactEmail = "security-jobs@securetech.cl",
            experienceLevel = ExperienceLevel.SENIOR
        )
    )
    
    fun getJobById(id: String): Job? = getAllJobs().find { it.id == id }
    
    fun searchJobs(query: String): List<Job> = getAllJobs().filter { job ->
        job.title.contains(query, ignoreCase = true) ||
        job.company.contains(query, ignoreCase = true) ||
        job.description.contains(query, ignoreCase = true)
    }
    
    fun getJobsByType(type: JobType): List<Job> = getAllJobs().filter { it.type == type }
    
    fun getUrgentJobs(): List<Job> = getAllJobs().filter { it.isUrgent }
    
    fun getRemoteJobs(): List<Job> = getAllJobs().filter { it.isRemote }
}
