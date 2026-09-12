pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
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

gradle.beforeProject { project ->
    if (project.name == "app") {
        project.extra.apply {
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

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

tasks.register("dependencies", org.gradle.api.tasks.diagnostics.DependencyReportTask::class) {
    configuration = "releaseRuntimeClasspath"
}

buildscript {
    extra.apply {
        set("compose_version", "2024.12.01")
    }
}