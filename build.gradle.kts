plugins {
    id("com.android.application") version "8.7.3" apply false
    id("org.jetbrains.kotlin.android") version "2.1.0" apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    id("com.google.devtools.ksp") version "2.1.0-1.0.28" apply false
    id("androidx.hilt.hilt-gradle-plugins") version "1.2.0" apply false
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "OfflineFirstApp"
include(":app")

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

gradle.beforeProject {
    if (this.name == "app") {
        this.extra.apply {
            set("compose_version", "2024.12.01")
            set("kotlin_version", "2.1.0")
            set("room_version", "2.6.1")
            set("hilt_version", "2.51.1")
            set("coroutines_version", "1.8.1")
            set("retrofit_version", "2.11.0")
            set("moshi_version", "1.15.1")
            set("work_version", "2.9.1")
            set("lifecycle_version", "2.8.7")
            set("navigation_version", "2.8.5")
        }
    }
}

allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "21"
            freeCompilerArgs += listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=kotlin.ExperimentalStdlibApi"
            )
        }
    }
}

subprojects {
    afterEvaluate {
        if (plugins.hasPlugin("com.android.application") || plugins.hasPlugin("com.android.library")) {
            extensions.configure<com.android.build.gradle.BaseExtension> {
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_21
                    targetCompatibility = JavaVersion.VERSION_21
                }
                kotlinOptions {
                    jvmTarget = "21"
                }
            }
        }
    }
}

buildscript {
    extra.apply {
        set("compose_version", "2024.12.01")
    }
}

tasks.register("dependencies", org.gradle.api.tasks.diagnostics.DependencyReportTask::class) {
    configuration = "releaseRuntimeClasspath"
}