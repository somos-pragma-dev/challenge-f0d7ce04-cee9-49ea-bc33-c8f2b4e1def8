// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.ksp) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

subprojects {
    afterEvaluate {
        if (plugins.hasPlugin("com.android.application") || plugins.hasPlugin("com.android.library")) {
            extensions.configure<com.android.build.gradle.BaseExtension> {
                compileSdk = 35
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
                kotlinOptions {
                    jvmTarget = "17"
                }
            }
        }
    }
}

// Configuración de versión centralizada para el proyecto
extra.apply {
    set("kotlinVersion", "2.1.0")
    set("composeVersion", "1.7.3")
    set("roomVersion", "2.6.1")
    set("retrofitVersion", "2.9.0")
    set("hiltVersion", "2.51.1")
    set("coroutinesVersion", "1.8.1")
    set("lifecycleVersion", "2.8.7")
    set("navigationVersion", "2.8.4")
}

// Configuración de repositories
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}