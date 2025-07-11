# 📱 Job Opportunity Mobile App

Una aplicación móvil moderna y completa para la búsqueda de empleo, capacitaciones profesionales y clasificados laborales, desarrollada con **Jetpack Compose** y **Material Design 3**.

## 🚀 Características Principales

### 🎨 Diseño Moderno
- **Material Design 3** con soporte completo para Material You
- **Tema Claro/Oscuro** con persistencia de preferencias
- **Paleta de colores profesional** optimizada para UX/UI
- **Animaciones suaves** y transiciones fluidas
- **Diseño responsivo** adaptable a diferentes tamaños de pantalla

### 🏠 Pantalla de Inicio
- Header de bienvenida personalizado con gradiente
- Estadísticas rápidas del usuario
- Acciones rápidas para navegación directa
- Secciones categorizadas (empleos urgentes, capacitaciones, clasificados)
- Consejos diarios para mejorar la búsqueda de empleo

### 💼 Gestión de Empleos
- Lista completa de empleos disponibles
- Filtros por categoría, ubicación y tipo
- Marcado de empleos urgentes y remotos
- Tarjetas informativas con datos clave
- Funcionalidad de aplicar y guardar favoritos

### 🎓 Capacitaciones
- Cursos y certificaciones disponibles
- Capacitaciones populares destacadas
- Información de duración y dificultad
- Sistema de inscripciones
- Seguimiento de progreso

### 📋 Clasificados
- Trabajos publicados por empresas
- Filtros por categoría y fecha
- Contacto directo con empleadores
- Información detallada de cada clasificado

### 👤 Perfil de Usuario
- Información personal completa
- Estadísticas de aplicaciones y cursos
- Habilidades y competencias
- Historial de actividad reciente
- Aplicaciones y favoritos guardados

### 🔔 Sistema de Notificaciones
- Notificaciones en tiempo real
- Categorización por tipos (empleos, aplicaciones, cursos)
- Indicadores visuales de no leídas
- Marcado inteligente de leídas

### ⚙️ Configuración Avanzada
- Control de tema claro/oscuro
- Gestión de notificaciones push y email
- Configuración de privacidad y seguridad
- Información de la aplicación
- Gestión de cuenta de usuario

## 🛠️ Tecnologías Utilizadas

### Frontend
- **Jetpack Compose** - UI moderna y declarativa
- **Material Design 3** - Sistema de diseño de Google
- **Navigation Compose** - Navegación entre pantallas
- **ViewModel** - Gestión de estado y lógica de negocio
- **StateFlow** - Manejo de datos reactivos

### Persistencia
- **DataStore Preferences** - Almacenamiento de configuraciones
- **Room Database** - Base de datos local (preparado)
- **Retrofit** - Comunicación con APIs (preparado)

### Arquitectura
- **MVVM** - Model-View-ViewModel pattern
- **Repository Pattern** - Abstracción de acceso a datos
- **Dependency Injection** - Inyección de dependencias (preparado)
- **Clean Architecture** - Separación de responsabilidades

## 📱 Pantallas Implementadas

1. **HomeScreen** - Pantalla principal con resumen
2. **JobsScreen** - Lista de empleos disponibles
3. **JobDetailScreen** - Detalles específicos de empleo
4. **TrainingsScreen** - Capacitaciones disponibles
5. **TrainingDetailScreen** - Información detallada de curso
6. **ClassifiedsScreen** - Trabajos clasificados
7. **ClassifiedDetailScreen** - Detalles de clasificado
8. **ProfileScreen** - Perfil completo del usuario
9. **SettingsScreen** - Configuración de la aplicación
10. **NotificationsScreen** - Centro de notificaciones

## 🎨 Paleta de Colores

### Tema Claro
- **Primary**: `#2E7D32` (Verde profesional)
- **Secondary**: `#1976D2` (Azul corporativo)
- **Tertiary**: `#E65100` (Naranja dinámico)

### Tema Oscuro
- **Primary**: `#81C784` (Verde claro)
- **Secondary**: `#64B5F6` (Azul claro)
- **Tertiary**: `#FFB74D` (Naranja claro)

### Colores Funcionales
- **Empleos Urgentes**: `#FF5722`
- **Trabajo Remoto**: `#9C27B0`
- **Capacitaciones**: `#00BCD4`
- **Clasificados**: `#8BC34A`

## 🚀 Instalación y Configuración

### Requisitos Previos
- Android Studio Hedgehog (2023.1.1) o superior
- JDK 11 o superior
- Android SDK 34
- Gradle 8.0+

### Pasos de Instalación

**Clonar el repositorio**
```bash
git clone https://github.com/username/job-opportunity-mobile.git
cd job-opportunity-mobile
```

**Abrir en Android Studio**
```bash
# Abrir Android Studio y seleccionar "Open an existing project"
# Navegar a la carpeta del proyecto clonado
```

**Sincronizar dependencias**
```bash
# En Android Studio, click en "Sync Project with Gradle Files"
# O desde terminal:
./gradlew sync
```

**Ejecutar la aplicación**
```bash
# Conectar un dispositivo Android o iniciar un emulador
# Hacer click en "Run" o usar el comando:
./gradlew installDebug
```

## 📦 Estructura del Proyecto

```
app/
├── src/main/java/com/kvn/job_opp_mobile/
│   ├── data/
│   │   ├── models/          # Modelos de datos
│   │   └── repository/      # Repositorios de acceso a datos
│   ├── ui/
│   │   ├── navigation/      # Configuración de navegación
│   │   ├── screens/         # Pantallas de la aplicación
│   │   ├── theme/           # Tema y estilos
│   │   └── viewmodel/       # ViewModels
│   └── utils/               # Utilidades y helpers
├── src/main/res/
│   ├── values/              # Recursos de valores
│   ├── drawable/            # Recursos gráficos
│   └── layout/              # Layouts XML (si los hay)
└── build.gradle.kts         # Configuración de Gradle
```

## 🎯 Funcionalidades Destacadas

### Sistema de Temas
- Cambio automático entre tema claro y oscuro
- Persistencia de preferencias del usuario
- Colores adaptativos según el tema del sistema
- Transiciones suaves entre temas

### Navegación Intuitiva
- Barra de navegación inferior con 5 secciones
- Botón de retroceso inteligente
- Navegación por argumentos para detalles
- Deep links preparados para notificaciones

### Experiencia de Usuario
- Carga lazy de listas largas
- Indicadores de estado visual
- Animaciones y transiciones fluidas
- Feedback inmediato en acciones


## 📝 Contribución

1. Fork el proyecto
2. Crear una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir un Pull Request

## 👥 Equipo de Desarrollo

- **Desarrollador Principal**: VIBEFKS TECH
- **Diseñador UI/UX**: Kevin Benavides
- **Arquitecto de Software**: Kevin Benavides
- **Desarrolladores**: 
Kevin E.A Benavides
Ricardo Estiven Cajas Sandoval
Brandon Andres Camayo Trujillo
Fabian Andre Jimenez Muñoz
Juan Alejandro Stewert Llanten Grijalba
Victor Manuel Cruz

## 🙏 Ayudas de

- [Material Design 3](https://m3.material.io/) por el sistema de diseño
- [Jetpack Compose](https://developer.android.com/jetpack/compose) por la UI moderna
- [Android Developers](https://developer.android.com/) por la documentación
- Comunidad de desarrolladores Android por el soporte continuo

---

**Versión**: 1.0.0  
**Última actualización**: Julio 2025  
**Estado**: ✅ Producción Ready

> 💡 **Nota**: Esta aplicación está diseñada siguiendo las mejores prácticas de desarrollo Android y está optimizada para una experiencia de usuario excepcional.