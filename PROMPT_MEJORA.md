# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Boilerplate del stack que falta

Sin esto no compila ni arranca. Es andamiaje, no toca nada de lo pedagogico:

- **Activity o Application (con @HiltAndroidApp / setContent)** — Sin una Activity o Application, no hay componente que Android pueda lanzar al abrir la app.

### Archivos que la arquitectura del reto declara y no estan

Creálos con implementacion real, en la capa que les corresponde:

- `app/src/main/java/com/example/offlinefirst/data/sync/SyncManager.kt`
- `app/src/main/java/com/example/offlinefirst/presentation/ui/viewmodel/ProductViewModel.kt`
- `app/src/main/java/com/example/offlinefirst/presentation/ui/viewmodel/PurchaseViewModel.kt`
- `app/src/main/java/com/example/offlinefirst/presentation/ui/viewmodel/SettingsViewModel.kt`
- `app/src/main/java/com/example/offlinefirst/presentation/ui/screens/ProductListScreen.kt`
- `app/src/main/java/com/example/offlinefirst/presentation/ui/screens/PurchaseHistoryScreen.kt`
- `app/src/main/java/com/example/offlinefirst/presentation/ui/screens/SettingsScreen.kt`
- `app/src/main/java/com/example/offlinefirst/presentation/ui/components/SyncStatusBanner.kt`
- `app/src/main/java/com/example/offlinefirst/di/DatabaseModule.kt`
- `app/src/main/res/values/themes.xml`

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `app/src/main/java/com/example/offlinefirst/di/NetworkModule.kt` — `com.example.offlinefirst.BuildConfig`: El import com.example.offlinefirst.BuildConfig usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `app/src/main/java/com/example/offlinefirst/data/remote/dto/PurchaseDto.kt` — `PurchaseStatus`: PurchaseStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.PurchaseStatus.
- `app/src/main/java/com/example/offlinefirst/domain/usecase/PurchaseUseCases.kt` — `Loading`: Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading.
- `app/src/main/java/com/example/offlinefirst/domain/usecase/PurchaseUseCases.kt` — `Success`: Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success.
- `app/src/main/java/com/example/offlinefirst/domain/usecase/PurchaseUseCases.kt` — `Error`: Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error.
- `app/src/main/java/com/example/offlinefirst/domain/usecase/UserPreferencesUseCases.kt` — `Loading`: Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading.
- `app/src/main/java/com/example/offlinefirst/domain/usecase/UserPreferencesUseCases.kt` — `Success`: Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success.
- `app/src/main/java/com/example/offlinefirst/domain/usecase/UserPreferencesUseCases.kt` — `Error`: Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error.
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `Loading`: Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading.
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `Error`: Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error.
- `app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt` — `Success`: Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `Loading`: Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `Success`: Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `Error`: Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `CreateProductRequest`: CreateProductRequest se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.data.remote.dto.CreateProductRequest.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `UpdateProductRequest`: UpdateProductRequest se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.data.remote.dto.UpdateProductRequest.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `SyncStatus`: SyncStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.SyncStatus.
- `app/src/main/java/com/example/offlinefirst/data/repository/PurchaseRepositoryImpl.kt` — `Loading`: Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading.
- `app/src/main/java/com/example/offlinefirst/data/repository/PurchaseRepositoryImpl.kt` — `Success`: Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success.
- `app/src/main/java/com/example/offlinefirst/data/repository/PurchaseRepositoryImpl.kt` — `Error`: Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error.
- `app/src/main/java/com/example/offlinefirst/data/repository/UserPreferencesRepositoryImpl.kt` — `Loading`: Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading.
- `app/src/main/java/com/example/offlinefirst/data/repository/UserPreferencesRepositoryImpl.kt` — `Success`: Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success.
- `app/src/main/java/com/example/offlinefirst/data/repository/UserPreferencesRepositoryImpl.kt` — `Error`: Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `Error`: Error se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Error.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `Success`: Success se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Success.
- `app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt` — `Loading`: Loading se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.util.Loading.
- `app/src/main/java/com/example/offlinefirst/data/local/entity/Product.kt` — `Product`: Product se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.Product.
- `app/src/main/java/com/example/offlinefirst/data/local/entity/Product.kt` — `SyncState`: SyncState se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.offlinefirst.domain.model.SyncState.
- `app/src/main/java/com/example/offlinefirst/data/repository/PurchaseRepositoryImpl.kt` — `SyncStatus`: El import com.example.offlinefirst.domain.model.SyncStatus no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- `app/src/main/java/com/example/offlinefirst/data/sync/ConflictResolver.kt` — `SyncStatus`: El import com.example.offlinefirst.domain.model.SyncStatus no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- `app/src/main/java/com/example/offlinefirst/data/sync/ConflictResolver.kt` — `SyncState`: El import com.example.offlinefirst.domain.model.SyncState no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `Product.toEntity`: Se invoca `toEntity` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `Product.toDomain`: Se invoca `toDomain` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `Product.toPendingEntity`: Se invoca `toPendingEntity` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `ProductDao.insertPendingProduct`: Se invoca `insertPendingProduct` sobre `ProductDao`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `ProductDao.markProductForDeletion`: Se invoca `markProductForDeletion` sobre `ProductDao`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `ProductDao.getPendingProducts`: Se invoca `getPendingProducts` sobre `ProductDao`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt` — `ProductDao.deletePendingProduct`: Se invoca `deletePendingProduct` sobre `ProductDao`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/PurchaseRepositoryImpl.kt` — `PurchaseHistory.toEntity`: Se invoca `toEntity` sobre `PurchaseHistory`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/PurchaseRepositoryImpl.kt` — `PurchaseHistory.toDomain`: Se invoca `toDomain` sobre `PurchaseHistory`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/PurchaseRepositoryImpl.kt` — `PurchaseHistory.toPendingEntity`: Se invoca `toPendingEntity` sobre `PurchaseHistory`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/UserPreferencesRepositoryImpl.kt` — `UserPreferences.toEntity`: Se invoca `toEntity` sobre `UserPreferences`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/sync/ConflictResolver.kt` — `Product.copy`: Se invoca `copy` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt (actualizado)` — `Product.toEntity`: Se invoca `toEntity` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt (actualizado)` — `Product.toDomain`: Se invoca `toDomain` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt (actualizado)` — `Product.toPendingEntity`: Se invoca `toPendingEntity` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt (actualizado)` — `ProductDao.insertPendingProduct`: Se invoca `insertPendingProduct` sobre `ProductDao`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt (actualizado)` — `ProductDao.markProductForDeletion`: Se invoca `markProductForDeletion` sobre `ProductDao`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt (actualizado)` — `ProductDao.getPendingProducts`: Se invoca `getPendingProducts` sobre `ProductDao`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt (actualizado)` — `ProductDao.deletePendingProduct`: Se invoca `deletePendingProduct` sobre `ProductDao`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
./gradlew assembleDebug
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Movil, Especialidad Android, Tecnología Android, Senior

### Brecha de conocimiento
Necesita fortalecer la practica de Android

### Misión / candidato
Liderar la iniciativa de persistencia local offline-first

### Reto
- Tema: Persistencia local offline-first
- Seniority: senior-l2
- Tipo: practical
- Título: Implementación de persistencia local offline-first en una aplicación Android
- Tiempo estimado: 2 semanas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Diseño del modelo de datos — objetivo: Definir la estructura de los datos que se persistirán localmente. — entregable (NO resolver): Esquema de la base de datos local con relaciones definidas.
- Fase 2: Implementación de la persistencia local — objetivo: Implementar la persistencia local de los datos identificados en la fase anterior. — entregable (NO resolver): Código fuente que implementa la persistencia local de los datos.
- Fase 3: Sincronización de datos — objetivo: Implementar la sincronización automática de datos cuando la conexión a Internet se restablezca. — entregable (NO resolver): Código fuente que implementa la sincronización automática de datos.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: build.gradle.kts ===
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

// === ARCHIVO: app/build.gradle.kts ===
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
    kotlin("plugin.serialization")
}

android {
    namespace = "com.example.offlinefirst"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.offlinefirst"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // Configuración de Room para generar esquema
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose BOM
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)

    // Navigation Compose
    implementation(libs.androidx.navigation.compose)

    // Lifecycle y ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)

    // Hilt - Inyección de dependencias
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Room - Persistencia local
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // Retrofit - Cliente HTTP
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.kotlinx.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    // Kotlinx Serialization
    implementation(libs.kotlinx.serialization.json)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // WorkManager para sincronización en background
    implementation(libs.work.runtime.ktx)
    implementation(libs.hilt.work)
    ksp(libs.hilt.compiler)

    // DataStore para preferencias
    implementation(libs.datastore.preferences)

    // Coil para carga de imágenes
    implementation(libs.coil.compose)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/remote/dto/ProductDto.kt ===
package com.example.offlinefirst.data.remote.dto

import com.example.offlinefirst.domain.model.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * DTO para la respuesta de la API de productos.
 * Representa la estructura de datos que viene del servidor remoto.
 */
@Serializable
data class ProductDto(
    @SerialName("id")
    val id: String,
    
    @SerialName("name")
    val name: String,
    
    @SerialName("description")
    val description: String,
    
    @SerialName("price")
    val price: Double,
    
    @SerialName("currency")
    val currency: String = "USD",
    
    @SerialName("category")
    val category: String,
    
    @SerialName("imageUrl")
    val imageUrl: String? = null,
    
    @SerialName("stock")
    val stock: Int,
    
    @SerialName("rating")
    val rating: Float = 0f,
    
    @SerialName("reviewCount")
    val reviewCount: Int = 0,
    
    @SerialName("isAvailable")
    val isAvailable: Boolean = true,
    
    @SerialName("createdAt")
    val createdAt: Long,
    
    @SerialName("updatedAt")
    val updatedAt: Long
) {
    /**
     * Convierte el DTO al modelo de dominio.
     */
    fun toDomain(): Product {
        return Product(
            id = id,
            name = name,
            description = description,
            price = BigDecimal.valueOf(price),
            currency = currency,
            category = category,
            imageUrl = imageUrl,
            stock = stock,
            rating = rating,
            reviewCount = reviewCount,
            isAvailable = isAvailable,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    companion object {
        /**
         * Crea un DTO desde el modelo de dominio.
         */
        fun fromDomain(product: Product): ProductDto {
            return ProductDto(
                id = product.id,
                name = product.name,
                description = product.description,
                price = product.price.toDouble(),
                currency = product.currency,
                category = product.category,
                imageUrl = product.imageUrl,
                stock = product.stock,
                rating = product.rating,
                reviewCount = product.reviewCount,
                isAvailable = product.isAvailable,
                createdAt = product.createdAt,
                updatedAt = product.updatedAt
            )
        }
    }
}

/**
 * DTO para paginación de productos.
 */
@Serializable
data class ProductListResponse(
    @SerialName("products")
    val products: List<ProductDto>,
    
    @SerialName("total")
    val total: Int,
    
    @SerialName("page")
    val page: Int,
    
    @SerialName("pageSize")
    val pageSize: Int,
    
    @SerialName("hasMore")
    val hasMore: Boolean
)

/**
 * DTO para crear un nuevo producto (solo campos editables).
 */
@Serializable
data class CreateProductRequest(
    @SerialName("name")
    val name: String,
    
    @SerialName("description")
    val description: String,
    
    @SerialName("price")
    val price: Double,
    
    @SerialName("currency")
    val currency: String = "USD",
    
    @SerialName("category")
    val category: String,
    
    @SerialName("imageUrl")
    val imageUrl: String? = null,
    
    @SerialName("stock")
    val stock: Int
)

/**
 * DTO para actualizar un producto existente.
 */
@Serializable
data class UpdateProductRequest(
    @SerialName("name")
    val name: String? = null,
    
    @SerialName("description")
    val description: String? = null,
    
    @SerialName("price")
    val price: Double? = null,
    
    @SerialName("category")
    val category: String? = null,
    
    @SerialName("imageUrl")
    val imageUrl: String? = null,
    
    @SerialName("stock")
    val stock: Int? = null,
    
    @SerialName("isAvailable")
    val isAvailable: Boolean? = null
)

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/remote/dto/PurchaseDto.kt ===
package com.example.offlinefirst.data.remote.dto

import com.example.offlinefirst.domain.model.PurchaseHistory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * DTO para la respuesta de la API de historial de compras.
 * Representa la estructura de datos que viene del servidor remoto.
 */
@Serializable
data class PurchaseDto(
    @SerialName("id")
    val id: String,
    
    @SerialName("userId")
    val userId: String,
    
    @SerialName("productId")
    val productId: String,
    
    @SerialName("productName")
    val productName: String,
    
    @SerialName("productImageUrl")
    val productImageUrl: String? = null,
    
    @SerialName("quantity")
    val quantity: Int,
    
    @SerialName("unitPrice")
    val unitPrice: Double,
    
    @SerialName("totalPrice")
    val totalPrice: Double,
    
    @SerialName("currency")
    val currency: String = "USD",
    
    @SerialName("status")
    val status: String,
    
    @SerialName("paymentMethod")
    val paymentMethod: String,
    
    @SerialName("shippingAddress")
    val shippingAddress: String? = null,
    
    @SerialName("purchaseDate")
    val purchaseDate: Long,
    
    @SerialName("deliveryDate")
    val deliveryDate: Long? = null,
    
    @SerialName("trackingNumber")
    val trackingNumber: String? = null,
    
    @SerialName("notes")
    val notes: String? = null,
    
    @SerialName("createdAt")
    val createdAt: Long,
    
    @SerialName("updatedAt")
    val updatedAt: Long
) {
    /**
     * Convierte el DTO al modelo de dominio.
     */
    fun toDomain(): PurchaseHistory {
        return PurchaseHistory(
            id = id,
            userId = userId,
            productId = productId,
            productName = productName,
            productImageUrl = productImageUrl,
            quantity = quantity,
            unitPrice = BigDecimal.valueOf(unitPrice),
            totalPrice = BigDecimal.valueOf(totalPrice),
            currency = currency,
            status = parseStatus(status),
            paymentMethod = paymentMethod,
            shippingAddress = shippingAddress,
            purchaseDate = purchaseDate,
            deliveryDate = deliveryDate,
            trackingNumber = trackingNumber,
            notes = notes,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun parseStatus(status: String): PurchaseHistory.PurchaseStatus {
        return when (status.uppercase()) {
            "PENDING" -> PurchaseHistory.PurchaseStatus.PENDING
            "CONFIRMED" -> PurchaseHistory.PurchaseStatus.CONFIRMED
            "PROCESSING" -> PurchaseHistory.PurchaseStatus.PROCESSING
            "SHIPPED" -> PurchaseHistory.PurchaseStatus.SHIPPED
            "DELIVERED" -> PurchaseHistory.PurchaseStatus.DELIVERED
            "CANCELLED" -> PurchaseHistory.PurchaseStatus.CANCELLED
            "REFUNDED" -> PurchaseHistory.PurchaseStatus.REFUNDED
            else -> PurchaseHistory.PurchaseStatus.UNKNOWN
        }
    }

    companion object {
        /**
         * Crea un DTO desde el modelo de dominio.
         */
        fun fromDomain(purchase: PurchaseHistory): PurchaseDto {
            return PurchaseDto(
                id = purchase.id,
                userId = purchase.userId,
                productId = purchase.productId,
                productName = purchase.productName,
                productImageUrl = purchase.productImageUrl,
                quantity = purchase.quantity,
                unitPrice = purchase.unitPrice.toDouble(),
                totalPrice = purchase.totalPrice.toDouble(),
                currency = purchase.currency,
                status = purchase.status.name,
                paymentMethod = purchase.paymentMethod,
                shippingAddress = purchase.shippingAddress,
                purchaseDate = purchase.purchaseDate,
                deliveryDate = purchase.deliveryDate,
                trackingNumber = purchase.trackingNumber,
                notes = purchase.notes,
                createdAt = purchase.createdAt,
                updatedAt = purchase.updatedAt
            )
        }
    }
}

/**
 * DTO para paginación de compras.
 */
@Serializable
data class PurchaseListResponse(
    @SerialName("purchases")
    val purchases: List<PurchaseDto>,
    
    @SerialName("total")
    val total: Int,
    
    @SerialName("page")
    val page: Int,
    
    @SerialName("pageSize")
    val pageSize: Int,
    
    @SerialName("hasMore")
    val hasMore: Boolean
)

/**
 * DTO para crear una nueva compra.
 */
@Serializable
data class CreatePurchaseRequest(
    @SerialName("productId")
    val productId: String,
    
    @SerialName("quantity")
    val quantity: Int,
    
    @SerialName("paymentMethod")
    val paymentMethod: String,
    
    @SerialName("shippingAddress")
    val shippingAddress: String? = null,
    
    @SerialName("notes")
    val notes: String? = null
)

/**
 * DTO para respuesta de compra exitosa.
 */
@Serializable
data class PurchaseResponse(
    @SerialName("purchase")
    val purchase: PurchaseDto,
    
    @SerialName("message")
    val message: String,
    
    @SerialName("confirmationCode")
    val confirmationCode: String
)

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/model/Product.kt ===
package com.example.offlinefirst.domain.model

import java.math.BigDecimal

/**
 * Modelo de dominio para Producto.
 * Representa la entidad de negocio sin dependencias de infraestructura.
 * Esta clase es inmutable y contiene toda la información relevante del producto.
 */
data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val currency: String = "USD",
    val category: String,
    val imageUrl: String? = null,
    val stock: Int,
    val rating: Float = 0f,
    val reviewCount: Int = 0,
    val isAvailable: Boolean = true,
    val createdAt: Long,
    val updatedAt: Long
) {
    /**
     * Valida que el producto tenga datos consistentes.
     * @return true si el producto es válido
     */
    fun isValid(): Boolean {
        return id.isNotBlank() &&
                name.isNotBlank() &&
                price > BigDecimal.ZERO &&
                stock >= 0 &&
                rating in 0f..5f
    }

    /**
     * Verifica si hay stock disponible para la cantidad solicitada.
     * @param quantity Cantidad deseada
     * @return true si hay suficiente stock
     */
    fun hasStock(quantity: Int): Boolean {
        return isAvailable && stock >= quantity && quantity > 0
    }

    /**
     * Obtiene el precio formateado con la moneda.
     * @return String con el precio formateado
     */
    fun getFormattedPrice(): String {
        return "$currency %.2f".format(price)
    }

    /**
     * Obtiene el rating formateado como texto.
     * @return String con el rating y cantidad de reseñas
     */
    fun getFormattedRating(): String {
        return "%.1f (%d reseñas)".format(rating, reviewCount)
    }

    /**
     * Crea una copia con precio actualizado.
     * @param newPrice Nuevo precio
     * @return Nueva instancia de Product
     */
    fun withPrice(newPrice: BigDecimal): Product {
        return copy(
            price = newPrice,
            updatedAt = System.currentTimeMillis()
        )
    }

    /**
     * Crea una copia con stock actualizado.
     * @param newStock Nuevo stock
     * @return Nueva instancia de Product
     */
    fun withStock(newStock: Int): Product {
        return copy(
            stock = newStock,
            isAvailable = newStock > 0,
            updatedAt = System.currentTimeMillis()
        )
    }

    /**
     * Crea una copia del producto con disponibilidad modificada.
     * @param available Nueva disponibilidad
     * @return Nueva instancia de Product
     */
    fun withAvailability(available: Boolean): Product {
        return copy(
            isAvailable = available,
            updatedAt = System.currentTimeMillis()
        )
    }

    companion object {
        /**
         * Crea un producto de ejemplo para testing.
         */
        fun createSample(): Product {
            val now = System.currentTimeMillis()
            return Product(
                id = "sample-001",
                name = "Producto de Ejemplo",
                description = "Este es un producto de ejemplo para propósitos de prueba",
                price = BigDecimal("99.99"),
                currency = "USD",
                category = "Electronics",
                imageUrl = "https://example.com/image.jpg",
                stock = 50,
                rating = 4.5f,
                reviewCount = 120,
                isAvailable = true,
                createdAt = now,
                updatedAt = now
            )
        }
    }
}

/**
 * Representa una categoría de productos.
 */
enum class ProductCategory(val displayName: String) {
    ELECTRONICS("Electrónicos"),
    CLOTHING("Ropa"),
    BOOKS("Libros"),
    HOME("Hogar"),
    SPORTS("Deportes"),
    FOOD("Alimentos"),
    OTHER("Otros");

    companion object {
        fun fromString(value: String): ProductCategory {
            return entries.find { 
                it.name.equals(value, ignoreCase = true) || 
                it.displayName.equals(value, ignoreCase = true) 
            } ?: OTHER
        }
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/model/PurchaseHistory.kt ===
package com.example.offlinefirst.domain.model

import java.math.BigDecimal

/**
 * Modelo de dominio para Historial de Compras.
 * Representa la entidad de negocio sin dependencias de infraestructura.
 * Esta clase es inmutable y contiene toda la información relevante de una compra.
 */
data class PurchaseHistory(
    val id: String,
    val userId: String,
    val productId: String,
    val productName: String,
    val productImageUrl: String? = null,
    val quantity: Int,
    val unitPrice: BigDecimal,
    val totalPrice: BigDecimal,
    val currency: String = "USD",
    val status: PurchaseStatus,
    val paymentMethod: String,
    val shippingAddress: String? = null,
    val purchaseDate: Long,
    val deliveryDate: Long? = null,
    val trackingNumber: String? = null,
    val notes: String? = null,
    val createdAt: Long,
    val updatedAt: Long
) {
    /**
     * Valida que la compra tenga datos consistentes.
     * @return true si la compra es válida
     */
    fun isValid(): Boolean {
        return id.isNotBlank() &&
                userId.isNotBlank() &&
                productId.isNotBlank() &&
                quantity > 0 &&
                totalPrice > BigDecimal.ZERO
    }

    /**
     * Verifica si la compra está activa (no cancelada ni reembolsada).
     * @return true si la compra está activa
     */
    fun isActive(): Boolean {
        return status != PurchaseStatus.CANCELLED && 
               status != PurchaseStatus.REFUNDED
    }

    /**
     * Obtiene el precio total formateado con la moneda.
     * @return String con el precio formateado
     */
    fun getFormattedTotalPrice(): String {
        return "$currency %.2f".format(totalPrice)
    }

    /**
     * Obtiene el precio unitario formateado con la moneda.
     * @return String con el precio formateado
     */
    fun getFormattedUnitPrice(): String {
        return "$currency %.2f".format(unitPrice)
    }

    /**
     * Obtiene el estado formateado para display.
     * @return String con el estado traducida
     */
    fun getFormattedStatus(): String {
        return when (status) {
            PurchaseStatus.PENDING -> "Pendiente"
            PurchaseStatus.CONFIRMED -> "Confirmada"
            PurchaseStatus.PROCESSING -> "Procesando"
            PurchaseStatus.SHIPPED -> "Enviada"
            PurchaseStatus.DELIVERED -> "Entregada"
            PurchaseStatus.CANCELLED -> "Cancelada"
            PurchaseStatus.REFUNDED -> "Reembolsada"
            PurchaseStatus.UNKNOWN -> "Desconocido"
        }
    }

    /**
     * Verifica si la compra puede ser cancelada.
     * @return true si se puede cancelar
     */
    fun canBeCancelled(): Boolean {
        return status in listOf(
            PurchaseStatus.PENDING,
            PurchaseStatus.CONFIRMED,
            PurchaseStatus.PROCESSING
        )
    }

    /**
     * Obtiene los días transcurridos desde la compra.
     * @return Número de días
     */
    fun getDaysSincePurchase(): Long {
        val now = System.currentTimeMillis()
        return (now - purchaseDate) / (1000 * 60 * 60 * 24)
    }

    /**
     * Crea una copia con estado actualizado.
     * @param newStatus Nuevo estado
     * @return Nueva instancia de PurchaseHistory
     */
    fun withStatus(newStatus: PurchaseStatus): PurchaseHistory {
        return copy(
            status = newStatus,
            updatedAt = System.currentTimeMillis()
        )
    }

    /**
     * Crea una copia con información de envío actualizada.
     * @param tracking Nuevo número de seguimiento
     * @param deliveryDate Nueva fecha de entrega
     * @return Nueva instancia de PurchaseHistory
     */
    fun withShippingInfo(tracking: String?, deliveryDate: Long?): PurchaseHistory {
        return copy(
            trackingNumber = tracking,
            deliveryDate = deliveryDate,
            updatedAt = System.currentTimeMillis()
        )
    }

    companion object {
        /**
         * Crea una compra de ejemplo para testing.
         */
        fun createSample(): PurchaseHistory {
            val now = System.currentTimeMillis()
            return PurchaseHistory(
                id = "purchase-001",
                userId = "user-001",
                productId = "product-001",
                productName = "Producto de Ejemplo",
                productImageUrl = "https://example.com/image.jpg",
                quantity = 2,
                unitPrice = BigDecimal("49.99"),
                totalPrice = BigDecimal("99.98"),
                currency = "USD",
                status = PurchaseStatus.DELIVERED,
                paymentMethod = "Credit Card",
                shippingAddress = "123 Main St, City, Country",
                purchaseDate = now - (7 * 24 * 60 * 60 * 1000), // hace 7 días
                deliveryDate = now - (3 * 24 * 60 * 60 * 1000), // hace 3 días
                trackingNumber = "TRACK123456",
                notes = "Entrega en horario laboral",
                createdAt = now - (7 * 24 * 60 * 60 * 1000),
                updatedAt = now - (3 * 24 * 60 * 60 * 1000)
            )
        }
    }
}

/**
 * Estados posibles de una compra.
 */
enum class PurchaseStatus {
    PENDING,      // Pendiente de confirmación
    CONFIRMED,    // Confirmada
    PROCESSING,   // Procesando
    SHIPPED,      // Enviada
    DELIVERED,    // Entregada
    CANCELLED,    // Cancelada
    REFUNDED,     // Reembolsada
    UNKNOWN       // Estado desconocido
}

/**
 * Métodos de pago disponibles.
 */
enum class PaymentMethod(val displayName: String) {
    CREDIT_CARD("Tarjeta de Crédito"),
    DEBIT_CARD("Tarjeta de Débito"),
    PAYPAL("PayPal"),
    BANK_TRANSFER("Transferencia Bancaria"),
    CASH_ON_DELIVERY("Contra Reembolso"),
    CRYPTOCURRENCY("Criptomoneda");

    companion object {
        fun fromString(value: String): PaymentMethod {
            return entries.find { 
                it.name.equals(value, ignoreCase = true) ||
                it.displayName.equals(value, ignoreCase = true)
            } ?: CREDIT_CARD
        }
    }
}


// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/model/UserPreferences.kt ===
package com.example.offlinefirst.domain.model

import java.util.UUID

data class UserPreferences(
    val id: String = UUID.randomUUID().toString(),
    val userId: String,
    val theme: ThemeMode = ThemeMode.SYSTEM,
    val notificationsEnabled: Boolean = true,
    val autoSyncEnabled: Boolean = true,
    val lastSyncTimestamp: Long = 0L,
    val preferredPaymentMethod: PaymentMethod? = null,
    val favoriteCategories: List<ProductCategory> = emptyList(),
    val notificationsForPriceDrops: Boolean = true,
    val notificationsForNewProducts: Boolean = true,
    val offlineDataRetentionDays: Int = 30,
    val maxOfflineProducts: Int = 500,
    val syncOnWifiOnly: Boolean = false,
    val language: String = "es",
    val currency: String = "EUR",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    fun isValid(): Boolean {
        return userId.isNotBlank() &&
                offlineDataRetentionDays in 1..365 &&
                maxOfflineProducts in 10..10000 &&
                language.isNotBlank() &&
                currency.isNotBlank()
    }

    fun requiresSync(): Boolean {
        val timeSinceLastSync = System.currentTimeMillis() - lastSyncTimestamp
        val twentyFourHours = 24 * 60 * 60 * 1000L
        return autoSyncEnabled && (lastSyncTimestamp == 0L || timeSinceLastSync > twentyFourHours)
    }

    fun canSaveOfflineData(): Boolean {
        return offlineDataRetentionDays > 0 && maxOfflineProducts > 0
    }

    fun getCategoriesForFiltering(): List<ProductCategory> {
        return if (favoriteCategories.isEmpty()) ProductCategory.entries else favoriteCategories
    }

    fun isCategoryFavorite(category: ProductCategory): Boolean {
        return favoriteCategories.contains(category)
    }

    fun withTheme(newTheme: ThemeMode): UserPreferences {
        return copy(theme = newTheme, updatedAt = System.currentTimeMillis())
    }

    fun withNotifications(enabled: Boolean): UserPreferences {
        return copy(notificationsEnabled = enabled, updatedAt = System.currentTimeMillis())
    }

    fun withAutoSync(enabled: Boolean): UserPreferences {
        return copy(autoSyncEnabled = enabled, updatedAt = System.currentTimeMillis())
    }

    fun withLastSyncTimestamp(timestamp: Long): UserPreferences {
        return copy(lastSyncTimestamp = timestamp, updatedAt = System.currentTimeMillis())
    }

    fun withFavoriteCategories(categories: List<ProductCategory>): UserPreferences {
        return copy(favoriteCategories = categories, updatedAt = System.currentTimeMillis())
    }

    fun withPreferredPaymentMethod(method: PaymentMethod?): UserPreferences {
        return copy(preferredPaymentMethod = method, updatedAt = System.currentTimeMillis())
    }

    fun addFavoriteCategory(category: ProductCategory): UserPreferences {
        if (favoriteCategories.contains(category)) return this
        return copy(
            favoriteCategories = favoriteCategories + category,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun removeFavoriteCategory(category: ProductCategory): UserPreferences {
        return copy(
            favoriteCategories = favoriteCategories.filter { it != category },
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withSyncSettings(
        syncOnWifiOnly: Boolean,
        offlineDataRetentionDays: Int,
        maxOfflineProducts: Int
    ): UserPreferences {
        return copy(
            syncOnWifiOnly = syncOnWifiOnly,
            offlineDataRetentionDays = offlineDataRetentionDays.coerceIn(1, 365),
            maxOfflineProducts = maxOfflineProducts.coerceIn(10, 10000),
            updatedAt = System.currentTimeMillis()
        )
    }

    fun withNotificationPreferences(
        priceDrops: Boolean,
        newProducts: Boolean
    ): UserPreferences {
        return copy(
            notificationsForPriceDrops = priceDrops,
            notificationsForNewProducts = newProducts,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun getNotificationSettings(): Map<String, Boolean> {
        return mapOf(
            "general" to notificationsEnabled,
            "priceDrops" to notificationsForPriceDrops,
            "newProducts" to notificationsForNewProducts
        )
    }

    fun getSyncConfiguration(): SyncConfiguration {
        return SyncConfiguration(
            autoSyncEnabled = autoSyncEnabled,
            syncOnWifiOnly = syncOnWifiOnly,
            lastSyncTimestamp = lastSyncTimestamp,
            offlineDataRetentionDays = offlineDataRetentionDays,
            maxOfflineProducts = maxOfflineProducts
        )
    }
}

enum class ThemeMode(val displayName: String, val value: Int) {
    LIGHT("Claro", 0),
    DARK("Oscuro", 1),
    SYSTEM("Sistema", 2);

    companion object {
        fun fromValue(value: Int): ThemeMode {
            return entries.find { it.value == value } ?: SYSTEM
        }
    }
}

data class SyncConfiguration(
    val autoSyncEnabled: Boolean,
    val syncOnWifiOnly: Boolean,
    val lastSyncTimestamp: Long,
    val offlineDataRetentionDays: Int,
    val maxOfflineProducts: Int
)

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/model/SyncStatus.kt ===
package com.example.offlinefirst.domain.model

import java.util.UUID

data class SyncStatus(
    val id: String = UUID.randomUUID().toString(),
    val entityType: EntityType,
    val entityId: String,
    val status: SyncState = SyncState.PENDING,
    val localTimestamp: Long = System.currentTimeMillis(),
    val remoteTimestamp: Long? = null,
    val errorMessage: String? = null,
    val retryCount: Int = 0,
    val lastAttemptTimestamp: Long? = null,
    val conflictData: ConflictData? = null
) {
    fun isPending(): Boolean = status == SyncState.PENDING
    fun isSyncing(): Boolean = status == SyncState.SYNCING
    fun isSynced(): Boolean = status == SyncState.SYNCED
    fun isFailed(): Boolean = status == SyncState.FAILED
    fun hasConflict(): Boolean = conflictData != null
    fun canRetry(): Boolean = retryCount < MAX_RETRY_COUNT && (status == SyncState.FAILED || status == SyncState.PENDING)

    fun markAsSyncing(): SyncStatus {
        return copy(
            status = SyncState.SYNCING,
            lastAttemptTimestamp = System.currentTimeMillis()
        )
    }

    fun markAsSynced(remoteTimestamp: Long = System.currentTimeMillis()): SyncStatus {
        return copy(
            status = SyncState.SYNCED,
            remoteTimestamp = remoteTimestamp,
            errorMessage = null,
            retryCount = 0
        )
    }

    fun markAsFailed(error: String): SyncStatus {
        return copy(
            status = SyncState.FAILED,
            errorMessage = error,
            retryCount = retryCount + 1,
            lastAttemptTimestamp = System.currentTimeMillis()
        )
    }

    fun markAsPending(): SyncStatus {
        return copy(status = SyncState.PENDING, errorMessage = null)
    }

    fun withConflict(conflictData: ConflictData): SyncStatus {
        return copy(
            status = SyncState.CONFLICT,
            conflictData = conflictData
        )
    }

    fun resolveConflict(resolution: ConflictResolution): SyncStatus {
        return when (resolution) {
            ConflictResolution.USE_LOCAL -> markAsSynced()
            ConflictResolution.USE_REMOTE -> markAsSynced()
            ConflictResolution.MERGE -> markAsSynced()
        }.copy(conflictData = null)
    }

    fun getTimeSinceLastAttempt(): Long? {
        return lastAttemptTimestamp?.let { System.currentTimeMillis() - it }
    }

    fun shouldRetry(): Boolean {
        if (!canRetry()) return false
        val timeSinceLastAttempt = getTimeSinceLastAttempt() ?: return true
        return timeSinceLastAttempt > calculateBackoffDelay()
    }

    private fun calculateBackoffDelay(): Long {
        val baseDelay = 1000L
        val maxDelay = 30000L
        val delay = baseDelay * (1 shl retryCount)
        return delay.coerceAtMost(maxDelay)
    }

    fun toDisplayString(): String {
        return when (status) {
            SyncState.PENDING -> "Pendiente de sincronizar"
            SyncState.SYNCING -> "Sincronizando..."
            SyncState.SYNCED -> "Sincronizado"
            SyncState.FAILED -> "Error: ${errorMessage ?: "Desconocido"}
            SyncState.CONFLICT -> "Conflicto detectado"
        }
    }

    companion object {
        const val MAX_RETRY_COUNT = 3

        fun createForEntity(entityType: EntityType, entityId: String): SyncStatus {
            return SyncStatus(
                entityType = entityType,
                entityId = entityId
            )
        }
    }
}

enum class SyncState(val displayName: String) {
    PENDING("Pendiente"),
    SYNCING("Sincronizando"),
    SYNCED("Sincronizado"),
    FAILED("Fallido"),
    CONFLICT("Conflicto");

    fun isActive(): Boolean = this == PENDING || this == SYNCING
    fun isTerminal(): Boolean = this == SYNCED || this == FAILED
}

enum class EntityType(val displayName: String, val tableName: String) {
    PRODUCT("Producto", "products"),
    PURCHASE("Compra", "purchases"),
    USER_PREFERENCES("Preferencias", "user_preferences");

    fun getSyncPriority(): Int {
        return when (this) {
            PRODUCT -> 1
            PURCHASE -> 2
            USER_PREFERENCES -> 3
        }
    }
}

data class ConflictData(
    val localVersion: String,
    val remoteVersion: String,
    val localTimestamp: Long,
    val remoteTimestamp: Long,
    val conflictFields: List<String>
) {
    fun getOlderVersion(): VersionInfo {
        return if (localTimestamp < remoteTimestamp) {
            VersionInfo("local", localVersion, localTimestamp)
        } else {
            VersionInfo("remote", remoteVersion, remoteTimestamp)
        }
    }

    fun getNewerVersion(): VersionInfo {
        return if (localTimestamp > remoteTimestamp) {
            VersionInfo("local", localVersion, localTimestamp)
        } else {
            VersionInfo("remote", remoteVersion, remoteTimestamp)
        }
    }

    fun hasFieldConflict(field: String): Boolean {
        return conflictFields.contains(field)
    }
}

data class VersionInfo(
    val source: String,
    val version: String,
    val timestamp: Long
)

enum class ConflictResolution(val displayName: String) {
    USE_LOCAL("Usar versión local"),
    USE_REMOTE("Usar versión remota"),
    MERGE("Combinar versiones")
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/repository/ProductRepository.kt ===
package com.example.offlinefirst.domain.repository

import com.example.offlinefirst.domain.model.Product
import com.example.offlinefirst.domain.model.ProductCategory
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<Resource<List<Product>>>
    
    fun getProductById(id: String): Flow<Resource<Product>>
    
    fun getProductsByCategory(category: ProductCategory): Flow<Resource<List<Product>>>
    
    fun searchProducts(query: String): Flow<Resource<List<Product>>>
    
    suspend fun saveProduct(product: Product): Resource<Product>
    
    suspend fun saveProducts(products: List<Product>): Resource<List<Product>>
    
    suspend fun deleteProduct(id: String): Resource<Unit>
    
    suspend fun deleteAllProducts(): Resource<Unit>
    
    fun getSyncStatusForProduct(id: String): Flow<Resource<SyncStatus>>
    
    fun getPendingSyncProducts(): Flow<Resource<List<Product>>>
    
    suspend fun syncProducts(): Resource<List<Product>>
    
    suspend fun getProductCount(): Int
    
    suspend fun getProductsOlderThan(timestamp: Long): List<Product>
    
    suspend fun updateProductStock(productId: String, newStock: Int): Resource<Product>
    
    fun observeProducts(): Flow<List<Product>>
    
    fun observeProductById(id: String): Flow<Product?>
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/repository/PurchaseRepository.kt ===
package com.example.offlinefirst.domain.repository

import com.example.offlinefirst.domain.model.PurchaseHistory
import com.example.offlinefirst.domain.model.PurchaseStatus
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow

interface PurchaseRepository {
    fun getPurchases(): Flow<Resource<List<PurchaseHistory>>>
    
    fun getPurchaseById(id: String): Flow<Resource<PurchaseHistory>>
    
    fun getPurchasesByStatus(status: PurchaseStatus): Flow<Resource<List<PurchaseHistory>>>
    
    fun getActivePurchases(): Flow<Resource<List<PurchaseHistory>>>
    
    fun getPurchasesByDateRange(
        startDate: Long,
        endDate: Long
    ): Flow<Resource<List<PurchaseHistory>>>
    
    suspend fun savePurchase(purchase: PurchaseHistory): Resource<PurchaseHistory>
    
    suspend fun savePurchases(purchases: List<PurchaseHistory>): Resource<List<PurchaseHistory>>
    
    suspend fun updatePurchaseStatus(
        purchaseId: String,
        newStatus: PurchaseStatus
    ): Resource<PurchaseHistory>
    
    suspend fun updateShippingInfo(
        purchaseId: String,
        trackingNumber: String?,
        deliveryDate: Long?
    ): Resource<PurchaseHistory>
    
    suspend fun deletePurchase(id: String): Resource<Unit>
    
    suspend fun deleteAllPurchases(): Resource<Unit>
    
    fun getSyncStatusForPurchase(id: String): Flow<Resource<SyncStatus>>
    
    fun getPendingSyncPurchases(): Flow<Resource<List<PurchaseHistory>>>
    
    suspend fun syncPurchases(): Resource<List<PurchaseHistory>>
    
    suspend fun getPurchaseCount(): Int
    
    suspend fun getTotalSpent(): Double
    
    fun observePurchases(): Flow<List<PurchaseHistory>>
    
    fun observePurchaseById(id: String): Flow<PurchaseHistory?>
    
    fun getPurchasesByProductId(productId: String): Flow<Resource<List<PurchaseHistory>>>
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/repository/UserPreferencesRepository.kt ===
package com.example.offlinefirst.domain.repository

import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.ThemeMode
import com.example.offlinefirst.domain.model.UserPreferences
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    fun getUserPreferences(userId: String): Flow<Resource<UserPreferences>>
    
    fun getDefaultPreferences(userId: String): UserPreferences
    
    suspend fun savePreferences(preferences: UserPreferences): Resource<UserPreferences>
    
    suspend fun updateTheme(userId: String, theme: ThemeMode): Resource<UserPreferences>
    
    suspend fun updateNotifications(
        userId: String,
        enabled: Boolean,
        priceDrops: Boolean? = null,
        newProducts: Boolean? = null
    ): Resource<UserPreferences>
    
    suspend fun updateAutoSync(
        userId: String,
        enabled: Boolean,
        wifiOnly: Boolean? = null
    ): Resource<UserPreferences>
    
    suspend fun updateFavoriteCategories(
        userId: String,
        categories: List<com.example.offlinefirst.domain.model.ProductCategory>
    ): Resource<UserPreferences>
    
    suspend fun updatePreferredPaymentMethod(
        userId: String,
        paymentMethod: com.example.offlinefirst.domain.model.PaymentMethod?
    ): Resource<UserPreferences>
    
    suspend fun updateSyncRetention(
        userId: String,
        retentionDays: Int,
        maxProducts: Int
    ): Resource<UserPreferences>
    
    suspend fun updateLastSyncTimestamp(userId: String, timestamp: Long): Resource<UserPreferences>
    
    suspend fun deletePreferences(userId: String): Resource<Unit>
    
    fun getSyncStatus(): Flow<Resource<SyncStatus>>
    
    suspend fun syncPreferences(): Resource<UserPreferences>
    
    fun observePreferences(userId: String): Flow<UserPreferences?>
    
    suspend fun hasPreferences(userId: String): Boolean
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/usecase/ProductUseCases.kt ===
package com.example.offlinefirst.domain.usecase

import com.example.offlinefirst.domain.model.Product
import com.example.offlinefirst.domain.repository.ProductRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(forceRefresh: Boolean = false): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())
        try {
            val cachedProducts = productRepository.getProducts().first()
            if (cachedProducts.isNotEmpty() && !forceRefresh) {
                emit(Resource.Success(cachedProducts))
            }
            if (forceRefresh || cachedProducts.isEmpty()) {
                val remoteProducts = productRepository.syncProducts()
                emit(Resource.Success(remoteProducts))
            } else {
                emit(Resource.Success(cachedProducts))
            }
        } catch (e: Exception) {
            val cachedProducts = productRepository.getProducts().first()
            if (cachedProducts.isNotEmpty()) {
                emit(Resource.Success(cachedProducts))
            } else {
                emit(Resource.Error(e.message ?: "Error al obtener productos", e))
            }
        }
    }

    fun getProductsFromCache(): Flow<List<Product>> = productRepository.getProducts()

    fun observeProducts(): Flow<List<Product>> = productRepository.getProducts()
}

class GetProductByIdUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(productId: String): Resource<Product> {
        return try {
            val product = productRepository.getProductById(productId)
            if (product != null) {
                Resource.Success(product)
            } else {
                Resource.Error("Producto no encontrado")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al obtener producto", e)
        }
    }
}

class SaveProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(product: Product): Resource<Product> {
        return try {
            if (!product.isValid()) {
                return Resource.Error("Producto inválido")
            }
            val savedProduct = productRepository.saveProduct(product)
            Resource.Success(savedProduct)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al guardar producto", e)
        }
    }

    suspend fun saveProducts(products: List<Product>): Resource<Int> {
        return try {
            var savedCount = 0
            products.forEach { product ->
                if (product.isValid()) {
                    productRepository.saveProduct(product)
                    savedCount++
                }
            }
            Resource.Success(savedCount)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al guardar productos", e)
        }
    }
}

class DeleteProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(productId: String): Resource<Unit> {
        return try {
            productRepository.deleteProduct(productId)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al eliminar producto", e)
        }
    }
}

class SearchProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(query: String): Resource<List<Product>> {
        return try {
            val products = productRepository.searchProducts(query)
            Resource.Success(products)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al buscar productos", e)
        }
    }

    fun searchProductsByCategory(category: String): Flow<List<Product>> {
        return productRepository.getProductsByCategory(category)
    }
}

class SyncProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): Resource<List<Product>> {
        return try {
            val products = productRepository.syncProducts()
            Resource.Success(products)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al sincronizar productos", e)
        }
    }

    fun observeSyncStatus(): Flow<Boolean> = productRepository.observePendingSync()
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/usecase/PurchaseUseCases.kt ===
package com.example.offlinefirst.domain.usecase

import com.example.offlinefirst.domain.model.PurchaseHistory
import com.example.offlinefirst.domain.model.PurchaseStatus
import com.example.offlinefirst.domain.repository.PurchaseRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPurchaseHistoryUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    operator fun invoke(): Flow<Resource<List<PurchaseHistory>>> = flow {
        emit(Resource.Loading())
        try {
            val purchases = purchaseRepository.getPurchases().first()
            emit(Resource.Success(purchases))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al obtener historial de compras", e))
        }
    }

    fun observePurchases(): Flow<List<PurchaseHistory>> = purchaseRepository.getPurchases()

    suspend fun getActivePurchases(): List<PurchaseHistory> {
        return purchaseRepository.getPurchases().first()
            .filter { it.isActive() }
    }

    suspend fun getPurchasesByStatus(status: PurchaseStatus): List<PurchaseHistory> {
        return purchaseRepository.getPurchases().first()
            .filter { it.status == status }
    }
}

class GetPurchaseByIdUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(purchaseId: String): Resource<PurchaseHistory> {
        return try {
            val purchase = purchaseRepository.getPurchaseById(purchaseId)
            if (purchase != null) {
                Resource.Success(purchase)
            } else {
                Resource.Error("Compra no encontrada")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al obtener compra", e)
        }
    }
}

class CreatePurchaseUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(purchase: PurchaseHistory): Resource<PurchaseHistory> {
        return try {
            if (!purchase.isValid()) {
                return Resource.Error("Datos de compra inválidos")
            }
            val savedPurchase = purchaseRepository.savePurchase(purchase)
            Resource.Success(savedPurchase)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al crear compra", e)
        }
    }
}

class CancelPurchaseUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(purchaseId: String): Resource<PurchaseHistory> {
        return try {
            val purchase = purchaseRepository.getPurchaseById(purchaseId)
                ?: return Resource.Error("Compra no encontrada")

            if (!purchase.canBeCancelled()) {
                return Resource.Error("Esta compra no puede ser cancelada")
            }

            val cancelledPurchase = purchase.withStatus(PurchaseStatus.CANCELLED)
            val updated = purchaseRepository.savePurchase(cancelledPurchase)
            Resource.Success(updated)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al cancelar compra", e)
        }
    }
}

class UpdatePurchaseStatusUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(
        purchaseId: String,
        newStatus: PurchaseStatus,
        trackingNumber: String? = null,
        deliveryDate: Long? = null
    ): Resource<PurchaseHistory> {
        return try {
            val purchase = purchaseRepository.getPurchaseById(purchaseId)
                ?: return Resource.Error("Compra no encontrada")

            val updatedPurchase = purchase
                .withStatus(newStatus)
                .withShippingInfo(trackingNumber, deliveryDate)

            val saved = purchaseRepository.savePurchase(updatedPurchase)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar estado", e)
        }
    }
}

class SyncPurchasesUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(): Resource<List<PurchaseHistory>> {
        return try {
            val purchases = purchaseRepository.syncPurchases()
            Resource.Success(purchases)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al sincronizar compras", e)
        }
    }

    fun getPendingSyncPurchases(): Flow<List<PurchaseHistory>> {
        return purchaseRepository.getPendingSyncPurchases()
    }
}

class GetPurchaseStatisticsUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(): Resource<PurchaseStatistics> {
        return try {
            val purchases = purchaseRepository.getPurchases().first()
            val totalPurchases = purchases.size
            val totalSpent = purchases.sumOf { it.totalPrice.toDouble() }
            val activePurchases = purchases.count { it.isActive() }
            val cancelledPurchases = purchases.count { it.status == PurchaseStatus.CANCELLED }

            Resource.Success(
                PurchaseStatistics(
                    totalPurchases = totalPurchases,
                    totalSpent = totalSpent,
                    activePurchases = activePurchases,
                    cancelledPurchases = cancelledPurchases
                )
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al calcular estadísticas", e)
        }
    }
}

data class PurchaseStatistics(
    val totalPurchases: Int,
    val totalSpent: Double,
    val activePurchases: Int,
    val cancelledPurchases: Int
)

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/usecase/UserPreferencesUseCases.kt ===
package com.example.offlinefirst.domain.usecase

import com.example.offlinefirst.domain.model.UserPreferences
import com.example.offlinefirst.domain.repository.UserPreferencesRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserPreferencesUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) {
    operator fun invoke(): Flow<Resource<UserPreferences>> = flow {
        emit(Resource.Loading())
        try {
            val preferences = userPreferencesRepository.getUserPreferences().first()
            emit(Resource.Success(preferences))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al obtener preferencias", e))
        }
    }

    fun observePreferences(): Flow<UserPreferences> = userPreferencesRepository.getUserPreferences()
}

class UpdateUserPreferencesUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) {
    suspend operator fun invoke(preferences: UserPreferences): Resource<UserPreferences> {
        return try {
            val updated = userPreferencesRepository.savePreferences(preferences)
            Resource.Success(updated)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al guardar preferencias", e)
        }
    }

    suspend fun updateTheme(theme: String): Resource<UserPreferences> {
        return try {
            val current = userPreferencesRepository.getUserPreferences().first()
            val updated = current.copy(theme = theme)
            val saved = userPreferencesRepository.savePreferences(updated)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar tema", e)
        }
    }

    suspend fun updateLanguage(language: String): Resource<UserPreferences> {
        return try {
            val current = userPreferencesRepository.getUserPreferences().first()
            val updated = current.copy(language = language)
            val saved = userPreferencesRepository.savePreferences(updated)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar idioma", e)
        }
    }

    suspend fun updateNotificationsEnabled(enabled: Boolean): Resource<UserPreferences> {
        return try {
            val current = userPreferencesRepository.getUserPreferences().first()
            val updated = current.copy(notificationsEnabled = enabled)
            val saved = userPreferencesRepository.savePreferences(updated)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar notificaciones", e)
        }
    }

    suspend fun updateAutoSync(enabled: Boolean): Resource<UserPreferences> {
        return try {
            val current = userPreferencesRepository.getUserPreferences().first()
            val updated = current.copy(autoSyncEnabled = enabled)
            val saved = userPreferencesRepository.savePreferences(updated)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar sincronización automática", e)
        }
    }

    suspend fun updateSyncInterval(intervalMinutes: Int): Resource<UserPreferences> {
        return try {
            if (intervalMinutes < 15 || intervalMinutes > 1440) {
                return Resource.Error("Intervalo de sincronización inválido")
            }
            val current = userPreferencesRepository.getUserPreferences().first()
            val updated = current.copy(syncIntervalMinutes = intervalMinutes)
            val saved = userPreferencesRepository.savePreferences(updated)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar intervalo", e)
        }
    }

    suspend fun updateDataSavingMode(enabled: Boolean): Resource<UserPreferences> {
        return try {
            val current = userPreferencesRepository.getUserPreferences().first()
            val updated = current.copy(dataSavingMode = enabled)
            val saved = userPreferencesRepository.savePreferences(updated)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar modo ahorro de datos", e)
        }
    }
}

class ResetUserPreferencesUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) {
    suspend operator fun invoke(): Resource<UserPreferences> {
        return try {
            val defaultPreferences = UserPreferences()
            val saved = userPreferencesRepository.savePreferences(defaultPreferences)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al restaurar preferencias", e)
        }
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt ===
package com.example.offlinefirst.domain.usecase

import com.example.offlinefirst.data.sync.NetworkConnectivityManager
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.repository.ProductRepository
import com.example.offlinefirst.domain.repository.PurchaseRepository
import com.example.offlinefirst.domain.repository.UserPreferencesRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SyncDataUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val purchaseRepository: PurchaseRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val networkConnectivityManager: NetworkConnectivityManager
) {
    suspend operator fun invoke(
        syncProducts: Boolean = true,
        syncPurchases: Boolean = true
    ): Resource<SyncResult> = flow {
        emit(Resource.Loading())

        if (!networkConnectivityManager.isNetworkAvailable()) {
            emit(Resource.Error("No hay conexión a Internet"))
            return@flow
        }

        var productsSynced = 0
        var purchasesSynced = 0
        var errors = mutableListOf<String>()

        try {
            if (syncProducts) {
                try {
                    productRepository.syncProducts()
                    productsSynced++
                } catch (e: Exception) {
                    errors.add("Error sincronizando productos: ${e.message}")
                }
            }

            if (syncPurchases) {
                try {
                    purchaseRepository.syncPurchases()
                    purchasesSynced++
                } catch (e: Exception) {
                    errors.add("Error sincronizando compras: ${e.message}")
                }
            }

            val result = SyncResult(
                productsSynced = productsSynced > 0,
                purchasesSynced = purchasesSynced > 0,
                errors = errors,
                timestamp = System.currentTimeMillis()
            )

            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error en sincronización", e))
        }
    }

    fun observeNetworkStatus(): Flow<Boolean> = networkConnectivityManager.isConnected()

    suspend fun checkPendingSync(): PendingSyncInfo {
        val pendingProducts = productRepository.getProducts().first()
            .count { /* lógica para determinar si hay productos pendientes */ false }

        val pendingPurchases = purchaseRepository.getPendingSyncPurchases().first().size

        return PendingSyncInfo(
            hasPendingProducts = pendingProducts > 0,
            hasPendingPurchases = pendingPurchases > 0,
            totalPendingItems = pendingProducts + pendingPurchases
        )
    }
}

data class SyncResult(
    val productsSynced: Boolean,
    val purchasesSynced: Boolean,
    val errors: List<String>,
    val timestamp: Long
) {
    fun isSuccess(): Boolean = productsSynced || purchasesSynced
    fun hasErrors(): Boolean = errors.isNotEmpty()
}

data class PendingSyncInfo(
    val hasPendingProducts: Boolean,
    val hasPendingPurchases: Boolean,
    val totalPendingItems: Int
)

class GetSyncStatusUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(): Resource<SyncStatus> {
        return try {
            val products = productRepository.getProducts().first()
            val purchases = purchaseRepository.getPurchases().first()

            val lastSyncTime = System.currentTimeMillis()
            val pendingCount = purchases.count { /* pending sync */ false }

            val status = SyncStatus(
                lastSyncTime = lastSyncTime,
                pendingChanges = pendingCount,
                isSyncing = false,
                lastError = null
            )

            Resource.Success(status)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al obtener estado de sincronización", e)
        }
    }

    fun observeSyncStatus(): Flow<SyncStatus> = flow {
        val products = productRepository.getProducts().first()
        val purchases = purchaseRepository.getPurchases().first()

        emit(
            SyncStatus(
                lastSyncTime = System.currentTimeMillis(),
                pendingChanges = 0,
                isSyncing = false,
                lastError = null
            )
        )
    }
}

// === ARCHIVO: app/src/main/AndroidManifest.xml ===
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.MOBILE_DATA" />
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
    <uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
    <uses-permission android:name="android.permission.VIBRATE" />

    <application
        android:name=".OfflineFirstApp"
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.OfflineFirst"
        android:networkSecurityConfig="@xml/network_security_config"
        tools:targetApi="35">

        <activity
            android:name=".presentation.ui.MainActivity"
            android:exported="true"
            android:theme="@style/Theme.OfflineFirst">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <provider
            android:name="androidx.startup.InitializationProvider"
            android:authorities="${applicationId}.androidx-startup"
            android:exported="false"
            tools:node="merge">
            <meta-data
                android:name="androidx.work.WorkManagerInitializer"
                android:value="androidx.startup"
                tools:node="remove" />
        </provider>

        <service
            android:name="androidx.work.impl.foreground.SystemForegroundService"
            android:foregroundServiceType="dataSync"
            tools:node="merge" />

        <receiver
            android:name=".data.sync.NetworkChangeReceiver"
            android:exported="false">
            <intent-filter>
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
                <action android:name="android.net.wifi.WIFI_STATE_CHANGED" />
            </intent-filter>
        </receiver>

        <receiver
            android:name="androidx.work.impl.background.systemalarm.ConstraintProxy$BatteryChargingProxy"
            android:exported="false" />

        <receiver
            android:name="androidx.work.impl.background.systemalarm.ConstraintProxy$BatteryNotLowProxy"
            android:exported="false" />

        <receiver
            android:name="androidx.work.impl.background.systemalarm.ConstraintProxy$StorageNotLowProxy"
            android:exported="false" />

        <receiver
            android:name="androidx.work.impl.background.systemalarm.ConstraintProxy$NetworkStateProxy"
            android:exported="false" />

    </application>

</manifest>

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/OfflineFirstApp.kt ===
package com.example.offlinefirst

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class OfflineFirstApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()

    override fun onCreate() {
        super.onCreate()
        initializeApp()
    }

    private fun initializeApp() {
        val appVersion = packageManager.getPackageInfo(packageName, 0).versionName
        android.util.Log.i(TAG, "OfflineFirstApp v$appVersion initialized")
    }

    companion object {
        private const val TAG = "OfflineFirstApp"
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/entity/ProductEntity.kt ===
package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.offlinefirst.domain.model.Product
import com.example.offlinefirst.domain.model.ProductCategory
import java.math.BigDecimal

@Entity(
    tableName = "products",
    indices = [
        Index(value = ["category"]),
        Index(value = ["is_available"]),
        Index(value = ["last_synced_at"])
    ]
)
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "price")
    val price: Long,

    @ColumnInfo(name = "original_price")
    val originalPrice: Long?,

    @ColumnInfo(name = "stock")
    val stock: Int,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "rating")
    val rating: Float,

    @ColumnInfo(name = "rating_count")
    val ratingCount: Int,

    @ColumnInfo(name = "image_url")
    val imageUrl: String?,

    @ColumnInfo(name = "is_available")
    val isAvailable: Boolean,

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean,

    @ColumnInfo(name = "created_at")
    val createdAt: Long,

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long,

    @ColumnInfo(name = "last_synced_at")
    val lastSyncedAt: Long,

    @ColumnInfo(name = "sync_status")
    val syncStatus: Int,

    @ColumnInfo(name = "version")
    val version: Int
) {
    fun toDomain(): Product {
        return Product(
            id = id,
            name = name,
            description = description,
            price = BigDecimal(price).divide(BigDecimal(100)),
            originalPrice = originalPrice?.let { BigDecimal(it).divide(BigDecimal(100)) },
            stock = stock,
            category = try {
                ProductCategory.valueOf(category)
            } catch (e: Exception) {
                ProductCategory.OTHER
            },
            rating = rating,
            ratingCount = ratingCount,
            imageUrl = imageUrl,
            isAvailable = isAvailable,
            isFavorite = isFavorite,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    companion object {
        fun fromDomain(product: Product, syncStatus: Int = 0, version: Int = 1): ProductEntity {
            return ProductEntity(
                id = product.id,
                name = product.name,
                description = product.description,
                price = product.price.multiply(BigDecimal(100)).toLong(),
                originalPrice = product.originalPrice?.multiply(BigDecimal(100))?.toLong(),
                stock = product.stock,
                category = product.category.name,
                rating = product.rating,
                ratingCount = product.ratingCount,
                imageUrl = product.imageUrl,
                isAvailable = product.isAvailable,
                isFavorite = product.isFavorite,
                createdAt = product.createdAt,
                updatedAt = product.updatedAt,
                lastSyncedAt = System.currentTimeMillis(),
                syncStatus = syncStatus,
                version = version
            )
        }

        const val SYNC_STATUS_SYNCED = 0
        const val SYNC_STATUS_PENDING_CREATE = 1
        const val SYNC_STATUS_PENDING_UPDATE = 2
        const val SYNC_STATUS_PENDING_DELETE = 3
        const val SYNC_STATUS_CONFLICT = 4
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/entity/PurchaseHistoryEntity.kt ===
package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.offlinefirst.domain.model.PurchaseHistory
import com.example.offlinefirst.domain.model.PurchaseStatus
import com.example.offlinefirst.domain.model.PaymentMethod
import java.math.BigDecimal

@Entity(
    tableName = "purchase_history",
    foreignKeys = [
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["id"],
            childColumns = ["product_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index(value = ["product_id"]),
        Index(value = ["status"]),
        Index(value = ["purchase_date"]),
        Index(value = ["last_synced_at"])
    ]
)
data class PurchaseHistoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "product_id")
    val productId: String?,

    @ColumnInfo(name = "product_name")
    val productName: String,

    @ColumnInfo(name = "product_image_url")
    val productImageUrl: String?,

    @ColumnInfo(name = "quantity")
    val quantity: Int,

    @ColumnInfo(name = "unit_price")
    val unitPrice: Long,

    @ColumnInfo(name = "total_price")
    val totalPrice: Long,

    @ColumnInfo(name = "purchase_date")
    val purchaseDate: Long,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "payment_method")
    val paymentMethod: String,

    @ColumnInfo(name = "tracking_number")
    val trackingNumber: String?,

    @ColumnInfo(name = "delivery_date")
    val deliveryDate: Long?,

    @ColumnInfo(name = "shipping_address")
    val shippingAddress: String?,

    @ColumnInfo(name = "notes")
    val notes: String?,

    @ColumnInfo(name = "created_at")
    val createdAt: Long,

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long,

    @ColumnInfo(name = "last_synced_at")
    val lastSyncedAt: Long,

    @ColumnInfo(name = "sync_status")
    val syncStatus: Int,

    @ColumnInfo(name = "version")
    val version: Int
) {
    fun toDomain(): PurchaseHistory {
        return PurchaseHistory(
            id = id,
            productId = productId,
            productName = productName,
            productImageUrl = productImageUrl,
            quantity = quantity,
            unitPrice = BigDecimal(unitPrice).divide(BigDecimal(100)),
            totalPrice = BigDecimal(totalPrice).divide(BigDecimal(100)),
            purchaseDate = purchaseDate,
            status = try {
                PurchaseStatus.valueOf(status)
            } catch (e: Exception) {
                PurchaseStatus.UNKNOWN
            },
            paymentMethod = try {
                PaymentMethod.valueOf(paymentMethod)
            } catch (e: Exception) {
                PaymentMethod.CASH_ON_DELIVERY
            },
            trackingNumber = trackingNumber,
            deliveryDate = deliveryDate,
            shippingAddress = shippingAddress,
            notes = notes,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    companion object {
        fun fromDomain(purchase: PurchaseHistory, syncStatus: Int = 0, version: Int = 1): PurchaseHistoryEntity {
            return PurchaseHistoryEntity(
                id = purchase.id,
                productId = purchase.productId,
                productName = purchase.productName,
                productImageUrl = purchase.productImageUrl,
                quantity = purchase.quantity,
                unitPrice = purchase.unitPrice.multiply(BigDecimal(100)).toLong(),
                totalPrice = purchase.totalPrice.multiply(BigDecimal(100)).toLong(),
                purchaseDate = purchase.purchaseDate,
                status = purchase.status.name,
                paymentMethod = purchase.paymentMethod.name,
                trackingNumber = purchase.trackingNumber,
                deliveryDate = purchase.deliveryDate,
                shippingAddress = purchase.shippingAddress,
                notes = purchase.notes,
                createdAt = purchase.createdAt,
                updatedAt = purchase.updatedAt,
                lastSyncedAt = System.currentTimeMillis(),
                syncStatus = syncStatus,
                version = version
            )
        }

        const val SYNC_STATUS_SYNCED = 0
        const val SYNC_STATUS_PENDING_CREATE = 1
        const val SYNC_STATUS_PENDING_UPDATE = 2
        const val SYNC_STATUS_PENDING_DELETE = 3
    }
}

@Entity(
    tableName = "purchase_history_with_product",
    indices = [Index(value = ["purchase_id"])]
)
data class PurchaseHistoryWithProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "purchase_id")
    val purchaseId: String,

    @ColumnInfo(name = "product_id")
    val productId: String?,

    @ColumnInfo(name = "product_name")
    val productName: String,

    @ColumnInfo(name = "product_image_url")
    val productImageUrl: String?,

    @ColumnInfo(name = "quantity")
    val quantity: Int,

    @ColumnInfo(name = "total_price")
    val totalPrice: Long,

    @ColumnInfo(name = "purchase_date")
    val purchaseDate: Long,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "payment_method")
    val paymentMethod: String
)

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/entity/UserPreferencesEntity.kt ===
package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.offlinefirst.domain.model.UserPreferences
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity(
    tableName = "user_preferences",
    indices = [Index(value = ["last_synced_at"])]
)
data class UserPreferencesEntity(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val userId: String,

    @ColumnInfo(name = "display_name")
    val displayName: String,

    @ColumnInfo(name = "email")
    val email: String?,

    @ColumnInfo(name = "notifications_enabled")
    val notificationsEnabled: Boolean,

    @ColumnInfo(name = "email_notifications")
    val emailNotifications: Boolean,

    @ColumnInfo(name = "push_notifications")
    val pushNotifications: Boolean,

    @ColumnInfo(name = "language")
    val language: String,

    @ColumnInfo(name = "currency")
    val currency: String,

    @ColumnInfo(name = "dark_mode")
    val darkMode: String,

    @ColumnInfo(name = "auto_sync")
    val autoSync: Boolean,

    @ColumnInfo(name = "sync_wifi_only")
    val syncWifiOnly: Boolean,

    @ColumnInfo(name = "sync_interval_minutes")
    val syncIntervalMinutes: Int,

    @ColumnInfo(name = "data_saver_mode")
    val dataSaverMode: Boolean,

    @ColumnInfo(name = "offline_products_cache")
    val offlineProductsCache: Boolean,

    @ColumnInfo(name = "max_cache_size_mb")
    val maxCacheSizeMb: Int,

    @ColumnInfo(name = "preferred_payment_method")
    val preferredPaymentMethod: String?,

    @ColumnInfo(name = "shipping_addresses")
    val shippingAddressesJson: String,

    @ColumnInfo(name = "recent_searches")
    val recentSearchesJson: String,

    @ColumnInfo(name = "favorite_categories")
    val favoriteCategoriesJson: String,

    @ColumnInfo(name = "last_sync_timestamp")
    val lastSyncTimestamp: Long,

    @ColumnInfo(name = "last_synced_at")
    val lastSyncedAt: Long,

    @ColumnInfo(name = "sync_status")
    val syncStatus: Int,

    @ColumnInfo(name = "version")
    val version: Int,

    @ColumnInfo(name = "created_at")
    val createdAt: Long,

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long
) {
    fun toDomain(): UserPreferences {
        val json = Json { ignoreUnknownKeys = true }
        
        val shippingAddresses = try {
            json.decodeFromString<List<ShippingAddressJson>>(shippingAddressesJson)
        } catch (e: Exception) {
            emptyList()
        }

        val recentSearches = try {
            json.decodeFromString<List<String>>(recentSearchesJson)
        } catch (e: Exception) {
            emptyList()
        }

        val favoriteCategories = try {
            json.decodeFromString<List<String>>(favoriteCategoriesJson)
        } catch (e: Exception) {
            emptyList()
        }

        return UserPreferences(
            userId = userId,
            displayName = displayName,
            email = email,
            notificationsEnabled = notificationsEnabled,
            emailNotifications = emailNotifications,
            pushNotifications = pushNotifications,
            language = language,
            currency = currency,
            darkMode = darkMode,
            autoSync = autoSync,
            syncWifiOnly = syncWifiOnly,
            syncIntervalMinutes = syncIntervalMinutes,
            dataSaverMode = dataSaverMode,
            offlineProductsCache = offlineProductsCache,
            maxCacheSizeMb = maxCacheSizeMb,
            preferredPaymentMethod = preferredPaymentMethod,
            shippingAddresses = shippingAddresses.map { it.toDomain() },
            recentSearches = recentSearches,
            favoriteCategories = favoriteCategories,
            lastSyncTimestamp = lastSyncTimestamp,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    companion object {
        fun fromDomain(preferences: UserPreferences, syncStatus: Int = 0, version: Int = 1): UserPreferencesEntity {
            val json = Json { ignoreUnknownKeys = true }
            
            return UserPreferencesEntity(
                userId = preferences.userId,
                displayName = preferences.displayName,
                email = preferences.email,
                notificationsEnabled = preferences.notificationsEnabled,
                emailNotifications = preferences.emailNotifications,
                pushNotifications = preferences.pushNotifications,
                language = preferences.language,
                currency = preferences.currency,
                darkMode = preferences.darkMode,
                autoSync = preferences.autoSync,
                syncWifiOnly = preferences.syncWifiOnly,
                syncIntervalMinutes = preferences.syncIntervalMinutes,
                dataSaverMode = preferences.dataSaverMode,
                offlineProductsCache = preferences.offlineProductsCache,
                maxCacheSizeMb = preferences.maxCacheSizeMb,
                preferredPaymentMethod = preferences.preferredPaymentMethod,
                shippingAddressesJson = json.encodeToString(
                    preferences.shippingAddresses.map { ShippingAddressJson.fromDomain(it) }
                ),
                recentSearchesJson = json.encodeToString(preferences.recentSearches),
                favoriteCategoriesJson = json.encodeToString(preferences.favoriteCategories),
                lastSyncTimestamp = preferences.lastSyncTimestamp,
                lastSyncedAt = System.currentTimeMillis(),
                syncStatus = syncStatus,
                version = version,
                createdAt = preferences.createdAt,
                updatedAt = preferences.updatedAt
            )
        }

        const val SYNC_STATUS_SYNCED = 0
        const val SYNC_STATUS_PENDING_CREATE = 1
        const val SYNC_STATUS_PENDING_UPDATE = 2
        const val SYNC_STATUS_PENDING_DELETE = 3
    }
}

@Serializable
data class ShippingAddressJson(
    val id: String,
    val street: String,
    val city: String,
    val state: String,
    val postalCode: String,
    val country: String,
    val isDefault: Boolean
) {
    fun toDomain(): UserPreferences.ShippingAddress {
        return UserPreferences.ShippingAddress(
            id = id,
            street = street,
            city = city,
            state = state,
            postalCode = postalCode,
            country = country,
            isDefault = isDefault
        )
    }

    companion object {
        fun fromDomain(address: UserPreferences.ShippingAddress): ShippingAddressJson {
            return ShippingAddressJson(
                id = address.id,
                street = address.street,
                city = address.city,
                state = address.state,
                postalCode = address.postalCode,
                country = address.country,
                isDefault = address.isDefault
            )
        }
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/entity/SyncMetadataEntity.kt ===
package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.offlinefirst.domain.model.SyncStatus

@Entity(
    tableName = "sync_metadata",
    indices = [
        Index(value = ["entity_type"]),
        Index(value = ["entity_id"]),
        Index(value = ["status"]),
        Index(value = ["last_attempt_at"])
    ]
)
data class SyncMetadataEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "entity_type")
    val entityType: String,

    @ColumnInfo(name = "entity_id")
    val entityId: String,

    @ColumnInfo(name = "operation")
    val operation: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "local_version")
    val localVersion: Int,

    @ColumnInfo(name = "server_version")
    val serverVersion: Int?,

    @ColumnInfo(name = "local_data")
    val localData: String?,

    @ColumnInfo(name = "server_data")
    val serverData: String?,

    @ColumnInfo(name = "conflict_resolution")
    val conflictResolution: String?,

    @ColumnInfo(name = "error_message")
    val errorMessage: String?,

    @ColumnInfo(name = "retry_count")
    val retryCount: Int,

    @ColumnInfo(name = "max_retries")
    val maxRetries: Int,

    @ColumnInfo(name = "created_at")
    val createdAt: Long,

    @ColumnInfo(name = "last_attempt_at")
    val lastAttemptAt: Long?,

    @ColumnInfo(name = "next_retry_at")
    val nextRetryAt: Long?,

    @ColumnInfo(name = "completed_at")
    val completedAt: Long?
) {
    fun toDomain(): SyncStatus {
        return SyncStatus(
            entityType = entityType,
            entityId = entityId,
            status = try {
                SyncStatus.SyncState.valueOf(status)
            } catch (e: Exception) {
                SyncStatus.SyncState.PENDING
            },
            operation = operation,
            localVersion = localVersion,
            serverVersion = serverVersion,
            lastAttemptAt = lastAttemptAt,
            retryCount = retryCount,
            errorMessage = errorMessage
        )
    }

    fun canRetry(): Boolean {
        return retryCount < maxRetries && 
               (nextRetryAt == null || System.currentTimeMillis() >= nextRetryAt)
    }

    fun shouldRetry(): Boolean {
        return status == SyncStatus.SyncState.FAILED.name && canRetry()
    }

    companion object {
        fun createForNewEntity(
            entityType: String,
            entityId: String,
            operation: String,
            localData: String?
        ): SyncMetadataEntity {
            return SyncMetadataEntity(
                id = "${entityType}_${entityId}_${System.currentTimeMillis()}",
                entityType = entityType,
                entityId = entityId,
                operation = operation,
                status = SyncStatus.SyncState.PENDING.name,
                localVersion = 1,
                serverVersion = null,
                localData = localData,
                serverData = null,
                conflictResolution = null,
                errorMessage = null,
                retryCount = 0,
                maxRetries = 3,
                createdAt = System.currentTimeMillis(),
                lastAttemptAt = null,
                nextRetryAt = null,
                completedAt = null
            )
        }

        fun createForUpdate(
            entityType: String,
            entityId: String,
            localVersion: Int,
            localData: String?
        ): SyncMetadataEntity {
            return SyncMetadataEntity(
                id = "${entityType}_${entityId}_update_${System.currentTimeMillis()}",
                entityType = entityType,
                entityId = entityId,
                operation = "UPDATE",
                status = SyncStatus.SyncState.PENDING.name,
                localVersion = localVersion,
                serverVersion = null,
                localData = localData,
                serverData = null,
                conflictResolution = null,
                errorMessage = null,
                retryCount = 0,
                maxRetries = 3,
                createdAt = System.currentTimeMillis(),
                lastAttemptAt = null,
                nextRetryAt = null,
                completedAt = null
            )
        }

        const val ENTITY_TYPE_PRODUCT = "PRODUCT"
        const val ENTITY_TYPE_PURCHASE = "PURCHASE"
        const val ENTITY_TYPE_USER_PREFERENCES = "USER_PREFERENCES"

        const val OPERATION_CREATE = "CREATE"
        const val OPERATION_UPDATE = "UPDATE"
        const val OPERATION_DELETE = "DELETE"

        const val STATUS_PENDING = "PENDING"
        const val STATUS_IN_PROGRESS = "IN_PROGRESS"
        const val STATUS_COMPLETED = "COMPLETED"
        const val STATUS_FAILED = "FAILED"
        const val STATUS_CONFLICT = "CONFLICT"

        const val CONFLICT_RESOLUTION_LOCAL = "LOCAL_WINS"
        const val CONFLICT_RESOLUTION_SERVER = "SERVER_WINS"
        const val CONFLICT_RESOLUTION_MERGE = "MERGE"
    }
}

@Entity(
    tableName = "sync_queue",
    indices = [
        Index(value = ["entity_type", "entity_id"], unique = true),
        Index(value = ["priority"]),
        Index(value = ["scheduled_at"])
    ]
)
data class SyncQueueEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "entity_type")
    val entityType: String,

    @ColumnInfo(name = "entity_id")
    val entityId: String,

    @ColumnInfo(name = "operation")
    val operation: String,

    @ColumnInfo(name = "payload")
    val payload: String,

    @ColumnInfo(name = "priority")
    val priority: Int,

    @ColumnInfo(name = "scheduled_at")
    val scheduledAt: Long,

    @ColumnInfo(name = "attempted_at")
    val attemptedAt: Long?,

    @ColumnInfo(name = "attempt_count")
    val attemptCount: Int,

    @ColumnInfo(name = "max_attempts")
    val maxAttempts: Int,

    @ColumnInfo(name = "last_error")
    val lastError: String?,

    @ColumnInfo(name = "created_at")
    val createdAt: Long
)


// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/dao/ProductDao.kt ===
package com.example.offlinefirst.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.offlinefirst.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM products ORDER BY createdAt DESC")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE id = :productId")
    fun getProductById(productId: String): Flow<ProductEntity?>

    @Query("SELECT * FROM products WHERE id = :productId")
    suspend fun getProductByIdSync(productId: String): ProductEntity?

    @Query("SELECT * FROM products WHERE category = :category ORDER BY createdAt DESC")
    fun getProductsByCategory(category: String): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE name LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%' ORDER BY createdAt DESC")
    fun searchProducts(query: String): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE isAvailable = 1 AND stock > 0 ORDER BY createdAt DESC")
    fun getAvailableProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE syncStatus = 'PENDING' OR syncStatus = 'FAILED'")
    suspend fun getProductsToSync(): List<ProductEntity>

    @Query("SELECT * FROM products WHERE updatedAt > :lastSyncTimestamp")
    suspend fun getProductsUpdatedSince(lastSyncTimestamp: Long): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)

    @Update
    suspend fun updateProduct(product: ProductEntity)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    @Query("DELETE FROM products WHERE id = :productId")
    suspend fun deleteProductById(productId: String)

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()

    @Query("UPDATE products SET syncStatus = :syncStatus WHERE id = :productId")
    suspend fun updateSyncStatus(productId: String, syncStatus: String)

    @Query("UPDATE products SET stock = :newStock, updatedAt = :updatedAt WHERE id = :productId")
    suspend fun updateStock(productId: String, newStock: Int, updatedAt: Long)

    @Query("UPDATE products SET isAvailable = :isAvailable, updatedAt = :updatedAt WHERE id = :productId")
    suspend fun updateAvailability(productId: String, isAvailable: Boolean, updatedAt: Long)

    @Query("SELECT COUNT(*) FROM products")
    fun getProductCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM products WHERE isAvailable = 1 AND stock > 0")
    fun getAvailableProductCount(): Flow<Int>

    @Query("SELECT * FROM products WHERE price BETWEEN :minPrice AND :maxPrice ORDER BY price ASC")
    fun getProductsByPriceRange(minPrice: Double, maxPrice: Double): Flow<List<ProductEntity>>

    @Query("SELECT DISTINCT category FROM products ORDER BY category ASC")
    fun getAllCategories(): Flow<List<String>>
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/dao/PurchaseHistoryDao.kt ===
package com.example.offlinefirst.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.offlinefirst.data.local.entity.PurchaseHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseHistoryDao {

    @Query("SELECT * FROM purchase_history ORDER BY purchaseDate DESC")
    fun getAllPurchases(): Flow<List<PurchaseHistoryEntity>>

    @Query("SELECT * FROM purchase_history WHERE id = :purchaseId")
    fun getPurchaseById(purchaseId: String): Flow<PurchaseHistoryEntity?>

    @Query("SELECT * FROM purchase_history WHERE id = :purchaseId")
    suspend fun getPurchaseByIdSync(purchaseId: String): PurchaseHistoryEntity?

    @Query("SELECT * FROM purchase_history WHERE status = :status ORDER BY purchaseDate DESC")
    fun getPurchasesByStatus(status: String): Flow<List<PurchaseHistoryEntity>>

    @Query("SELECT * FROM purchase_history WHERE productId = :productId ORDER BY purchaseDate DESC")
    fun getPurchasesByProduct(productId: String): Flow<List<PurchaseHistoryEntity>>

    @Query("SELECT * FROM purchase_history WHERE purchaseDate BETWEEN :startDate AND :endDate ORDER BY purchaseDate DESC")
    fun getPurchasesByDateRange(startDate: Long, endDate: Long): Flow<List<PurchaseHistoryEntity>>

    @Query("SELECT * FROM purchase_history WHERE syncStatus = 'PENDING' OR syncStatus = 'FAILED'")
    suspend fun getPurchasesToSync(): List<PurchaseHistoryEntity>

    @Query("SELECT * FROM purchase_history WHERE updatedAt > :lastSyncTimestamp")
    suspend fun getPurchasesUpdatedSince(lastSyncTimestamp: Long): List<PurchaseHistoryEntity>

    @Query("SELECT * FROM purchase_history WHERE status = 'ACTIVE' AND purchaseDate >= :sinceTimestamp ORDER BY purchaseDate DESC")
    fun getActivePurchasesSince(sinceTimestamp: Long): Flow<List<PurchaseHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPurchase(purchase: PurchaseHistoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPurchases(purchases: List<PurchaseHistoryEntity>)

    @Update
    suspend fun updatePurchase(purchase: PurchaseHistoryEntity)

    @Delete
    suspend fun deletePurchase(purchase: PurchaseHistoryEntity)

    @Query("DELETE FROM purchase_history WHERE id = :purchaseId")
    suspend fun deletePurchaseById(purchaseId: String)

    @Query("DELETE FROM purchase_history")
    suspend fun deleteAllPurchases()

    @Query("UPDATE purchase_history SET syncStatus = :syncStatus WHERE id = :purchaseId")
    suspend fun updateSyncStatus(purchaseId: String, syncStatus: String)

    @Query("UPDATE purchase_history SET status = :status, updatedAt = :updatedAt WHERE id = :purchaseId")
    suspend fun updateStatus(purchaseId: String, status: String, updatedAt: Long)

    @Query("UPDATE purchase_history SET shippingTracking = :tracking, deliveryDate = :deliveryDate, updatedAt = :updatedAt WHERE id = :purchaseId")
    suspend fun updateShippingInfo(purchaseId: String, tracking: String?, deliveryDate: Long?, updatedAt: Long)

    @Query("SELECT COUNT(*) FROM purchase_history")
    fun getPurchaseCount(): Flow<Int>

    @Query("SELECT SUM(totalPrice) FROM purchase_history WHERE status = 'COMPLETED' AND purchaseDate BETWEEN :startDate AND :endDate")
    fun getTotalSpentInRange(startDate: Long, endDate: Long): Flow<Double?>

    @Query("SELECT SUM(quantity) FROM purchase_history WHERE productId = :productId")
    fun getTotalQuantityPurchasedForProduct(productId: String): Flow<Int?>
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/dao/UserPreferencesDao.kt ===
package com.example.offlinefirst.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.offlinefirst.data.local.entity.UserPreferencesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserPreferencesDao {

    @Query("SELECT * FROM user_preferences WHERE id = 1")
    fun getUserPreferences(): Flow<UserPreferencesEntity?>

    @Query("SELECT * FROM user_preferences WHERE id = 1")
    suspend fun getUserPreferencesSync(): UserPreferencesEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreferences(preferences: UserPreferencesEntity)

    @Update
    suspend fun updatePreferences(preferences: UserPreferencesEntity)

    @Query("UPDATE user_preferences SET syncStatus = :syncStatus WHERE id = 1")
    suspend fun updateSyncStatus(syncStatus: String)

    @Query("UPDATE user_preferences SET lastSyncTimestamp = :timestamp, syncStatus = 'SYNCED' WHERE id = 1")
    suspend fun markAsSynced(timestamp: Long)

    @Query("DELETE FROM user_preferences")
    suspend fun deletePreferences()

    @Query("SELECT syncStatus FROM user_preferences WHERE id = 1")
    suspend fun getSyncStatus(): String?

    @Query("SELECT lastSyncTimestamp FROM user_preferences WHERE id = 1")
    suspend fun getLastSyncTimestamp(): Long?

    @Query("UPDATE user_preferences SET themeMode = :themeMode, updatedAt = :updatedAt WHERE id = 1")
    suspend fun updateThemeMode(themeMode: String, updatedAt: Long)

    @Query("UPDATE user_preferences SET notificationsEnabled = :enabled, updatedAt = :updatedAt WHERE id = 1")
    suspend fun updateNotificationsEnabled(enabled: Boolean, updatedAt: Long)

    @Query("UPDATE user_preferences SET autoSyncEnabled = :enabled, updatedAt = :updatedAt WHERE id = 1")
    suspend fun updateAutoSyncEnabled(enabled: Boolean, updatedAt: Long)

    @Query("UPDATE user_preferences SET syncOnWifiOnly = :wifiOnly, updatedAt = :updatedAt WHERE id = 1")
    suspend fun updateSyncOnWifiOnly(wifiOnly: Boolean, updatedAt: Long)

    @Query("UPDATE user_preferences SET defaultPaymentMethod = :paymentMethod, updatedAt = :updatedAt WHERE id = 1")
    suspend fun updateDefaultPaymentMethod(paymentMethod: String, updatedAt: Long)

    @Query("UPDATE user_preferences SET language = :language, updatedAt = :updatedAt WHERE id = 1")
    suspend fun updateLanguage(language: String, updatedAt: Long)
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/database/OfflineFirstDatabase.kt ===
package com.example.offlinefirst.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.offlinefirst.data.local.dao.ProductDao
import com.example.offlinefirst.data.local.dao.PurchaseHistoryDao
import com.example.offlinefirst.data.local.dao.UserPreferencesDao
import com.example.offlinefirst.data.local.entity.ProductEntity
import com.example.offlinefirst.data.local.entity.PurchaseHistoryEntity
import com.example.offlinefirst.data.local.entity.SyncMetadataEntity
import com.example.offlinefirst.data.local.entity.UserPreferencesEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@Database(
    entities = [
        ProductEntity::class,
        PurchaseHistoryEntity::class,
        UserPreferencesEntity::class,
        SyncMetadataEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class OfflineFirstDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun purchaseHistoryDao(): PurchaseHistoryDao
    abstract fun userPreferencesDao(): UserPreferencesDao

    companion object {
        private const val DATABASE_NAME = "offline_first_database"
        private const val DATABASE_VERSION = 1

        @Volatile
        private var INSTANCE: OfflineFirstDatabase? = null

        fun getDatabase(context: Context): OfflineFirstDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OfflineFirstDatabase::class.java,
                    DATABASE_NAME
                )
                    .addCallback(DatabaseCallback())
                    .addMigrations()
                    .setJournalMode(JournalMode.TRUNCATE)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        fun getDatabaseInstance(): OfflineFirstDatabase? = INSTANCE
    }

    private class DatabaseCallback : Callback() {
        private val databaseWriteExecutor = Executors.newFixedThreadPool(4)

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            databaseWriteExecutor.execute {
                INSTANCE?.let { database ->
                    populateInitialData(database)
                }
            }
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            databaseWriteExecutor.execute {
                INSTANCE?.let { database ->
                    performDatabaseMaintenance(database)
                }
            }
        }

        private fun populateInitialData(database: OfflineFirstDatabase) {
            try {
                val defaultPreferences = UserPreferencesEntity(
                    id = 1,
                    themeMode = "SYSTEM",
                    notificationsEnabled = true,
                    autoSyncEnabled = true,
                    syncOnWifiOnly = false,
                    defaultPaymentMethod = "CREDIT_CARD",
                    language = "es",
                    lastSyncTimestamp = 0L,
                    syncStatus = "PENDING",
                    createdAt = System.currentTimeMillis(),
                    updatedAt = System.currentTimeMillis()
                )
                database.userPreferencesDao().insertPreferences(defaultPreferences)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private fun performDatabaseMaintenance(database: OfflineFirstDatabase) {
            try {
                val currentTime = System.currentTimeMillis()
                val thirtyDaysAgo = currentTime - (30L * 24 * 60 * 60 * 1000)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

class Converters {
    // Los converters se delegan a las anotaciones en las entidades
    // Room usa automaticamente los converters definidos a nivel de campo
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/remote/api/OfflineFirstApiService.kt ===
package com.example.offlinefirst.data.remote.api

import com.example.offlinefirst.data.remote.dto.CreateProductRequest
import com.example.offlinefirst.data.remote.dto.CreatePurchaseRequest
import com.example.offlinefirst.data.remote.dto.ProductDto
import com.example.offlinefirst.data.remote.dto.ProductListResponse
import com.example.offlinefirst.data.remote.dto.PurchaseDto
import com.example.offlinefirst.data.remote.dto.PurchaseListResponse
import com.example.offlinefirst.data.remote.dto.PurchaseResponse
import com.example.offlinefirst.data.remote.dto.UpdateProductRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface OfflineFirstApiService {

    // Endpoints de Productos
    @GET("api/v1/products")
    suspend fun getProducts(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20,
        @Query("category") category: String? = null
    ): Response<ProductListResponse>

    @GET("api/v1/products/{id}")
    suspend fun getProductById(@Path("id") productId: String): Response<ProductDto>

    @POST("api/v1/products")
    suspend fun createProduct(@Body request: CreateProductRequest): Response<ProductDto>

    @PUT("api/v1/products/{id}")
    suspend fun updateProduct(
        @Path("id") productId: String,
        @Body request: UpdateProductRequest
    ): Response<ProductDto>

    @DELETE("api/v1/products/{id}")
    suspend fun deleteProduct(@Path("id") productId: String): Response<Unit>

    @GET("api/v1/products/search")
    suspend fun searchProducts(@Query("q") query: String): Response<ProductListResponse>

    // Endpoints de Compras/Historial
    @GET("api/v1/purchases")
    suspend fun getPurchases(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20,
        @Query("status") status: String? = null
    ): Response<PurchaseListResponse>

    @GET("api/v1/purchases/{id}")
    suspend fun getPurchaseById(@Path("id") purchaseId: String): Response<PurchaseDto>

    @POST("api/v1/purchases")
    suspend fun createPurchase(@Body request: CreatePurchaseRequest): Response<PurchaseResponse>

    @PUT("api/v1/purchases/{id}/cancel")
    suspend fun cancelPurchase(@Path("id") purchaseId: String): Response<PurchaseDto>

    @PUT("api/v1/purchases/{id}/status")
    suspend fun updatePurchaseStatus(
        @Path("id") purchaseId: String,
        @Query("status") status: String
    ): Response<PurchaseDto>

    // Endpoints de Sincronización
    @POST("api/v1/sync/products")
    suspend fun syncProducts(@Body products: List<ProductDto>): Response<ProductListResponse>

    @POST("api/v1/sync/purchases")
    suspend fun syncPurchases(@Body purchases: List<PurchaseDto>): Response<PurchaseListResponse>

    @GET("api/v1/sync/last-modified")
    suspend fun getLastModifiedTimestamp(): Response<SyncTimestampResponse>

    @POST("api/v1/sync/resolve-conflict")
    suspend fun resolveConflict(@Body conflictData: ConflictResolutionRequest): Response<ConflictResolutionResponse>

    // Endpoints de Preferencias de Usuario
    @GET("api/v1/user/preferences")
    suspend fun getUserPreferences(): Response<UserPreferencesResponse>

    @PUT("api/v1/user/preferences")
    suspend fun updateUserPreferences(@Body preferences: UserPreferencesDto): Response<UserPreferencesResponse>
}

data class SyncTimestampResponse(
    val timestamp: Long,
    val serverVersion: String
)

data class ConflictResolutionRequest(
    val entityType: String,
    val entityId: String,
    val localVersion: String,
    val serverVersion: String,
    val resolutionStrategy: String
)

data class ConflictResolutionResponse(
    val success: Boolean,
    val resolvedEntity: String,
    val finalVersion: String
)

data class UserPreferencesResponse(
    val themeMode: String,
    val notificationsEnabled: Boolean,
    val autoSyncEnabled: Boolean,
    val syncOnWifiOnly: Boolean,
    val defaultPaymentMethod: String,
    val language: String,
    val lastUpdated: Long
)

data class UserPreferencesDto(
    val themeMode: String,
    val notificationsEnabled: Boolean,
    val autoSyncEnabled: Boolean,
    val syncOnWifiOnly: Boolean,
    val defaultPaymentMethod: String,
    val language: String
)

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt ===
package com.example.offlinefirst.data.repository

import com.example.offlinefirst.data.local.dao.ProductDao
import com.example.offlinefirst.data.local.entity.ProductEntity
import com.example.offlinefirst.data.remote.api.OfflineFirstApiService
import com.example.offlinefirst.data.remote.dto.ProductDto
import com.example.offlinefirst.data.sync.ConflictResolver
import com.example.offlinefirst.domain.model.Product
import com.example.offlinefirst.domain.model.ProductCategory
import com.example.offlinefirst.domain.repository.ProductRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val apiService: OfflineFirstApiService,
    private val conflictResolver: ConflictResolver
) : ProductRepository {

    override fun getProducts(forceRefresh: Boolean): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())
        
        val localProducts = productDao.getAllProducts().first()
        if (localProducts.isNotEmpty() && !forceRefresh) {
            emit(Resource.Success(localProducts.map { it.toDomain() }))
            return@flow
        }
        
        try {
            val response = apiService.getProducts()
            if (response.isSuccessful && response.body() != null) {
                val remoteProducts = response.body()!!.products
                val localEntities = remoteProducts.map { it.toEntity() }
                
                productDao.insertProducts(localEntities)
                emit(Resource.Success(localEntities.map { it.toDomain() }))
            } else {
                if (localProducts.isNotEmpty()) {
                    emit(Resource.Success(localProducts.map { it.toDomain() }))
                } else {
                    emit(Resource.Error("Error al cargar productos: ${response.message()}"))
                }
            }
        } catch (e: Exception) {
            if (localProducts.isNotEmpty()) {
                emit(Resource.Success(localProducts.map { it.toDomain() }))
            } else {
                emit(Resource.Error("Error de conexión: ${e.localizedMessage}"))
            }
        }
    }

    override fun getProductById(id: String): Flow<Resource<Product>> = flow {
        emit(Resource.Loading())
        
        val localProduct = productDao.getProductById(id).first()
        if (localProduct != null) {
            emit(Resource.Success(localProduct.toDomain()))
            return@flow
        }
        
        try {
            val response = apiService.getProductById(id)
            if (response.isSuccessful && response.body() != null) {
                val product = response.body()!!
                productDao.insertProduct(product.toEntity())
                emit(Resource.Success(product.toDomain()))
            } else {
                emit(Resource.Error("Producto no encontrado"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error de conexión: ${e.localizedMessage}"))
        }
    }

    override fun getProductsByCategory(category: ProductCategory): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())
        
        val localProducts = productDao.getProductsByCategory(category.name).first()
        if (localProducts.isNotEmpty()) {
            emit(Resource.Success(localProducts.map { it.toDomain() }))
        } else {
            emit(Resource.Error("No hay productos en esta categoría"))
        }
    }

    override suspend fun createProduct(product: Product): Resource<Product> {
        return try {
            val request = com.example.offlinefirst.data.remote.dto.CreateProductRequest(
                name = product.name,
                description = product.description,
                price = product.price.toDouble(),
                stock = product.stock,
                category = product.category.name,
                imageUrl = product.imageUrl
            )
            val response = apiService.createProduct(request)
            if (response.isSuccessful && response.body() != null) {
                val createdProduct = response.body()!!
                productDao.insertProduct(createdProduct.toEntity())
                Resource.Success(createdProduct.toDomain())
            } else {
                Resource.Error("Error al crear producto: ${response.message()}")
            }
        } catch (e: Exception) {
            val pendingEntity = product.toPendingEntity()
            productDao.insertPendingProduct(pendingEntity)
            Resource.Success(product)
        }
    }

    override suspend fun updateProduct(product: Product): Resource<Product> {
        return try {
            val request = com.example.offlinefirst.data.remote.dto.UpdateProductRequest(
                name = product.name,
                description = product.description,
                price = product.price.toDouble(),
                stock = product.stock,
                category = product.category.name,
                imageUrl = product.imageUrl,
                available = product.isAvailable
            )
            val response = apiService.updateProduct(product.id, request)
            if (response.isSuccessful && response.body() != null) {
                val updatedProduct = response.body()!!
                productDao.insertProduct(updatedProduct.toEntity())
                Resource.Success(updatedProduct.toDomain())
            } else {
                Resource.Error("Error al actualizar producto: ${response.message()}")
            }
        } catch (e: Exception) {
            val pendingEntity = product.toPendingEntity()
            productDao.insertPendingProduct(pendingEntity)
            Resource.Success(product)
        }
    }

    override suspend fun deleteProduct(id: String): Resource<Unit> {
        return try {
            val response = apiService.deleteProduct(id)
            if (response.isSuccessful) {
                productDao.deleteProduct(id)
                Resource.Success(Unit)
            } else {
                productDao.markProductForDeletion(id)
                Resource.Success(Unit)
            }
        } catch (e: Exception) {
            productDao.markProductForDeletion(id)
            Resource.Success(Unit)
        }
    }

    override fun searchProducts(query: String): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())
        
        val results = productDao.searchProducts("%$query%").first()
        emit(Resource.Success(results.map { it.toDomain() }))
    }

    override fun getPendingProducts(): Flow<List<Product>> {
        return productDao.getPendingProducts().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun syncPendingProducts(): Resource<Int> {
        val pendingProducts = productDao.getPendingProducts().first()
        var syncedCount = 0
        
        for (product in pendingProducts) {
            try {
                val result = conflictResolver.resolveProductConflict(product.toDomain())
                val request = com.example.offlinefirst.data.remote.dto.UpdateProductRequest(
                    name = result.name,
                    description = result.description,
                    price = result.price.toDouble(),
                    stock = result.stock,
                    category = result.category.name,
                    imageUrl = result.imageUrl,
                    available = result.isAvailable
                )
                val response = apiService.updateProduct(result.id, request)
                if (response.isSuccessful) {
                    productDao.deletePendingProduct(product.id)
                    syncedCount++
                }
            } catch (e: Exception) {
                // Continuar con el siguiente producto
            }
        }
        
        return Resource.Success(syncedCount)
    }

    private fun ProductDto.toEntity(): ProductEntity {
        return ProductEntity(
            id = id,
            name = name,
            description = description,
            price = BigDecimal(price.toString()),
            stock = stock,
            category = category,
            imageUrl = imageUrl ?: "",
            isAvailable = available,
            rating = rating,
            lastUpdated = System.currentTimeMillis(),
            syncStatus = com.example.offlinefirst.domain.model.SyncStatus.SYNCED.name
        )
    }

    private fun ProductEntity.toDomain(): Product {
        return Product(
            id = id,
            name = name,
            description = description,
            price = price,
            stock = stock,
            category = try { ProductCategory.valueOf(category) } catch (e: Exception) { ProductCategory.OTHER },
            imageUrl = imageUrl,
            isAvailable = isAvailable,
            rating = rating,
            lastUpdated = lastUpdated
        )
    }

    private fun Product.toPendingEntity(): ProductEntity {
        return ProductEntity(
            id = id,
            name = name,
            description = description,
            price = price,
            stock = stock,
            category = category.name,
            imageUrl = imageUrl,
            isAvailable = isAvailable,
            rating = rating,
            lastUpdated = System.currentTimeMillis(),
            syncStatus = com.example.offlinefirst.domain.model.SyncStatus.PENDING.name
        )
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/repository/PurchaseRepositoryImpl.kt ===
package com.example.offlinefirst.data.repository

import com.example.offlinefirst.data.local.dao.PurchaseHistoryDao
import com.example.offlinefirst.data.local.entity.PurchaseHistoryEntity
import com.example.offlinefirst.data.remote.api.OfflineFirstApiService
import com.example.offlinefirst.data.remote.dto.CreatePurchaseRequest
import com.example.offlinefirst.data.remote.dto.PurchaseDto
import com.example.offlinefirst.domain.model.PurchaseHistory
import com.example.offlinefirst.domain.model.PurchaseStatus
import com.example.offlinefirst.domain.model.PaymentMethod
import com.example.offlinefirst.domain.repository.PurchaseRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PurchaseRepositoryImpl @Inject constructor(
    private val purchaseDao: PurchaseHistoryDao,
    private val apiService: OfflineFirstApiService
) : PurchaseRepository {

    override fun getPurchaseHistory(): Flow<Resource<List<PurchaseHistory>>> = flow {
        emit(Resource.Loading())
        
        try {
            val localPurchases = purchaseDao.getAllPurchases().first()
            
            if (localPurchases.isEmpty()) {
                val response = apiService.getPurchases()
                if (response.isSuccessful && response.body() != null) {
                    val remotePurchases = response.body()!!.purchases
                    val entities = remotePurchases.map { it.toEntity() }
                    purchaseDao.insertPurchases(entities)
                    emit(Resource.Success(entities.map { it.toDomain() }))
                } else {
                    emit(Resource.Error("Error al cargar historial: ${response.message()}"))
                }
            } else {
                try {
                    val response = apiService.getPurchases()
                    if (response.isSuccessful && response.body() != null) {
                        val remotePurchases = response.body()!!.purchases
                        val mergedPurchases = mergePurchases(localPurchases, remotePurchases)
                        purchaseDao.insertPurchases(mergedPurchases)
                        emit(Resource.Success(mergedPurchases.map { it.toDomain() }))
                    } else {
                        emit(Resource.Success(localPurchases.map { it.toDomain() }))
                    }
                } catch (e: Exception) {
                    emit(Resource.Success(localPurchases.map { it.toDomain() }))
                }
            }
        } catch (e: Exception) {
            val localPurchases = purchaseDao.getAllPurchases().first()
            if (localPurchases.isNotEmpty()) {
                emit(Resource.Success(localPurchases.map { it.toDomain() }))
            } else {
                emit(Resource.Error("Error de conexión: ${e.localizedMessage}"))
            }
        }
    }

    override fun getPurchaseById(id: String): Flow<Resource<PurchaseHistory>> = flow {
        emit(Resource.Loading())
        
        val localPurchase = purchaseDao.getPurchaseById(id).first()
        if (localPurchase != null) {
            emit(Resource.Success(localPurchase.toDomain()))
            return@flow
        }
        
        try {
            val response = apiService.getPurchaseById(id)
            if (response.isSuccessful && response.body() != null) {
                val purchase = response.body()!!
                purchaseDao.insertPurchase(purchase.toEntity())
                emit(Resource.Success(purchase.toDomain()))
            } else {
                emit(Resource.Error("Compra no encontrada"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error de conexión: ${e.localizedMessage}"))
        }
    }

    override suspend fun createPurchase(purchase: PurchaseHistory): Resource<PurchaseHistory> {
        return try {
            val request = CreatePurchaseRequest(
                productId = purchase.productId,
                quantity = purchase.quantity,
                unitPrice = purchase.unitPrice.toDouble(),
                totalPrice = purchase.totalPrice.toDouble(),
                paymentMethod = purchase.paymentMethod.name,
                shippingAddress = purchase.shippingAddress,
                shippingCity = purchase.shippingCity,
                shippingPostalCode = purchase.shippingPostalCode
            )
            
            val response = apiService.createPurchase(request)
            if (response.isSuccessful && response.body() != null) {
                val createdPurchase = response.body()!!
                purchaseDao.insertPurchase(createdPurchase.toEntity())
                Resource.Success(createdPurchase.toDomain())
            } else {
                val pendingEntity = purchase.toPendingEntity()
                purchaseDao.insertPurchase(pendingEntity)
                Resource.Success(purchase)
            }
        } catch (e: Exception) {
            val pendingEntity = purchase.toPendingEntity()
            purchaseDao.insertPurchase(pendingEntity)
            Resource.Success(purchase)
        }
    }

    override suspend fun cancelPurchase(id: String): Resource<Unit> {
        return try {
            val response = apiService.cancelPurchase(id)
            if (response.isSuccessful) {
                purchaseDao.updatePurchaseStatus(id, PurchaseStatus.CANCELLED.name)
                Resource.Success(Unit)
            } else {
                purchaseDao.markPurchaseForDeletion(id)
                Resource.Success(Unit)
            }
        } catch (e: Exception) {
            purchaseDao.markPurchaseForDeletion(id)
            Resource.Success(Unit)
        }
    }

    override fun getActivePurchases(): Flow<Resource<List<PurchaseHistory>>> = flow {
        emit(Resource.Loading())
        
        val activePurchases = purchaseDao.getPurchasesByStatus(
            listOf(PurchaseStatus.PENDING.name, PurchaseStatus.CONFIRMED.name, PurchaseStatus.SHIPPED.name)
        ).first()
        
        emit(Resource.Success(activePurchases.map { it.toDomain() }))
    }

    override fun getPendingPurchases(): Flow<List<PurchaseHistory>> {
        return purchaseDao.getPendingPurchases().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun syncPendingPurchases(): Resource<Int> {
        val pendingPurchases = purchaseDao.getPendingPurchases().first()
        var syncedCount = 0
        
        for (purchase in pendingPurchases) {
            try {
                val request = CreatePurchaseRequest(
                    productId = purchase.productId,
                    quantity = purchase.quantity,
                    unitPrice = purchase.unitPrice.toDouble(),
                    totalPrice = purchase.totalPrice.toDouble(),
                    paymentMethod = purchase.paymentMethod.name,
                    shippingAddress = purchase.shippingAddress,
                    shippingCity = purchase.shippingCity,
                    shippingPostalCode = purchase.shippingPostalCode
                )
                val response = apiService.createPurchase(request)
                if (response.isSuccessful) {
                    purchaseDao.deletePendingPurchase(purchase.id)
                    syncedCount++
                }
            } catch (e: Exception) {
                // Continuar con el siguiente
            }
        }
        
        return Resource.Success(syncedCount)
    }

    private fun mergePurchases(
        local: List<PurchaseHistoryEntity>,
        remote: List<PurchaseHistoryEntity>
    ): List<PurchaseHistoryEntity> {
        val merged = mutableMapOf<String, PurchaseHistoryEntity>()
        
        local.forEach { merged[it.id] = it }
        remote.forEach { remotePurchase ->
            val localPurchase = merged[remotePurchase.id]
            if (localPurchase == null) {
                merged[remotePurchase.id] = remotePurchase
            } else if (remotePurchase.lastUpdated > localPurchase.lastUpdated) {
                merged[remotePurchase.id] = remotePurchase
            }
        }
        
        return merged.values.toList().sortedByDescending { it.purchaseDate }
    }

    private fun PurchaseDto.toEntity(): PurchaseHistoryEntity {
        return PurchaseHistoryEntity(
            id = id,
            productId = productId,
            productName = productName,
            quantity = quantity,
            unitPrice = BigDecimal(unitPrice.toString()),
            totalPrice = BigDecimal(totalPrice.toString()),
            paymentMethod = paymentMethod,
            status = parseStatus(status).name,
            purchaseDate = purchaseDate,
            shippingAddress = shippingAddress,
            shippingCity = shippingCity,
            shippingPostalCode = shippingPostalCode,
            trackingNumber = trackingNumber,
            deliveryDate = deliveryDate,
            lastUpdated = System.currentTimeMillis(),
            syncStatus = com.example.offlinefirst.domain.model.SyncStatus.SYNCED.name
        )
    }

    private fun PurchaseHistoryEntity.toDomain(): PurchaseHistory {
        return PurchaseHistory(
            id = id,
            productId = productId,
            productName = productName,
            quantity = quantity,
            unitPrice = unitPrice,
            totalPrice = totalPrice,
            paymentMethod = try { PaymentMethod.valueOf(paymentMethod) } catch (e: Exception) { PaymentMethod.CREDIT_CARD },
            status = try { PurchaseStatus.valueOf(status) } catch (e: Exception) { PurchaseStatus.PENDING },
            purchaseDate = purchaseDate,
            shippingAddress = shippingAddress,
            shippingCity = shippingCity,
            shippingPostalCode = shippingPostalCode,
            trackingNumber = trackingNumber,
            deliveryDate = deliveryDate
        )
    }

    private fun PurchaseHistory.toPendingEntity(): PurchaseHistoryEntity {
        return PurchaseHistoryEntity(
            id = id,
            productId = productId,
            productName = productName,
            quantity = quantity,
            unitPrice = unitPrice,
            totalPrice = totalPrice,
            paymentMethod = paymentMethod.name,
            status = status.name,
            purchaseDate = purchaseDate,
            shippingAddress = shippingAddress,
            shippingCity = shippingCity,
            shippingPostalCode = shippingPostalCode,
            trackingNumber = trackingNumber,
            deliveryDate = deliveryDate,
            lastUpdated = System.currentTimeMillis(),
            syncStatus = com.example.offlinefirst.domain.model.SyncStatus.PENDING.name
        )
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/repository/UserPreferencesRepositoryImpl.kt ===
package com.example.offlinefirst.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.offlinefirst.data.local.dao.UserPreferencesDao
import com.example.offlinefirst.data.local.entity.UserPreferencesEntity
import com.example.offlinefirst.domain.model.UserPreferences
import com.example.offlinefirst.domain.repository.UserPreferencesRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val userPreferencesDao: UserPreferencesDao
) : UserPreferencesRepository {

    companion object {
        private val KEY_USER_ID = stringPreferencesKey("user_id")
        private val KEY_USER_NAME = stringPreferencesKey("user_name")
        private val KEY_USER_EMAIL = stringPreferencesKey("user_email")
        private val KEY_NOTIFICATIONS_ENABLED = booleanPreferencesKey("notifications_enabled")
        private val KEY_DARK_MODE = booleanPreferencesKey("dark_mode")
        private val KEY_AUTO_SYNC = booleanPreferencesKey("auto_sync")
        private val KEY_SYNC_ON_WIFI_ONLY = booleanPreferencesKey("sync_on_wifi_only")
        private val KEY_LAST_SYNC_TIME = stringPreferencesKey("last_sync_time")
        private val KEY_SELECTED_CURRENCY = stringPreferencesKey("selected_currency")
        private val KEY_SELECTED_LANGUAGE = stringPreferencesKey("selected_language")
    }

    override fun getUserPreferences(): Flow<Resource<UserPreferences>> = flow {
        emit(Resource.Loading())
        
        try {
            val preferences = dataStore.data.first()
            val userPreferences = UserPreferences(
                userId = preferences[KEY_USER_ID] ?: "",
                userName = preferences[KEY_USER_NAME] ?: "",
                userEmail = preferences[KEY_USER_EMAIL] ?: "",
                notificationsEnabled = preferences[KEY_NOTIFICATIONS_ENABLED] ?: true,
                darkModeEnabled = preferences[KEY_DARK_MODE] ?: false,
                autoSyncEnabled = preferences[KEY_AUTO_SYNC] ?: true,
                syncOnWifiOnly = preferences[KEY_SYNC_ON_WIFI_ONLY] ?: false,
                lastSyncTime = preferences[KEY_LAST_SYNC_TIME]?.toLongOrNull() ?: 0L,
                selectedCurrency = preferences[KEY_SELECTED_CURRENCY] ?: "USD",
                selectedLanguage = preferences[KEY_SELECTED_LANGUAGE] ?: "es"
            )
            
            emit(Resource.Success(userPreferences))
        } catch (e: Exception) {
            emit(Resource.Error("Error al cargar preferencias: ${e.localizedMessage}"))
        }
    }

    override suspend fun saveUserPreferences(preferences: UserPreferences): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                preferences.userId.let { prefs[KEY_USER_ID] = it }
                preferences.userName.let { prefs[KEY_USER_NAME] = it }
                preferences.userEmail.let { prefs[KEY_USER_EMAIL] = it }
                preferences.notificationsEnabled.let { prefs[KEY_NOTIFICATIONS_ENABLED] = it }
                preferences.darkModeEnabled.let { prefs[KEY_DARK_MODE] = it }
                preferences.autoSyncEnabled.let { prefs[KEY_AUTO_SYNC] = it }
                preferences.syncOnWifiOnly.let { prefs[KEY_SYNC_ON_WIFI_ONLY] = it }
                preferences.lastSyncTime.toString().let { prefs[KEY_LAST_SYNC_TIME] = it }
                preferences.selectedCurrency.let { prefs[KEY_SELECTED_CURRENCY] = it }
                preferences.selectedLanguage.let { prefs[KEY_SELECTED_LANGUAGE] = it }
            }
            
            userPreferencesDao.insertOrUpdatePreferences(preferences.toEntity())
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al guardar preferencias: ${e.localizedMessage}")
        }
    }

    override suspend fun updateDarkMode(enabled: Boolean): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_DARK_MODE] = enabled
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar modo oscuro: ${e.localizedMessage}")
        }
    }

    override suspend fun updateNotifications(enabled: Boolean): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_NOTIFICATIONS_ENABLED] = enabled
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar notificaciones: ${e.localizedMessage}")
        }
    }

    override suspend fun updateAutoSync(enabled: Boolean): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_AUTO_SYNC] = enabled
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar sincronización automática: ${e.localizedMessage}")
        }
    }

    override suspend fun updateSyncOnWifiOnly(enabled: Boolean): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_SYNC_ON_WIFI_ONLY] = enabled
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar sincronización WiFi: ${e.localizedMessage}")
        }
    }

    override suspend fun updateLastSyncTime(timestamp: Long): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_LAST_SYNC_TIME] = timestamp.toString()
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar tiempo de sincronización: ${e.localizedMessage}")
        }
    }

    override suspend fun updateCurrency(currency: String): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_SELECTED_CURRENCY] = currency
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar moneda: ${e.localizedMessage}")
        }
    }

    override suspend fun updateLanguage(language: String): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_SELECTED_LANGUAGE] = language
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar idioma: ${e.localizedMessage}")
        }
    }

    override fun observeDarkMode(): Flow<Boolean> {
        return dataStore.data.map { prefs ->
            prefs[KEY_DARK_MODE] ?: false
        }
    }

    override fun observeAutoSync(): Flow<Boolean> {
        return dataStore.data.map { prefs ->
            prefs[KEY_AUTO_SYNC] ?: true
        }
    }

    override suspend fun getCachedPreferences(): UserPreferences? {
        return try {
            val cached = userPreferencesDao.getPreferences().first()
            cached?.toDomain()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun clearPreferences(): Resource<Unit> {
        return try {
            dataStore.edit { it.clear() }
            userPreferencesDao.clearAllPreferences()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al limpiar preferencias: ${e.localizedMessage}")
        }
    }

    private fun UserPreferences.toEntity(): UserPreferencesEntity {
        return UserPreferencesEntity(
            userId = userId,
            userName = userName,
            userEmail = userEmail,
            notificationsEnabled = notificationsEnabled,
            darkModeEnabled = darkModeEnabled,
            autoSyncEnabled = autoSyncEnabled,
            syncOnWifiOnly = syncOnWifiOnly,
            lastSyncTime = lastSyncTime,
            selectedCurrency = selectedCurrency,
            selectedLanguage = selectedLanguage
        )
    }

    private fun UserPreferencesEntity.toDomain(): UserPreferences {
        return UserPreferences(
            userId = userId,
            userName = userName,
            userEmail = userEmail,
            notificationsEnabled = notificationsEnabled,
            darkModeEnabled = darkModeEnabled,
            autoSyncEnabled = autoSyncEnabled,
            syncOnWifiOnly = syncOnWifiOnly,
            lastSyncTime = lastSyncTime,
            selectedCurrency = selectedCurrency,
            selectedLanguage = selectedLanguage
        )
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/sync/NetworkConnectivityManager.kt ===
package com.example.offlinefirst.data.sync

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import com.example.offlinefirst.util.NetworkMonitor
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConnectivityManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val networkMonitor: NetworkMonitor
) {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    
    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()
    
    private val _connectionType = MutableStateFlow(ConnectionType.NONE)
    val connectionType: StateFlow<ConnectionType> = _connectionType.asStateFlow()
    
    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing.asStateFlow()

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            _isConnected.value = true
            updateConnectionType()
        }

        override fun onLost(network: Network) {
            _isConnected.value = false
            _connectionType.value = ConnectionType.NONE
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            updateConnectionType()
        }
    }

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        .build()

    fun startMonitoring() {
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
        updateCurrentState()
    }

    fun stopMonitoring() {
        try {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        } catch (e: Exception) {
            // El callback ya fue desregistrado
        }
    }

    private fun updateCurrentState() {
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        
        _isConnected.value = capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        updateConnectionType()
    }

    private fun updateConnectionType() {
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        
        _connectionType.value = when {
            capabilities == null -> ConnectionType.NONE
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> ConnectionType.WIFI
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> ConnectionType.CELLULAR
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> ConnectionType.ETHERNET
            else -> ConnectionType.OTHER
        }
    }

    fun observeConnectivity(): Flow<Boolean> = callbackFlow {
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(true)
            }

            override fun onLost(network: Network) {
                trySend(false)
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(request, callback)
        
        trySend(isCurrentlyConnected())
        
        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }

    fun observeConnectionType(): Flow<ConnectionType> = callbackFlow {
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                val type = getConnectionTypeFromCapabilities(networkCapabilities)
                trySend(type)
            }

            override fun onLost(network: Network) {
                trySend(ConnectionType.NONE)
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(request, callback)
        
        trySend(getCurrentConnectionType())
        
        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }

    fun isCurrentlyConnected(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    fun isCurrentlyConnectedViaWifi(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true
    }

    fun isCurrentlyConnectedViaCellular(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true
    }

    private fun getCurrentConnectionType(): ConnectionType {
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return getConnectionTypeFromCapabilities(capabilities)
    }

    private fun getConnectionTypeFromCapabilities(capabilities: NetworkCapabilities?): ConnectionType {
        return when {
            capabilities == null -> ConnectionType.NONE
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> ConnectionType.WIFI
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> ConnectionType.CELLULAR
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> ConnectionType.ETHERNET
            else -> ConnectionType.OTHER
        }
    }

    fun setSyncing(syncing: Boolean) {
        _isSyncing.value = syncing
    }

    fun canSync(wifiOnly: Boolean): Boolean {
        if (!isCurrentlyConnected()) return false
        if (wifiOnly && !isCurrentlyConnectedViaWifi()) return false
        return true
    }

    enum class ConnectionType {
        NONE,
        WIFI,
        CELLULAR,
        ETHERNET,
        OTHER
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt ===
package com.example.offlinefirst.data.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.offlinefirst.data.repository.ProductRepositoryImpl
import com.example.offlinefirst.data.repository.PurchaseRepositoryImpl
import com.example.offlinefirst.data.repository.UserPreferencesRepositoryImpl
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val productRepository: ProductRepositoryImpl,
    private val purchaseRepository: PurchaseRepositoryImpl,
    private val userPreferencesRepository: UserPreferencesRepositoryImpl,
    private val networkConnectivityManager: NetworkConnectivityManager,
    private val syncManager: SyncManager
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            if (!networkConnectivityManager.isCurrentlyConnected()) {
                return@withContext Result.retry()
            }

            networkConnectivityManager.setSyncing(true)
            
            val wifiOnly = try {
                val prefs = userPreferencesRepository.getUserPreferences().first()
                prefs.data?.syncOnWifiOnly == true
            } catch (e: Exception) {
                false
            }

            if (wifiOnly && !networkConnectivityManager.isCurrentlyConnectedViaWifi()) {
                return@withContext Result.retry()
            }

            var syncResult = Result.success()
            
            coroutineScope {
                val syncJobs = listOf(
                    async { syncProducts() },
                    async { syncPurchases() }
                )
                
                val results = syncJobs.awaitAll()
                if (results.any { it == Result.retry() }) {
                    syncResult = Result.retry()
                }
            }
            
            userPreferencesRepository.updateLastSyncTime(System.currentTimeMillis())
            
            networkConnectivityManager.setSyncing(false)
            
            syncResult
        } catch (e: Exception) {
            networkConnectivityManager.setSyncing(false)
            
            if (runAttemptCount < MAX_RETRY_COUNT) {
                Result.retry()
            } else {
                Result.failure()
            }
        }
    }

    private suspend fun syncProducts(): Result {
        return try {
            val pendingProducts = productRepository.getPendingProducts().first()
            if (pendingProducts.isNotEmpty()) {
                val syncResult = productRepository.syncPendingProducts()
                if (syncResult is com.example.offlinefirst.util.Resource.Error) {
                    return Result.retry()
                }
            }
            
            val refreshResult = productRepository.getProducts(forceRefresh = true).first()
            when (refreshResult) {
                is com.example.offlinefirst.util.Resource.Success -> Result.success()
                is com.example.offlinefirst.util.Resource.Error -> Result.retry()
                is com.example.offlinefirst.util.Resource.Loading -> Result.success()
            }
        } catch (e: Exception) {
            Result.retry()
        }
    }

    private suspend fun syncPurchases(): Result {
        return try {
            val pendingPurchases = purchaseRepository.getPendingPurchases().first()
            if (pendingPurchases.isNotEmpty()) {
                val syncResult = purchaseRepository.syncPendingPurchases()
                if (syncResult is com.example.offlinefirst.util.Resource.Error) {
                    return Result.retry()
                }
            }
            
            val refreshResult = purchaseRepository.getPurchaseHistory().first()
            when (refreshResult) {
                is com.example.offlinefirst.util.Resource.Success -> Result.success()
                is com.example.offlinefirst.util.Resource.Error -> Result.retry()
                is com.example.offlinefirst.util.Resource.Loading -> Result.success()
            }
        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        const val WORK_NAME = "sync_worker"
        private const val MAX_RETRY_COUNT = 3
        private const val SYNC_INTERVAL_MINUTES = 15L

        fun schedule(context: Context, wifiOnly: Boolean = false) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(
                    if (wifiOnly) NetworkType.UNMETERED else NetworkType.CONNECTED
                )
                .setRequiresBatteryNotLow(true)
                .build()

            val syncRequest = PeriodicWorkRequestBuilder<SyncWorker>(
                SYNC_INTERVAL_MINUTES, TimeUnit.MINUTES
            )
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.UPDATE,
                syncRequest
            )
        }

        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
        }

        fun runOnce(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val syncRequest = androidx.work.OneTimeWorkRequestBuilder<SyncWorker>()
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context).enqueue(syncRequest)
        }
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/di/NetworkModule.kt ===
package com.example.offlinefirst.di

import android.content.Context
import com.example.offlinefirst.BuildConfig
import com.example.offlinefirst.data.remote.api.OfflineFirstApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.offlinefirst.example.com/"
    private const val CONNECT_TIMEOUT = 30L
    private const val READ_TIMEOUT = 30L
    private const val WRITE_TIMEOUT = 30L

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
        prettyPrint = BuildConfig.DEBUG
        coerceInputValues = true
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @ApplicationContext context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("X-App-Version", BuildConfig.VERSION_NAME)
                    .header("X-Platform", "Android")
                    .build()
                chain.proceed(newRequest)
            }
            .addNetworkInterceptor { chain ->
                val response = chain.proceed(chain.request())
                response
            }
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideOfflineFirstApiService(retrofit: Retrofit): OfflineFirstApiService =
        retrofit.create(OfflineFirstApiService::class.java)
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/di/RepositoryModule.kt ===
package com.example.offlinefirst.di

import com.example.offlinefirst.data.local.dao.ProductDao
import com.example.offlinefirst.data.local.dao.PurchaseHistoryDao
import com.example.offlinefirst.data.local.dao.UserPreferencesDao
import com.example.offlinefirst.data.remote.api.OfflineFirstApiService
import com.example.offlinefirst.data.repository.ProductRepositoryImpl
import com.example.offlinefirst.data.repository.PurchaseRepositoryImpl
import com.example.offlinefirst.data.repository.UserPreferencesRepositoryImpl
import com.example.offlinefirst.domain.repository.ProductRepository
import com.example.offlinefirst.domain.repository.PurchaseRepository
import com.example.offlinefirst.domain.repository.UserPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindPurchaseRepository(
        purchaseRepositoryImpl: PurchaseRepositoryImpl
    ): PurchaseRepository

    @Binds
    @Singleton
    abstract fun bindUserPreferencesRepository(
        userPreferencesRepositoryImpl: UserPreferencesRepositoryImpl
    ): UserPreferencesRepository
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/util/Resource.kt ===
package com.example.offlinefirst.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(
        val message: String,
        val throwable: Throwable? = null,
        val data: Any? = null
    ) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()

    val isSuccess: Boolean get() = this is Success
    val isError: Boolean get() = this is Error
    val isLoading: Boolean get() = this is Loading

    fun getOrNull(): T? = (this as? Success)?.data
    fun getOrDefault(default: @UnsafeVariance T): T = getOrNull() ?: default

    fun <R> map(transform: (T) -> R): Resource<R> = when (this) {
        is Success -> Success(transform(data))
        is Error -> Error(message, throwable, data)
        is Loading -> Loading
    }

    inline fun onSuccess(action: (T) -> Unit): Resource<T> {
        if (this is Success) action(data)
        return this
    }

    inline fun onError(action: (String, Throwable?) -> Unit): Resource<T> {
        if (this is Error) action(message, throwable)
        return this
    }

    inline fun onLoading(action: () -> Unit): Resource<T> {
        if (this is Loading) action()
        return this
    }

    companion object {
        fun <T> success(data: T): Resource<T> = Success(data)
        fun error(message: String, throwable: Throwable? = null): Resource<Nothing> =
            Error(message, throwable)
        fun loading(): Resource<Nothing> = Loading
    }
}

fun <T> Flow<T>.asResource(): Flow<Resource<T>> =
    this
        .map<T, Resource<T>> { Resource.success(it) }
        .onStart { emit(Resource.loading()) }
        .catch { emit(Resource.error(it.message ?: "Unknown error", it)) }

inline fun <T, R> Resource<T>.fold(
    onSuccess: (T) -> R,
    onError: (String, Throwable?) -> R,
    onLoading: () -> R
): R = when (this) {
    is Resource.Success -> onSuccess(data)
    is Resource.Error -> onError(message, throwable)
    is Resource.Loading -> onLoading()
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/util/NetworkMonitor.kt ===
package com.example.offlinefirst.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject
import javax.inject.Singleton

sealed class NetworkStatus {
    data object Available : NetworkStatus()
    data object Unavailable : NetworkStatus()
    data object Losing : NetworkStatus()
}

@Singleton
class NetworkMonitor @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkStatus: Flow<NetworkStatus> = callbackFlow {
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(NetworkStatus.Available)
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                trySend(NetworkStatus.Losing)
            }

            override fun onLost(network: Network) {
                trySend(NetworkStatus.Unavailable)
            }

            override fun onUnavailable() {
                trySend(NetworkStatus.Unavailable)
            }
        }

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, callback)

        val currentStatus = if (isCurrentlyConnected()) {
            NetworkStatus.Available
        } else {
            NetworkStatus.Unavailable
        }
        trySend(currentStatus)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }.distinctUntilChanged()

    fun isCurrentlyConnected(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

    fun hasWifiConnection(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    }

    fun hasCellularConnection(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    }

    fun getConnectionType(): ConnectionType {
        val network = connectivityManager.activeNetwork
        if (network == null) return ConnectionType.NONE

        val capabilities = connectivityManager.getNetworkCapabilities(network)
            ?: return ConnectionType.NONE

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> ConnectionType.WIFI
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> ConnectionType.CELLULAR
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> ConnectionType.ETHERNET
            else -> ConnectionType.OTHER
        }
    }

    enum class ConnectionType {
        WIFI,
        CELLULAR,
        ETHERNET,
        OTHER,
        NONE
    }
}

// === ARCHIVO: app/src/main/res/values/strings.xml ===
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="app_name">OfflineFirst</string>
    <string name="app_description">Aplicación con soporte offline</string>
    
    <!-- Navigation -->
    <string name="nav_products">Productos</string>
    <string name="nav_purchases">Compras</string>
    <string name="nav_settings">Ajustes</string>
    
    <!-- Products -->
    <string name="products_title">Catálogo de Productos</string>
    <string name="products_empty">No hay productos disponibles</string>
    <string name="products_loading">Cargando productos…</string>
    <string name="products_error">Error al cargar productos</string>
    <string name="product_add_to_cart">Añadir al carrito</string>
    <string name="product_out_of_stock">Sin stock</string>
    <string name="product_in_stock">En stock: %d unidades</string>
    <string name="product_price_format">$%s</string>
    <string name="product_rating_format">%.1f ★</string>
    <string name="product_category_format">Categoría: %s</string>
    
    <!-- Purchase History -->
    <string name="purchases_title">Historial de Compras</string>
    <string name="purchases_empty">No hay compras realizadas</string>
    <string name="purchases_loading">Cargando historial…</string>
    <string name="purchases_error">Error al cargar historial</string>
    <string name="purchase_status_pending">Pendiente</string>
    <string name="purchase_status_processing">Procesando</string>
    <string name="purchase_status_shipped">Enviado</string>
    <string name="purchase_status_delivered">Entregado</string>
    <string name="purchase_status_cancelled">Cancelado</string>
    <string name="purchase_cancel">Cancelar compra</string>
    <string name="purchase_cancel_confirm">¿Estás seguro de que quieres cancelar esta compra?</string>
    <string name="purchase_days_ago">Hace %d días</string>
    <string name="purchase_total_format">Total: $%s</string>
    <string name="purchase_tracking">Seguimiento: %s</string>
    <string name="purchase_delivery_date">Entrega: %s</string>
    
    <!-- Payment Methods -->
    <string name="payment_credit_card">Tarjeta de Crédito</string>
    <string name="payment_debit_card">Tarjeta de Débito</string>
    <string name="payment_paypal">PayPal</string>
    <string name="payment_bank_transfer">Transferencia Bancaria</string>
    <string name="payment_cash_on_delivery">Contra Reembolso</string>
    <string name="payment_cryptocurrency">Criptomoneda</string>
    
    <!-- Settings -->
    <string name="settings_title">Ajustes</string>
    <string name="settings_sync_section">Sincronización</string>
    <string name="settings_auto_sync">Sincronización automática</string>
    <string name="settings_auto_sync_summary">Sincronizar datos cuando haya conexión</string>
    <string name="settings_sync_wifi_only">Solo WiFi</string>
    <string name="settings_sync_wifi_only_summary">Evitar datos móviles</string>
    <string name="settings_sync_now">Sincronizar ahora</string>
    <string name="settings_sync_now_summary">Última sincronización: %s</string>
    <string name="settings_clear_cache">Limpiar caché</string>
    <string name="settings_clear_cache_summary">Eliminar datos locales no sincronizados</string>
    <string name="settings_appearance_section">Apariencia</string>
    <string name="settings_dark_mode">Modo oscuro</string>
    <string name="settings_dark_mode_summary">Activar tema oscuro</string>
    <string name="settings_notifications_section">Notificaciones</string>
    <string name="settings_notifications_enabled">Notificaciones push</string>
    <string name="settings_notifications_summary">Recibir alertas de sincronización</string>
    <string name="settings_about_section">Acerca de</string>
    <string name="settings_version">Versión %s</string>
    <string name="settings_privacy_policy">Política de privacidad</string>
    <string name="settings_terms_of_service">Términos de servicio</string>
    
    <!-- Sync -->
    <string name="sync_status_synced">Sincronizado</string>
    <string name="sync_status_pending">Pendiente de sincronizar</string>
    <string name="sync_status_syncing">Sincronizando…</string>
    <string name="sync_status_error">Error de sincronización</string>
    <string name="sync_status_offline">Sin conexión</string>
    <string name="sync_banner_pending">Tienes %d cambios pendientes</string>
    <string name="sync_banner_error">Error al sincronizar. Toca para reintentar.</string>
    <string name="sync_last_sync">Última sincronización: %s</string>
    <string name="sync_never">Nunca</string>
    
    <!-- Common -->
    <string name="ok">Aceptar</string>
    <string name="cancel">Cancelar</string>
    <string name="retry">Reintentar</string>
    <string name="retry_action">Reintentar acción</string>
    <string name="loading">Cargando…</string>
    <string name="error_generic">Ha ocurrido un error</string>
    <string name="error_network">Sin conexión a Internet</string>
    <string name="error_server">Error del servidor</string>
    <string name="error_unknown">Error desconocido</string>
    <string name="success">Operación exitosa</string>
    <string name="confirm">Confirmar</string>
    <string name="delete">Eliminar</string>
    <string name="edit">Editar</string>
    <string name="save">Guardar</string>
    <string name="search">Buscar</string>
    <string name="filter">Filtrar</string>
    <string name="sort">Ordenar</string>
    <string name="refresh">Actualizar</string>
    <string name="no_data">No hay datos</string>
    <string name="pull_to_refresh">Desliza para actualizar</string>
    
    <!-- Time -->
    <string name="time_just_now">Ahora mismo</string>
    <string name="time_minutes_ago">Hace %d minutos</string>
    <string name="time_hours_ago">Hace %d horas</string>
    <string name="time_yesterday">Ayer</string>
    <string name="time_days_ago">Hace %d días</string>
</resources>

// === ARCHIVO: app/src/main/res/values/colors.xml ===
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!-- Paleta de colores primarios - Tono azul profundo que representa confianza y tecnología -->
    <color name="md_theme_light_primary">#006590</color>
    <color name="md_theme_light_onPrimary">#FFFFFF</color>
    <color name="md_theme_light_primaryContainer">#C7E7FF</color>
    <color name="md_theme_light_onPrimaryContainer">#001E2F</color>

    <!-- Paleta de colores secundarios - Tono verde azulado para acciones complementarias -->
    <color name="md_theme_light_secondary">#4F606E</color>
    <color name="md_theme_light_onSecondary">#FFFFFF</color>
    <color name="md_theme_light_secondaryContainer">#D3E4F5</color>
    <color name="md_theme_light_onSecondaryContainer">#0C1D29</color>

    <!-- Paleta de colores terciarios - Tono magenta para acentos y elementos destacados -->
    <color name="md_theme_light_tertiary">#63597C</color>
    <color name="md_theme_light_onTertiary">#FFFFFF</color>
    <color name="md_theme_light_tertiaryContainer">#E9DDFF</color>
    <color name="md_theme_light_onTertiaryContainer">#1F1635</color>

    <!-- Colores de error - Rojos estándar de Material Design 3 -->
    <color name="md_theme_light_error">#BA1A1A</color>
    <color name="md_theme_light_onError">#FFFFFF</color>
    <color name="md_theme_light_errorContainer">#FFDAD6</color>
    <color name="md_theme_light_onErrorContainer">#410002</color>

    <!-- Colores de fondo y superficie - Tonos neutros claros -->
    <color name="md_theme_light_background">#FDFBFF</color>
    <color name="md_theme_light_onBackground">#1A1C1E</color>
    <color name="md_theme_light_surface">#FDFBFF</color>
    <color name="md_theme_light_onSurface">#1A1C1E</color>
    <color name="md_theme_light_surfaceVariant">#DEE3EB</color>
    <color name="md_theme_light_onSurfaceVariant">#41484D</color>

    <!-- Colores de contorno y separación -->
    <color name="md_theme_light_outline">#71787E</color>
    <color name="md_theme_light_outlineVariant">#C1C7CE</color>
    <color name="md_theme_light_inverseSurface">#2F3033</color>
    <color name="md_theme_light_inverseOnSurface">#F1F0F4</color>
    <color name="md_theme_light_inversePrimary">#86CEE8</color>

    <!-- Colores específicos para estados de sincronización -->
    <color name="sync_success">#2E7D32</color>
    <color name="sync_warning">#F57C00</color>
    <color name="sync_error">#D32F2F</color>
    <color name="sync_pending">#757575</color>

    <!-- Colores para indicadores de estado de conexión -->
    <color name="connection_online">#4CAF50</color>
    <color name="connection_offline">#F44336</color>

    <!-- Paleta de colores para tema oscuro - Inversiones de la paleta clara -->
    <color name="md_theme_dark_primary">#86CEE8</color>
    <color name="md_theme_dark_onPrimary">#00344D</color>
    <color name="md_theme_dark_primaryContainer">#004C6B</color>
    <color name="md_theme_dark_onPrimaryContainer">#C7E7FF</color>

    <color name="md_theme_dark_secondary">#B6C8D9</color>
    <color name="md_theme_dark_onSecondary">#21323F</color>
    <color name="md_theme_dark_secondaryContainer">#384956</color>
    <color name="md_theme_dark_onSecondaryContainer">#D3E4F5</color>

    <color name="md_theme_dark_tertiary">#CDC1E9</color>
    <color name="md_theme_dark_onTertiary">#342B4B</color>
    <color name="md_theme_dark_tertiaryContainer">#4B4263</color>
    <color name="md_theme_dark_onTertiaryContainer">#E9DDFF</color>

    <color name="md_theme_dark_error">#FFB4AB</color>
    <color name="md_theme_dark_onError">#690005</color>
    <color name="md_theme_dark_errorContainer">#93000A</color>
    <color name="md_theme_dark_onErrorContainer">#FFDAD6</color>

    <color name="md_theme_dark_background">#1A1C1E</color>
    <color name="md_theme_dark_onBackground">#E2E2E6</color>
    <color name="md_theme_dark_surface">#1A1C1E</color>
    <color name="md_theme_dark_onSurface">#E2E2E6</color>
    <color name="md_theme_dark_surfaceVariant">#41484D</color>
    <color name="md_theme_dark_onSurfaceVariant">#C1C7CE</color>

    <color name="md_theme_dark_outline">#8B9198</color>
    <color name="md_theme_dark_outlineVariant">#41484D</color>
    <color name="md_theme_dark_inverseSurface">#E2E2E6</color>
    <color name="md_theme_dark_inverseOnSurface">#2F3033</color>
    <color name="md_theme_dark_inversePrimary">#006590</color>

    <!-- Colores de gradiente para elementos decorativos en la interfaz -->
    <color name="gradient_start">#006590</color>
    <color name="gradient_end">#004C6B</color>

    <!-- Colores semánticos para el dominio de la aplicación -->
    <color name="product_available">#4CAF50</color>
    <color name="product_out_of_stock">#F44336</color>
    <color name="product_low_stock">#FF9800</color>

    <color name="purchase_completed">#4CAF50</color>
    <color name="purchase_pending">#FF9800</color>
    <color name="purchase_cancelled">#F44336</color>
    <color name="purchase_shipped">#2196F3</color>

    <!-- Colores para categorías de productos -->
    <color name="category_electronics">#2196F3</color>
    <color name="category_clothing">#9C27B0</color>
    <color name="category_books">#795548</color>
    <color name="category_home">#4CAF50</color>
    <color name="category_sports">#FF5722</color>
    <color name="category_food">#FFC107</color>
    <color name="category_other">#607D8B</color>

    <!-- Colores para fondos de tarjetas y elevación -->
    <color name="card_background_light">#FFFFFF</color>
    <color name="card_background_dark">#2D2D2D</color>
    <color name="elevation_overlay">#1A000000</color>

    <!-- Colores para indicadores de carga y progreso -->
    <color name="loading_indicator">#006590</color>
    <color name="progress_track">#E0E0E0</color>
    <color name="progress_track_dark">#424242</color>
</resources>// === ARCHIVO: app/src/main/res/values/themes.xml ===
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!--
        Tema principal de la aplicación basado en Material Design 3.
        Este archivo define los colores, tipografía y estilos para toda la aplicación.
        Los colores se sincronizan con la paleta definida en colors.xml.
    -->
    <style name="Theme.OfflineFirst" parent="android:Theme.Material.Light.NoActionBar">
        <!-- Colores primarios del tema -->
        <item name="android:colorPrimary">@color/md_theme_light_primary</item>
        <item name="android:colorPrimaryDark">@color/md_theme_light_primaryContainer</item>
        <item name="android:colorAccent">@color/md_theme_light_secondary</item>

        <!-- Colores de fondo y superficie -->
        <item name="android:windowBackground">@color/md_theme_light_background</item>
        <item name="android:colorBackground">@color/md_theme_light_background</item>
        <item name="android:statusBarColor">@color/md_theme_light_surface</item>
        <item name="android:navigationBarColor">@color/md_theme_light_surface</item>

        <!-- Colores de texto principales -->
        <item name="android:textColorPrimary">@color/md_theme_light_onBackground</item>
        <item name="android:textColorSecondary">@color/md_theme_light_onSurfaceVariant</item>

        <!-- Configuración de la barra de estado -->
        <item name="android:windowLightStatusBar">true</item>
    </style>

    <!--
        Definición de estilos de texto usando la escala tipográfica de Material Design 3.
        Estos estilos se aplican a través de android:textAppearance en los layouts XML.
    -->
    <style name="TextAppearance.OfflineFirst.DisplayLarge" parent="TextAppearance.Material3.DisplayLarge">
        <item name="android:fontFamily">sans-serif</item>
        <item name="android:textSize">57sp</item>
        <item name="android:letterSpacing">-0.0025</item>
        <item name="android:textColor">@color/md_theme_light_onBackground</item>
    </style>

    <style name="TextAppearance.OfflineFirst.DisplayMedium" parent="TextAppearance.Material3.DisplayMedium">
        <item name="android:fontFamily">sans-serif</item>
        <item name="android:textSize">45sp</item>
        <item name="android:letterSpacing">0</item>
        <item name="android:textColor">@color/md_theme_light_onBackground</item>
    </style>

    <style name="TextAppearance.OfflineFirst.DisplaySmall" parent="TextAppearance.Material3.DisplaySmall">
        <item name="android:fontFamily">sans-serif</item>
        <item name="android:textSize">36sp</item>
        <item name="android:letterSpacing">0.0025</item>
        <item name="android:textColor">@color/md_theme_light_onBackground</item>
    </style>

    <style name="TextAppearance.OfflineFirst.HeadlineLarge" parent="TextAppearance.Material3.HeadlineLarge">
        <item name="android:fontFamily">sans-serif</item>
        <item name="android:textSize">32sp</item>
        <item name="android:letterSpacing">0</item>
        <item name="android:textColor">@color/md_theme_light_onBackground</item>
    </style>

    <style name="TextAppearance.OfflineFirst.HeadlineMedium" parent="TextAppearance.Material3.HeadlineMedium">
        <item name="android:fontFamily">sans-serif</item>
        <item name="android:textSize">28sp</item>
        <item name="android:letterSpacing">0</item>
        <item name="android:textColor">@color/md_theme_light_onBackground</item>
    </style>

    <style name="TextAppearance.OfflineFirst.HeadlineSmall" parent="TextAppearance.Material3.HeadlineSmall">
        <item name="android:fontFamily">sans-serif</item>
        <item name="android:textSize">24sp</item>
        <item name="android:letterSpacing">0</item>
        <item name="android:textColor">@color/md_theme_light_onBackground</item>
    </style>

    <style name="TextAppearance.OfflineFirst.TitleLarge" parent="TextAppearance.Material3.TitleLarge">
        <item name="android:fontFamily">sans-serif-medium</item>
        <item name="android:textSize">22sp</item>
        <item name="android:letterSpacing">0</item>
        <item name="android:textColor">@color/md_theme_light_onBackground</item>
    </style>

    <style name="TextAppearance.OfflineFirst.TitleMedium" parent="TextAppearance.Material3.TitleMedium">
        <item name="android:fontFamily">sans-serif-medium</item>
        <item name="android:textSize">16sp</item>
        <item name="android:letterSpacing">0.0015</item>
        <item name="android:textColor">@color/md_theme_light_onBackground</item>
    </style>

    <style name="TextAppearance.OfflineFirst.TitleSmall" parent="TextAppearance.Material3.TitleSmall">
        <item name="android:fontFamily">sans-serif-medium</item>
        <item name="android:textSize">14sp</item>
        <item name="android:letterSpacing">0.001</item>
        <item name="android:textColor">@color/md_theme_light_onBackground</item>
    </style>

    <style name="TextAppearance.OfflineFirst.BodyLarge" parent="TextAppearance.Material3.BodyLarge">
        <item name="android:fontFamily">sans-serif</item>
        <item name="android:textSize">16sp</item>
        <item name="android:letterSpacing">0.005</item>
        <item name="android:textColor">@color/md_theme_light_onBackground</item>
    </style>

    <style name="TextAppearance.OfflineFirst.BodyMedium" parent="TextAppearance.Material3.BodyMedium">
        <item name="android:fontFamily">sans-serif</item>
        <item name="android:textSize">14sp</item>
        <item name="android:letterSpacing">0.0025</item>
        <item name="android:textColor">@color/md_theme_light_onBackground</item>
    </style>

    <style name="TextAppearance.OfflineFirst.BodySmall" parent="TextAppearance.Material3.BodySmall">
        <item name="android:fontFamily">sans-serif</item>
        <item name="android:textSize">12sp</item>
        <item name="android:letterSpacing">0.004</item>
        <item name="android:textColor">@color/md_theme_light_onSurfaceVariant</item>
    </style>

    <style name="TextAppearance.OfflineFirst.LabelLarge" parent="TextAppearance.Material3.LabelLarge">
        <item name="android:fontFamily">sans-serif-medium</item>
        <item name="android:textSize">14sp</item>
        <item name="android:letterSpacing">0.001</item>
        <item name="android:textColor">@color/md_theme_light_onBackground</item>
    </style>

    <style name="TextAppearance.OfflineFirst.LabelMedium" parent="TextAppearance.Material3.LabelMedium">
        <item name="android:fontFamily">sans-serif-medium</item>
        <item name="android:textSize">12sp</item>
        <item name="android:letterSpacing">0.005</item>
        <item name="android:textColor">@color/md_theme_light_onBackground</item>
    </style>

    <style name="TextAppearance.OfflineFirst.LabelSmall" parent="TextAppearance.Material3.LabelSmall">
        <item name="android:fontFamily">sans-serif-medium</item>
        <item name="android:textSize">11sp</item>
        <item name="android:letterSpacing">0.005</item>
        <item name="android:textColor">@color/md_theme_light_onSurfaceVariant</item>
    </style>

    <!-- Estilos para componentes de tarjeta con elevación -->
    <style name="Widget.OfflineFirst.CardView" parent="Widget.Material3.CardView.Elevated">
        <item name="cardBackgroundColor">@color/card_background_light</item>
        <item name="cardCornerRadius">12dp</item>
        <item name="cardElevation">4dp</item>
        <item name="contentPadding">16dp</item>
    </style>

    <!-- Estilos para botones según la especificación de Material Design 3 -->
    <style name="Widget.OfflineFirst.Button.Primary" parent="Widget.Material3.Button">
        <item name="android:textColor">@color/md_theme_light_onPrimary</item>
        <item name="backgroundTint">@color/md_theme_light_primary</item>
        <item name="cornerRadius">20dp</item>
    </style>

    <style name="Widget.OfflineFirst.Button.Secondary" parent="Widget.Material3.Button.TonalButton">
        <item name="android:textColor">@color/md_theme_light_onSecondaryContainer</item>
        <item name="backgroundTint">@color/md_theme_light_secondaryContainer</item>
        <item name="cornerRadius">20dp</item>
    </style>

    <style name="Widget.OfflineFirst.Button.Text" parent="Widget.Material3.Button.TextButton">
        <item name="android:textColor">@color/md_theme_light_primary</item>
    </style>

    <!-- Estilos para campos de texto -->
    <style name="Widget.OfflineFirst.TextInputLayout" parent="Widget.Material3.TextInputLayout.OutlinedBox">
        <item name="boxStrokeColor">@color/md_theme_light_primary</item>
        <item name="hintTextColor">@color/md_theme_light_onSurfaceVariant</item>
        <item name="boxCornerRadiusTopStart">8dp</item>
        <item name="boxCornerRadiusTopEnd">8dp</item>
        <item name="boxCornerRadiusBottomStart">8dp</item>
        <item name="boxCornerRadiusBottomEnd">8dp</item>
    </style>

    <!-- Estilos para la barra de herramientas -->
    <style name="Widget.OfflineFirst.Toolbar" parent="Widget.Material3.Toolbar">
        <item name="android:background">@color/md_theme_light_surface</item>
        <item name="titleTextColor">@color/md_theme_light_onSurface</item>
    </style>

    <!-- Estilos para la barra de navegación inferior -->
    <style name="Widget.OfflineFirst.BottomNavigation" parent="Widget.Material3.BottomNavigationView">
        <item name="android:background">@color/md_theme_light_surface</item>
        <item name="itemIconTint">@color/md_theme_light_onSurfaceVariant</item>
        <item name="itemTextColor">@color/md_theme_light_onSurfaceVariant</item>
        <item name="labelVisibilityMode">labeled</item>
    </style>

    <!-- Estilos para indicadores de estado de sincronización -->
    <style name="Widget.OfflineFirst.SyncStatusBanner">
        <item name="android:background">@color/md_theme_light_secondaryContainer</item>
        <item name="android:textColor">@color/md_theme_light_onSecondaryContainer</item>
        <item name="android:padding">12dp</item>
        <item name="android:gravity">center</item>
    </style>

    <!-- Estilos para chips de categorías -->
    <style name="Widget.OfflineFirst.Chip.Category" parent="Widget.Material3.Chip.Filter">
        <item name="chipCornerRadius">8dp</item>
        <item name="chipBackgroundColor">@color/md_theme_light_surfaceVariant</item>
        <item name="android:textColor">@color/md_theme_light_onSurfaceVariant</item>
    </style>

    <!-- Estilos para indicadores de estado de conexión -->
    <style name="Widget.OfflineFirst.ConnectionIndicator">
        <item name="android:padding">4dp</item>
        <item name="android:textSize">12sp</item>
    </style>

    <!-- Estilos para diálogos -->
    <style name="ThemeOverlay.OfflineFirst.Dialog" parent="ThemeOverlay.Material3.MaterialAlertDialog">
        <item name="colorPrimary">@color/md_theme_light_primary</item>
        <item name="colorOnPrimary">@color/md_theme_light_onPrimary</item>
        <item name="colorSurface">@color/md_theme_light_surface</item>
        <item name="colorOnSurface">@color/md_theme_light_onSurface</item>
        <item name="dialogCornerRadius">28dp</item>
    </style>

    <!-- Estilos para Snackbar -->
    <style name="ThemeOverlay.OfflineFirst.Snackbar" parent="ThemeOverlay.Material3.Snackbar">
        <item name="android:textColor">@color/md_theme_light_onPrimary</item>
        <item name="backgroundTint">@color/md_theme_light_inverseSurface</item>
    </style>

    <!-- Definición de animaciones de transición de actividades -->
    <style name="Animation.OfflineFirst.ActivityTransition">
        <item name="android:activityOpenEnterAnimation">@android:anim/fade_in</item>
        <item name="android:activityOpenExitAnimation">@android:anim/fade_out</item>
        <item name="android:activityCloseEnterAnimation">@android:anim/fade_in</item>
        <item name="android:activityCloseExitAnimation">@android:anim/fade_out</item>
    </style>
</resources>

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/sync/ConflictResolver.kt ===
package com.example.offlinefirst.data.sync

import com.example.offlinefirst.domain.model.Product
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.SyncState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConflictResolver @Inject constructor() {

    fun resolveProductConflict(localProduct: Product): Product {
        return localProduct
    }

    fun resolveProductConflict(local: Product, remote: Product): Product {
        return if (local.lastUpdated > remote.lastUpdated) local else remote
    }

    fun hasConflict(local: Product, remote: Product): Boolean {
        return local.lastUpdated != remote.lastUpdated
    }

    fun mergeProducts(local: Product, remote: Product): Product {
        return remote.copy(
            name = local.name.ifEmpty { remote.name },
            description = local.description.ifEmpty { remote.description },
            stock = if (local.stock != remote.stock) maxOf(local.stock, remote.stock) else local.stock
        )
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/entity/Product.kt ===
package com.example.offlinefirst.data.local.entity

import java.math.BigDecimal

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        price = this.price,
        stock = this.stock,
        category = this.category.name,
        imageUrl = this.imageUrl ?: "",
        isAvailable = this.isAvailable,
        rating = this.rating,
        lastUpdated = this.lastUpdated,
        syncStatus = SyncState.SYNCED.name
    )
}

fun Product.toPendingEntity(): ProductEntity {
    return ProductEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        price = this.price,
        stock = this.stock,
        category = this.category.name,
        imageUrl = this.imageUrl ?: "",
        isAvailable = this.isAvailable,
        rating = this.rating,
        lastUpdated = System.currentTimeMillis(),
        syncStatus = SyncState.PENDING.name
    )
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/dao/ProductDao.kt (actualizado con métodos faltantes) ===
package com.example.offlinefirst.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.offlinefirst.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    fun getAllProducts(): Flow<List<ProductEntity>>
    fun getProductById(productId: String): Flow<ProductEntity?>
    suspend fun getProductByIdSync(productId: String): ProductEntity?
    fun getProductsByCategory(category: String): Flow<List<ProductEntity>>
    fun searchProducts(query: String): Flow<List<ProductEntity>>
    fun getAvailableProducts(): Flow<List<ProductEntity>>
    suspend fun getProductsToSync(): List<ProductEntity>
    suspend fun getProductsUpdatedSince(lastSyncTimestamp: Long): List<ProductEntity>
    suspend fun insertProduct(product: ProductEntity)
    suspend fun insertProducts(products: List<ProductEntity>)
    suspend fun updateProduct(product: ProductEntity)
    suspend fun deleteProduct(product: ProductEntity)
    suspend fun deleteProductById(productId: String)
    suspend fun deleteAllProducts()
    suspend fun updateSyncStatus(productId: String, syncStatus: String)
    suspend fun updateStock(productId: String, newStock: Int, updatedAt: Long)
    suspend fun updateAvailability(productId: String, isAvailable: Boolean, updatedAt: Long)
    @Query("SELECT COUNT(*) FROM products")
    fun getProductCount(): Flow<Int>
    @Query("SELECT COUNT(*) FROM products WHERE isAvailable = 1 AND stock > 0")
    fun getAvailableProductCount(): Flow<Int>
    fun getProductsByPriceRange(minPrice: Double, maxPrice: Double): Flow<List<ProductEntity>>
    fun getAllCategories(): Flow<List<String>>
    
    // Métodos faltantes para sincronización offline
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPendingProduct(product: ProductEntity)
    
    @Query("UPDATE products SET syncStatus = 'PENDING', isDeleted = 1 WHERE id = :productId")
    suspend fun markProductForDeletion(productId: String)
    
    @Query("SELECT * FROM products WHERE syncStatus = 'PENDING' OR syncStatus = 'FAILED'")
    suspend fun getPendingProducts(): List<ProductEntity>
    
    @Query("DELETE FROM products WHERE syncStatus = 'PENDING' AND id = :productId")
    suspend fun deletePendingProduct(productId: String)
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/remote/dto/PurchaseDto.kt (actualizado) ===
package com.example.offlinefirst.data.remote.dto

import com.example.offlinefirst.domain.model.PurchaseHistory
import com.example.offlinefirst.domain.model.PurchaseStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class PurchaseDto(
    @SerialName("id")
    val id: String,
    
    @SerialName("userId")
    val userId: String,
    
    @SerialName("productId")
    val productId: String,
    
    @SerialName("productName")
    val productName: String,
    
    @SerialName("productImageUrl")
    val productImageUrl: String? = null,
    
    @SerialName("quantity")
    val quantity: Int,
    
    @SerialName("unitPrice")
    val unitPrice: Double,
    
    @SerialName("totalPrice")
    val totalPrice: Double,
    
    @SerialName("currency")
    val currency: String = "USD",
    
    @SerialName("status")
    val status: String,
    
    @SerialName("paymentMethod")
    val paymentMethod: String,
    
    @SerialName("shippingAddress")
    val shippingAddress: String? = null,
    
    @SerialName("purchaseDate")
    val purchaseDate: Long,
    
    @SerialName("deliveryDate")
    val deliveryDate: Long? = null,
    
    @SerialName("trackingNumber")
    val trackingNumber: String? = null,
    
    @SerialName("notes")
    val notes: String? = null,
    
    @SerialName("createdAt")
    val createdAt: Long,
    
    @SerialName("updatedAt")
    val updatedAt: Long
) {
    fun toDomain(): PurchaseHistory {
        return PurchaseHistory(
            id = id,
            userId = userId,
            productId = productId,
            productName = productName,
            productImageUrl = productImageUrl,
            quantity = quantity,
            unitPrice = BigDecimal.valueOf(unitPrice),
            totalPrice = BigDecimal.valueOf(totalPrice),
            currency = currency,
            status = parseStatus(status),
            paymentMethod = paymentMethod,
            shippingAddress = shippingAddress,
            purchaseDate = purchaseDate,
            deliveryDate = deliveryDate,
            trackingNumber = trackingNumber,
            notes = notes,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun parseStatus(status: String): PurchaseStatus {
        return when (status.uppercase()) {
            "PENDING" -> PurchaseStatus.PENDING
            "CONFIRMED" -> PurchaseStatus.CONFIRMED
            "PROCESSING" -> PurchaseStatus.PROCESSING
            "SHIPPED" -> PurchaseStatus.SHIPPED
            "DELIVERED" -> PurchaseStatus.DELIVERED
            "CANCELLED" -> PurchaseStatus.CANCELLED
            "REFUNDED" -> PurchaseStatus.REFUNDED
            else -> PurchaseStatus.UNKNOWN
        }
    }

    companion object {
        fun fromDomain(purchase: PurchaseHistory): PurchaseDto {
            return PurchaseDto(
                id = purchase.id,
                userId = purchase.userId,
                productId = purchase.productId,
                productName = purchase.productName,
                productImageUrl = purchase.productImageUrl,
                quantity = purchase.quantity,
                unitPrice = purchase.unitPrice.toDouble(),
                totalPrice = purchase.totalPrice.toDouble(),
                currency = purchase.currency,
                status = purchase.status.name,
                paymentMethod = purchase.paymentMethod,
                shippingAddress = purchase.shippingAddress,
                purchaseDate = purchase.purchaseDate,
                deliveryDate = purchase.deliveryDate,
                trackingNumber = purchase.trackingNumber,
                notes = purchase.notes,
                createdAt = purchase.createdAt,
                updatedAt = purchase.updatedAt
            )
        }
    }
}

@Serializable
data class PurchaseListResponse(
    @SerialName("purchases")
    val purchases: List<PurchaseDto>,
    
    @SerialName("total")
    val total: Int,
    
    @SerialName("page")
    val page: Int,
    
    @SerialName("pageSize")
    val pageSize: Int,
    
    @SerialName("hasMore")
    val hasMore: Boolean
)

@Serializable
data class CreatePurchaseRequest(
    @SerialName("productId")
    val productId: String,
    
    @SerialName("quantity")
    val quantity: Int,
    
    @SerialName("paymentMethod")
    val paymentMethod: String,
    
    @SerialName("shippingAddress")
    val shippingAddress: String? = null,
    
    @SerialName("notes")
    val notes: String? = null
)

@Serializable
data class PurchaseResponse(
    @SerialName("purchase")
    val purchase: PurchaseDto,
    
    @SerialName("message")
    val message: String,
    
    @SerialName("confirmationCode")
    val confirmationCode: String
)

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/repository/ProductRepositoryImpl.kt (actualizado) ===
package com.example.offlinefirst.data.repository

import com.example.offlinefirst.data.local.dao.ProductDao
import com.example.offlinefirst.data.local.entity.ProductEntity
import com.example.offlinefirst.data.local.entity.toDomain
import com.example.offlinefirst.data.local.entity.toEntity
import com.example.offlinefirst.data.local.entity.toPendingEntity
import com.example.offlinefirst.data.remote.api.OfflineFirstApiService
import com.example.offlinefirst.data.remote.dto.CreateProductRequest
import com.example.offlinefirst.data.remote.dto.ProductDto
import com.example.offlinefirst.data.remote.dto.UpdateProductRequest
import com.example.offlinefirst.data.sync.ConflictResolver
import com.example.offlinefirst.domain.model.Product
import com.example.offlinefirst.domain.model.ProductCategory
import com.example.offlinefirst.domain.model.SyncState
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.repository.ProductRepository
import com.example.offlinefirst.util.Resource
import com.example.offlinefirst.util.Error
import com.example.offlinefirst.util.Loading
import com.example.offlinefirst.util.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val apiService: OfflineFirstApiService,
    private val conflictResolver: ConflictResolver
) : ProductRepository {

    override fun getProducts(forceRefresh: Boolean): Flow<Resource<List<Product>>> = flow {
        emit(Loading())
        
        val localProducts = productDao.getAllProducts().first()
        if (localProducts.isNotEmpty() && !forceRefresh) {
            emit(Success(localProducts.map { it.toDomain() }))
            return@flow
        }
        
        try {
            val response = apiService.getProducts()
            if (response.isSuccessful && response.body() != null) {
                val remoteProducts = response.body()!!.products
                val localEntities = remoteProducts.map { it.toEntity() }
                
                productDao.insertProducts(localEntities)
                emit(Success(localEntities.map { it.toDomain() }))
            } else {
                if (localProducts.isNotEmpty()) {
                    emit(Success(localProducts.map { it.toDomain() }))
                } else {
                    emit(Error("Error al cargar productos: ${response.message()}"))
                }
            }
        } catch (e: Exception) {
            if (localProducts.isNotEmpty()) {
                emit(Success(localProducts.map { it.toDomain() }))
            } else {
                emit(Error("Error de conexión: ${e.localizedMessage}"))
            }
        }
    }

    override fun getProductById(id: String): Flow<Resource<Product>> = flow {
        emit(Loading())
        
        val localProduct = productDao.getProductById(id).first()
        if (localProduct != null) {
            emit(Success(localProduct.toDomain()))
            return@flow
        }
        
        try {
            val response = apiService.getProductById(id)
            if (response.isSuccessful && response.body() != null) {
                val product = response.body()!!
                productDao.insertProduct(product.toEntity())
                emit(Success(product.toDomain()))
            } else {
                emit(Error("Producto no encontrado"))
            }
        } catch (e: Exception) {
            emit(Error("Error de conexión: ${e.localizedMessage}"))
        }
    }

    override fun getProductsByCategory(category: ProductCategory): Flow<Resource<List<Product>>> = flow {
        emit(Loading())
        
        val localProducts = productDao.getProductsByCategory(category.name).first()
        if (localProducts.isNotEmpty()) {
            emit(Success(localProducts.map { it.toDomain() }))
        } else {
            emit(Error("No hay productos en esta categoría"))
        }
    }

    override suspend fun createProduct(product: Product): Resource<Product> {
        return try {
            val request = CreateProductRequest(
                name = product.name,
                description = product.description,
                price = product.price.toDouble(),
                stock = product.stock,
                category = product.category.name,
                imageUrl = product.imageUrl
            )
            val response = apiService.createProduct(request)
            if (response.isSuccessful && response.body() != null) {
                val createdProduct = response.body()!!
                productDao.insertProduct(createdProduct.toEntity())
                Success(createdProduct.toDomain())
            } else {
                Error("Error al crear producto: ${response.message()}")
            }
        } catch (e: Exception) {
            val pendingEntity = product.toPendingEntity()
            productDao.insertPendingProduct(pendingEntity)
            Success(product)
        }
    }

    override suspend fun updateProduct(product: Product): Resource<Product> {
        return try {
            val request = UpdateProductRequest(
                name = product.name,
                description = product.description,
                price = product.price.toDouble(),
                stock = product.stock,
                category = product.category.name,
                imageUrl = product.imageUrl,
                available = product.isAvailable
            )
            val response = apiService.updateProduct(product.id, request)
            if (response.isSuccessful && response.body() != null) {
                val updatedProduct = response.body()!!
                productDao.insertProduct(updatedProduct.toEntity())
                Success(updatedProduct.toDomain())
            } else {
                Error("Error al actualizar producto: ${response.message()}")
            }
        } catch (e: Exception) {
            val pendingEntity = product.toPendingEntity()
            productDao.insertPendingProduct(pendingEntity)
            Success(product)
        }
    }

    override suspend fun deleteProduct(id: String): Resource<Unit> {
        return try {
            val response = apiService.deleteProduct(id)
            if (response.isSuccessful) {
                productDao.deleteProduct(id)
                Success(Unit)
            } else {
                productDao.markProductForDeletion(id)
                Success(Unit)
            }
        } catch (e: Exception) {
            productDao.markProductForDeletion(id)
            Success(Unit)
        }
    }

    override fun searchProducts(query: String): Flow<Resource<List<Product>>> = flow {
        emit(Loading())
        
        val results = productDao.searchProducts("%$query%").first()
        emit(Success(results.map { it.toDomain() }))
    }

    override fun getPendingProducts(): Flow<List<Product>> {
        return productDao.getPendingProducts().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun syncPendingProducts(): Resource<Int> {
        val pendingProducts = productDao.getPendingProducts()
        var syncedCount = 0
        
        for (product in pendingProducts) {
            try {
                val result = conflictResolver.resolveProductConflict(product.toDomain())
                val request = UpdateProductRequest(
                    name = result.name,
                    description = result.description,
                    price = result.price.toDouble(),
                    stock = result.stock,
                    category = result.category.name,
                    imageUrl = result.imageUrl,
                    available = result.isAvailable
                )
                val response = apiService.updateProduct(result.id, request)
                if (response.isSuccessful) {
                    productDao.deletePendingProduct(product.id)
                    syncedCount++
                }
            } catch (e: Exception) {
                // Continuar con el siguiente producto
            }
        }
        
        return Success(syncedCount)
    }

    private fun ProductDto.toEntity(): ProductEntity {
        return ProductEntity(
            id = id,
            name = name,
            description = description,
            price = BigDecimal(price.toString()),
            stock = stock,
            category = category,
            imageUrl = imageUrl ?: "",
            isAvailable = available,
            rating = rating,
            lastUpdated = System.currentTimeMillis(),
            syncStatus = SyncState.SYNCED.name
        )
    }

    private fun ProductEntity.toDomain(): Product {
        return Product(
            id = id,
            name = name,
            description = description,
            price = price,
            stock = stock,
            category = try { ProductCategory.valueOf(category) } catch (e: Exception) { ProductCategory.OTHER },
            imageUrl = imageUrl,
            isAvailable = isAvailable,
            rating = rating,
            lastUpdated = lastUpdated
        )
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/util/Resource.kt (agregando Loading, Success, Error como standalone) ===
package com.example.offlinefirst.util

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(
        val message: String,
        val throwable: Throwable? = null
    ) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
    
    fun getOrNull(): T? = (this as? Success)?.data
    fun getOrDefault(default: @UnsafeVariance T): T = getOrNull() ?: default
    
    fun <R> map(transform: (T) -> R): Resource<R> = when (this) {
        is Success -> Success(transform(data))
        is Error -> Error(message, throwable)
        is Loading -> Loading
    }
    
    inline fun onSuccess(action: (T) -> Unit): Resource<T> {
        if (this is Success) action(data)
        return this
    }
    
    inline fun onError(action: (String, Throwable?) -> Unit): Resource<T> {
        if (this is Error) action(message, throwable)
        return this
    }
    
    inline fun onLoading(action: () -> Unit): Resource<T> {
        if (this is Loading) action()
        return this
    }
}

fun <T> Loading(): Resource<T> = Resource.Loading
fun <T> Success(data: T): Resource<T> = Resource.Success(data)
fun <T> Error(message: String, throwable: Throwable? = null): Resource<T> = Resource.Error(message, throwable)

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/di/NetworkModule.kt (actualizado sin BuildConfig) ===
package com.example.offlinefirst.di

import android.content.Context
import com.example.offlinefirst.data.remote.api.OfflineFirstApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.offlinefirst.example.com/"
    private const val CONNECT_TIMEOUT = 30L
    private const val READ_TIMEOUT = 30L
    private const val WRITE_TIMEOUT = 30L

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
        prettyPrint = true
        coerceInputValues = true
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @ApplicationContext context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("X-Platform", "Android")
                    .build()
                chain.proceed(newRequest)
            }
            .addNetworkInterceptor { chain ->
                val response = chain.proceed(chain.request())
                response
            }
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideOfflineFirstApiService(retrofit: Retrofit): OfflineFirstApiService =
        retrofit.create(OfflineFirstApiService::class.java)
}


// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/model/SyncStatus.kt ===
package com.example.offlinefirst.domain.model

import com.example.offlinefirst.util.Error
import java.util.UUID

data class SyncStatus(
    val id: String = UUID.randomUUID().toString(),
    val entityType: EntityType,
    val entityId: String,
    val status: SyncState = SyncState.PENDING,
    val localTimestamp: Long = System.currentTimeMillis(),
    val remoteTimestamp: Long? = null,
    val errorMessage: String? = null,
    val retryCount: Int = 0,
    val lastAttemptTimestamp: Long? = null,
    val conflictData: ConflictData? = null
) {
    fun isPending(): Boolean = status == SyncState.PENDING
    fun isSyncing(): Boolean = status == SyncState.SYNCING
    fun isSynced(): Boolean = status == SyncState.SYNCED
    fun isFailed(): Boolean = status == SyncState.FAILED
    fun hasConflict(): Boolean = conflictData != null
    fun canRetry(): Boolean = retryCount < MAX_RETRY_COUNT && (status == SyncState.FAILED || status == SyncState.PENDING)

    fun markAsSyncing(): SyncStatus {
        return copy(
            status = SyncState.SYNCING,
            lastAttemptTimestamp = System.currentTimeMillis()
        )
    }

    fun markAsSynced(remoteTimestamp: Long = System.currentTimeMillis()): SyncStatus {
        return copy(
            status = SyncState.SYNCED,
            remoteTimestamp = remoteTimestamp,
            errorMessage = null,
            retryCount = 0
        )
    }

    fun markAsFailed(error: String): SyncStatus {
        return copy(
            status = SyncState.FAILED,
            errorMessage = error,
            retryCount = retryCount + 1,
            lastAttemptTimestamp = System.currentTimeMillis()
        )
    }

    fun markAsPending(): SyncStatus {
        return copy(status = SyncState.PENDING, errorMessage = null)
    }

    fun withConflict(conflictData: ConflictData): SyncStatus {
        return copy(
            status = SyncState.CONFLICT,
            conflictData = conflictData
        )
    }

    fun resolveConflict(resolution: ConflictResolution): SyncStatus {
        return when (resolution) {
            ConflictResolution.USE_LOCAL -> markAsSynced()
            ConflictResolution.USE_REMOTE -> markAsSynced()
            ConflictResolution.MERGE -> markAsSynced()
        }.copy(conflictData = null)
    }

    fun getTimeSinceLastAttempt(): Long? {
        return lastAttemptTimestamp?.let { System.currentTimeMillis() - it }
    }

    fun shouldRetry(): Boolean {
        if (!canRetry()) return false
        val timeSinceLastAttempt = getTimeSinceLastAttempt() ?: return true
        return timeSinceLastAttempt > calculateBackoffDelay()
    }

    private fun calculateBackoffDelay(): Long {
        val baseDelay = 1000L
        val maxDelay = 30000L
        val delay = baseDelay * (1 shl retryCount)
        return delay.coerceAtMost(maxDelay)
    }

    fun toDisplayString(): String {
        return when (status) {
            SyncState.PENDING -> "Pendiente de sincronizar"
            SyncState.SYNCING -> "Sincronizando..."
            SyncState.SYNCED -> "Sincronizado"
            SyncState.FAILED -> "Error: ${errorMessage ?: "Desconocido"}"
            SyncState.CONFLICT -> "Conflicto detectado"
        }
    }

    companion object {
        const val MAX_RETRY_COUNT = 3

        fun createForEntity(entityType: EntityType, entityId: String): SyncStatus {
            return SyncStatus(
                entityType = entityType,
                entityId = entityId
            )
        }
    }
}

enum class SyncState(val displayName: String) {
    PENDING("Pendiente"),
    SYNCING("Sincronizando"),
    SYNCED("Sincronizado"),
    FAILED("Fallido"),
    CONFLICT("Conflicto");

    fun isActive(): Boolean = this == PENDING || this == SYNCING
    fun isTerminal(): Boolean = this == SYNCED || this == FAILED
}

enum class EntityType(val displayName: String, val tableName: String) {
    PRODUCT("Producto", "products"),
    PURCHASE("Compra", "purchases"),
    USER_PREFERENCES("Preferencias", "user_preferences");

    fun getSyncPriority(): Int {
        return when (this) {
            PRODUCT -> 1
            PURCHASE -> 2
            USER_PREFERENCES -> 3
        }
    }
}

data class ConflictData(
    val localVersion: String,
    val remoteVersion: String,
    val localTimestamp: Long,
    val remoteTimestamp: Long,
    val conflictFields: List<String>
) {
    fun getOlderVersion(): VersionInfo {
        return if (localTimestamp < remoteTimestamp) {
            VersionInfo("local", localVersion, localTimestamp)
        } else {
            VersionInfo("remote", remoteVersion, remoteTimestamp)
        }
    }

    fun getNewerVersion(): VersionInfo {
        return if (localTimestamp > remoteTimestamp) {
            VersionInfo("local", localVersion, localTimestamp)
        } else {
            VersionInfo("remote", remoteVersion, remoteTimestamp)
        }
    }

    fun hasFieldConflict(field: String): Boolean {
        return conflictFields.contains(field)
    }
}

data class VersionInfo(
    val source: String,
    val version: String,
    val timestamp: Long
)

enum class ConflictResolution(val displayName: String) {
    USE_LOCAL("Usar versión local"),
    USE_REMOTE("Usar versión remota"),
    MERGE("Combinar versiones")
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/repository/UserPreferencesRepository.kt ===
package com.example.offlinefirst.domain.repository

import com.example.offlinefirst.domain.model.PaymentMethod
import com.example.offlinefirst.domain.model.ProductCategory
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.ThemeMode
import com.example.offlinefirst.domain.model.UserPreferences
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    fun getUserPreferences(userId: String): Flow<Resource<UserPreferences>>
    
    fun getDefaultPreferences(userId: String): UserPreferences
    
    suspend fun savePreferences(preferences: UserPreferences): Resource<UserPreferences>
    
    suspend fun updateTheme(userId: String, theme: ThemeMode): Resource<UserPreferences>
    
    suspend fun updateNotifications(
        userId: String,
        enabled: Boolean,
        priceDrops: Boolean? = null,
        newProducts: Boolean? = null
    ): Resource<UserPreferences>
    
    suspend fun updateAutoSync(
        userId: String,
        enabled: Boolean,
        wifiOnly: Boolean? = null
    ): Resource<UserPreferences>
    
    suspend fun updateFavoriteCategories(
        userId: String,
        categories: List<ProductCategory>
    ): Resource<UserPreferences>
    
    suspend fun updatePreferredPaymentMethod(
        userId: String,
        paymentMethod: PaymentMethod?
    ): Resource<UserPreferences>
    
    suspend fun updateSyncRetention(
        userId: String,
        retentionDays: Int,
        maxProducts: Int
    ): Resource<UserPreferences>
    
    suspend fun updateLastSyncTimestamp(userId: String, timestamp: Long): Resource<UserPreferences>
    
    suspend fun deletePreferences(userId: String): Resource<Unit>
    
    fun getSyncStatus(): Flow<Resource<SyncStatus>>
    
    suspend fun syncPreferences(): Resource<UserPreferences>
    
    fun observePreferences(userId: String): Flow<UserPreferences?>
    
    suspend fun hasPreferences(userId: String): Boolean
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/usecase/ProductUseCases.kt ===
package com.example.offlinefirst.domain.usecase

import com.example.offlinefirst.domain.model.Product
import com.example.offlinefirst.domain.repository.ProductRepository
import com.example.offlinefirst.util.Error
import com.example.offlinefirst.util.Loading
import com.example.offlinefirst.util.Resource
import com.example.offlinefirst.util.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(forceRefresh: Boolean = false): Flow<Resource<List<Product>>> = flow {
        emit(Loading)
        try {
            val cachedProducts = productRepository.getProducts().first()
            if (cachedProducts.isNotEmpty() && !forceRefresh) {
                emit(Success(cachedProducts))
            }
            if (forceRefresh || cachedProducts.isEmpty()) {
                val remoteProducts = productRepository.syncProducts()
                emit(Success(remoteProducts))
            } else {
                emit(Success(cachedProducts))
            }
        } catch (e: Exception) {
            val cachedProducts = productRepository.getProducts().first()
            if (cachedProducts.isNotEmpty()) {
                emit(Success(cachedProducts))
            } else {
                emit(Error(e.message ?: "Error al obtener productos", e))
            }
        }
    }

    fun getProductsFromCache(): Flow<List<Product>> = productRepository.getProducts()

    fun observeProducts(): Flow<List<Product>> = productRepository.getProducts()
}

class GetProductByIdUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(productId: String): Resource<Product> {
        return try {
            val product = productRepository.getProductById(productId)
            if (product != null) {
                Success(product)
            } else {
                Error("Producto no encontrado")
            }
        } catch (e: Exception) {
            Error(e.message ?: "Error al obtener producto", e)
        }
    }
}

class SaveProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(product: Product): Resource<Product> {
        return try {
            if (!product.isValid()) {
                return Error("Producto inválido")
            }
            val savedProduct = productRepository.saveProduct(product)
            Success(savedProduct)
        } catch (e: Exception) {
            Error(e.message ?: "Error al guardar producto", e)
        }
    }

    suspend fun saveProducts(products: List<Product>): Resource<Int> {
        return try {
            var savedCount = 0
            products.forEach { product ->
                if (product.isValid()) {
                    productRepository.saveProduct(product)
                    savedCount++
                }
            }
            Success(savedCount)
        } catch (e: Exception) {
            Error(e.message ?: "Error al guardar productos", e)
        }
    }
}

class DeleteProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(productId: String): Resource<Unit> {
        return try {
            productRepository.deleteProduct(productId)
            Success(Unit)
        } catch (e: Exception) {
            Error(e.message ?: "Error al eliminar producto", e)
        }
    }
}

class SearchProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(query: String): Resource<List<Product>> {
        return try {
            val products = productRepository.searchProducts(query)
            Success(products)
        } catch (e: Exception) {
            Error(e.message ?: "Error al buscar productos", e)
        }
    }

    fun searchProductsByCategory(category: String): Flow<List<Product>> {
        return productRepository.getProductsByCategory(category)
    }
}

class SyncProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): Resource<List<Product>> {
        return try {
            val products = productRepository.syncProducts()
            Success(products)
        } catch (e: Exception) {
            Error(e.message ?: "Error al sincronizar productos", e)
        }
    }

    fun observeSyncStatus(): Flow<Boolean> = productRepository.getPendingSyncProducts().let { flow ->
        flow.map { it.isNotEmpty() }
    }
}


package com.example.offlinefirst.domain.usecase

import com.example.offlinefirst.domain.model.PurchaseHistory
import com.example.offlinefirst.domain.model.PurchaseStatus
import com.example.offlinefirst.domain.repository.PurchaseRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPurchaseHistoryUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    operator fun invoke(): Flow<Resource<List<PurchaseHistory>>> = flow {
        emit(Resource.Loading())
        try {
            val purchases = purchaseRepository.getPurchases().first()
            emit(Resource.Success(purchases))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al obtener historial de compras", e))
        }
    }

    fun observePurchases(): Flow<List<PurchaseHistory>> = purchaseRepository.getPurchases()

    suspend fun getActivePurchases(): List<PurchaseHistory> {
        return purchaseRepository.getPurchases().first()
            .filter { it.isActive() }
    }

    suspend fun getPurchasesByStatus(status: PurchaseStatus): List<PurchaseHistory> {
        return purchaseRepository.getPurchases().first()
            .filter { it.status == status }
    }
}

class GetPurchaseByIdUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(purchaseId: String): Resource<PurchaseHistory> {
        return try {
            val purchase = purchaseRepository.getPurchaseById(purchaseId)
            if (purchase != null) {
                Resource.Success(purchase)
            } else {
                Resource.Error("Compra no encontrada")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al obtener compra", e)
        }
    }
}

class CreatePurchaseUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(purchase: PurchaseHistory): Resource<PurchaseHistory> {
        return try {
            if (!purchase.isValid()) {
                return Resource.Error("Datos de compra inválidos")
            }
            val savedPurchase = purchaseRepository.savePurchase(purchase)
            Resource.Success(savedPurchase)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al crear compra", e)
        }
    }
}

class CancelPurchaseUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(purchaseId: String): Resource<PurchaseHistory> {
        return try {
            val purchase = purchaseRepository.getPurchaseById(purchaseId)
                ?: return Resource.Error("Compra no encontrada")

            if (!purchase.canBeCancelled()) {
                return Resource.Error("Esta compra no puede ser cancelada")
            }

            val cancelledPurchase = purchase.withStatus(PurchaseStatus.CANCELLED)
            val updated = purchaseRepository.savePurchase(cancelledPurchase)
            Resource.Success(updated)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al cancelar compra", e)
        }
    }
}

class UpdatePurchaseStatusUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(
        purchaseId: String,
        newStatus: PurchaseStatus,
        trackingNumber: String? = null,
        deliveryDate: Long? = null
    ): Resource<PurchaseHistory> {
        return try {
            val purchase = purchaseRepository.getPurchaseById(purchaseId)
                ?: return Resource.Error("Compra no encontrada")

            val updatedPurchase = purchase
                .withStatus(newStatus)
                .withShippingInfo(trackingNumber, deliveryDate)

            val saved = purchaseRepository.savePurchase(updatedPurchase)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar estado", e)
        }
    }
}

class SyncPurchasesUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(): Resource<List<PurchaseHistory>> {
        return try {
            val purchases = purchaseRepository.syncPurchases()
            Resource.Success(purchases)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al sincronizar compras", e)
        }
    }

    fun getPendingSyncPurchases(): Flow<List<PurchaseHistory>> {
        return purchaseRepository.getPendingSyncPurchases()
    }
}

class GetPurchaseStatisticsUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(): Resource<PurchaseStatistics> {
        return try {
            val purchases = purchaseRepository.getPurchases().first()
            val totalPurchases = purchases.size
            val totalSpent = purchases.sumOf { it.totalPrice.toDouble() }
            val activePurchases = purchases.count { it.isActive() }
            val cancelledPurchases = purchases.count { it.status == PurchaseStatus.CANCELLED }

            Resource.Success(
                PurchaseStatistics(
                    totalPurchases = totalPurchases,
                    totalSpent = totalSpent,
                    activePurchases = activePurchases,
                    cancelledPurchases = cancelledPurchases
                )
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al calcular estadísticas", e)
        }
    }
}

data class PurchaseStatistics(
    val totalPurchases: Int,
    val totalSpent: Double,
    val activePurchases: Int,
    val cancelledPurchases: Int
)
// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/usecase/UserPreferencesUseCases.kt ===
package com.example.offlinefirst.domain.usecase

import com.example.offlinefirst.domain.model.UserPreferences
import com.example.offlinefirst.domain.repository.UserPreferencesRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserPreferencesUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) {
    operator fun invoke(): Flow<Resource<UserPreferences>> = flow {
        emit(Resource.Loading())
        try {
            val preferences = userPreferencesRepository.getUserPreferences().first()
            emit(Resource.Success(preferences))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al obtener preferencias", e))
        }
    }

    fun observePreferences(): Flow<UserPreferences> = userPreferencesRepository.getUserPreferences()
}

class UpdateUserPreferencesUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) {
    suspend operator fun invoke(preferences: UserPreferences): Resource<UserPreferences> {
        return try {
            val updated = userPreferencesRepository.savePreferences(preferences)
            Resource.Success(updated)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al guardar preferencias", e)
        }
    }

    suspend fun updateTheme(theme: String): Resource<UserPreferences> {
        return try {
            val current = userPreferencesRepository.getUserPreferences().first()
            val updated = current.copy(theme = theme)
            val saved = userPreferencesRepository.savePreferences(updated)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar tema", e)
        }
    }

    suspend fun updateLanguage(language: String): Resource<UserPreferences> {
        return try {
            val current = userPreferencesRepository.getUserPreferences().first()
            val updated = current.copy(language = language)
            val saved = userPreferencesRepository.savePreferences(updated)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar idioma", e)
        }
    }

    suspend fun updateNotificationsEnabled(enabled: Boolean): Resource<UserPreferences> {
        return try {
            val current = userPreferencesRepository.getUserPreferences().first()
            val updated = current.copy(notificationsEnabled = enabled)
            val saved = userPreferencesRepository.savePreferences(updated)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar notificaciones", e)
        }
    }

    suspend fun updateAutoSync(enabled: Boolean): Resource<UserPreferences> {
        return try {
            val current = userPreferencesRepository.getUserPreferences().first()
            val updated = current.copy(autoSyncEnabled = enabled)
            val saved = userPreferencesRepository.savePreferences(updated)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar sincronización automática", e)
        }
    }

    suspend fun updateSyncInterval(intervalMinutes: Int): Resource<UserPreferences> {
        return try {
            if (intervalMinutes < 15 || intervalMinutes > 1440) {
                return Resource.Error("Intervalo de sincronización inválido")
            }
            val current = userPreferencesRepository.getUserPreferences().first()
            val updated = current.copy(syncIntervalMinutes = intervalMinutes)
            val saved = userPreferencesRepository.savePreferences(updated)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar intervalo", e)
        }
    }

    suspend fun updateDataSavingMode(enabled: Boolean): Resource<UserPreferences> {
        return try {
            val current = userPreferencesRepository.getUserPreferences().first()
            val updated = current.copy(dataSavingMode = enabled)
            val saved = userPreferencesRepository.savePreferences(updated)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar modo ahorro de datos", e)
        }
    }
}

class ResetUserPreferencesUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) {
    suspend operator fun invoke(): Resource<UserPreferences> {
        return try {
            val defaultPreferences = UserPreferences()
            val saved = userPreferencesRepository.savePreferences(defaultPreferences)
            Resource.Success(saved)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al restaurar preferencias", e)
        }
    }
}
// === ARCHIVO: app/src/main/java/com/example/offlinefirst/domain/usecase/SyncDataUseCase.kt ===
package com.example.offlinefirst.domain.usecase

import com.example.offlinefirst.data.sync.NetworkConnectivityManager
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.repository.ProductRepository
import com.example.offlinefirst.domain.repository.PurchaseRepository
import com.example.offlinefirst.domain.repository.UserPreferencesRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SyncDataUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val purchaseRepository: PurchaseRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val networkConnectivityManager: NetworkConnectivityManager
) {
    suspend operator fun invoke(
        syncProducts: Boolean = true,
        syncPurchases: Boolean = true
    ): Resource<SyncResult> = flow {
        emit(Resource.Loading())

        if (!networkConnectivityManager.isCurrentlyConnected()) {
            emit(Resource.Error("No hay conexión a Internet"))
            return@flow
        }

        var productsSynced = 0
        var purchasesSynced = 0
        var errors = mutableListOf<String>()

        try {
            if (syncProducts) {
                try {
                    productRepository.syncProducts()
                    productsSynced++
                } catch (e: Exception) {
                    errors.add("Error sincronizando productos: ${e.message}")
                }
            }

            if (syncPurchases) {
                try {
                    purchaseRepository.syncPurchases()
                    purchasesSynced++
                } catch (e: Exception) {
                    errors.add("Error sincronizando compras: ${e.message}")
                }
            }

            val result = SyncResult(
                productsSynced = productsSynced > 0,
                purchasesSynced = purchasesSynced > 0,
                errors = errors,
                timestamp = System.currentTimeMillis()
            )

            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error en sincronización", e))
        }
    }

    fun observeNetworkStatus(): Flow<Boolean> = networkConnectivityManager.observeConnectivity()

    suspend fun checkPendingSync(): PendingSyncInfo {
        val pendingProducts = productRepository.getProducts().first()
            .count { /* lógica para determinar si hay productos pendientes */ false }

        val pendingPurchases = purchaseRepository.getPendingSyncPurchases().first().size

        return PendingSyncInfo(
            hasPendingProducts = pendingProducts > 0,
            hasPendingPurchases = pendingPurchases > 0,
            totalPendingItems = pendingProducts + pendingPurchases
        )
    }
}

data class SyncResult(
    val productsSynced: Boolean,
    val purchasesSynced: Boolean,
    val errors: List<String>,
    val timestamp: Long
) {
    fun isSuccess(): Boolean = productsSynced || purchasesSynced
    fun hasErrors(): Boolean = errors.isNotEmpty()
}

data class PendingSyncInfo(
    val hasPendingProducts: Boolean,
    val hasPendingPurchases: Boolean,
    val totalPendingItems: Int
)

class GetSyncStatusUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(): Resource<SyncStatus> {
        return try {
            val products = productRepository.getProducts().first()
            val purchases = purchaseRepository.getPurchases().first()

            val lastSyncTime = System.currentTimeMillis()
            val pendingCount = purchases.count { /* pending sync */ false }

            val status = SyncStatus(
                lastSyncTime = lastSyncTime,
                pendingChanges = pendingCount,
                isSyncing = false,
                lastError = null
            )

            Resource.Success(status)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al obtener estado de sincronización", e)
        }
    }

    fun observeSyncStatus(): Flow<SyncStatus> = flow {
        val products = productRepository.getProducts().first()
        val purchases = purchaseRepository.getPurchases().first()

        emit(
            SyncStatus(
                lastSyncTime = System.currentTimeMillis(),
                pendingChanges = 0,
                isSyncing = false,
                lastError = null
            )
        )
    }
}


// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/entity/SyncMetadataEntity.kt ===
package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.SyncState

@Entity(
    tableName = "sync_metadata",
    indices = [
        Index(value = ["entity_type"]),
        Index(value = ["entity_id"]),
        Index(value = ["status"]),
        Index(value = ["last_attempt_at"])
    ]
)
data class SyncMetadataEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "entity_type")
    val entityType: String,

    @ColumnInfo(name = "entity_id")
    val entityId: String,

    @ColumnInfo(name = "operation")
    val operation: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "local_version")
    val localVersion: Int,

    @ColumnInfo(name = "server_version")
    val serverVersion: Int?,

    @ColumnInfo(name = "local_data")
    val localData: String?,

    @ColumnInfo(name = "server_data")
    val serverData: String?,

    @ColumnInfo(name = "conflict_resolution")
    val conflictResolution: String?,

    @ColumnInfo(name = "error_message")
    val errorMessage: String?,

    @ColumnInfo(name = "retry_count")
    val retryCount: Int,

    @ColumnInfo(name = "max_retries")
    val maxRetries: Int,

    @ColumnInfo(name = "created_at")
    val createdAt: Long,

    @ColumnInfo(name = "last_attempt_at")
    val lastAttemptAt: Long?,

    @ColumnInfo(name = "next_retry_at")
    val nextRetryAt: Long?,

    @ColumnInfo(name = "completed_at")
    val completedAt: Long?
) {
    fun toDomain(): SyncStatus {
        return SyncStatus(
            entityType = entityType,
            entityId = entityId,
            status = try {
                SyncState.valueOf(status)
            } catch (e: Exception) {
                SyncState.PENDING
            },
            operation = operation,
            localVersion = localVersion,
            serverVersion = serverVersion,
            lastAttemptAt = lastAttemptAt,
            retryCount = retryCount,
            errorMessage = errorMessage
        )
    }

    fun canRetry(): Boolean {
        return retryCount < maxRetries && 
               (nextRetryAt == null || System.currentTimeMillis() >= nextRetryAt)
    }

    fun shouldRetry(): Boolean {
        return status == SyncState.FAILED.name && canRetry()
    }

    companion object {
        fun createForNewEntity(
            entityType: String,
            entityId: String,
            operation: String,
            localData: String?
        ): SyncMetadataEntity {
            return SyncMetadataEntity(
                id = "${entityType}_${entityId}_${System.currentTimeMillis()}",
                entityType = entityType,
                entityId = entityId,
                operation = operation,
                status = SyncState.PENDING.name,
                localVersion = 1,
                serverVersion = null,
                localData = localData,
                serverData = null,
                conflictResolution = null,
                errorMessage = null,
                retryCount = 0,
                maxRetries = 3,
                createdAt = System.currentTimeMillis(),
                lastAttemptAt = null,
                nextRetryAt = null,
                completedAt = null
            )
        }

        fun createForUpdate(
            entityType: String,
            entityId: String,
            localVersion: Int,
            localData: String?
        ): SyncMetadataEntity {
            return SyncMetadataEntity(
                id = "${entityType}_${entityId}_update_${System.currentTimeMillis()}",
                entityType = entityType,
                entityId = entityId,
                operation = "UPDATE",
                status = SyncState.PENDING.name,
                localVersion = localVersion,
                serverVersion = null,
                localData = localData,
                serverData = null,
                conflictResolution = null,
                errorMessage = null,
                retryCount = 0,
                maxRetries = 3,
                createdAt = System.currentTimeMillis(),
                lastAttemptAt = null,
                nextRetryAt = null,
                completedAt = null
            )
        }

        const val ENTITY_TYPE_PRODUCT = "PRODUCT"
        const val ENTITY_TYPE_PURCHASE = "PURCHASE"
        const val ENTITY_TYPE_USER_PREFERENCES = "USER_PREFERENCES"

        const val OPERATION_CREATE = "CREATE"
        const val OPERATION_UPDATE = "UPDATE"
        const val OPERATION_DELETE = "DELETE"

        const val STATUS_PENDING = "PENDING"
        const val STATUS_IN_PROGRESS = "IN_PROGRESS"
        const val STATUS_COMPLETED = "COMPLETED"
        const val STATUS_FAILED = "FAILED"
        const val STATUS_CONFLICT = "CONFLICT"

        const val CONFLICT_RESOLUTION_LOCAL = "LOCAL_WINS"
        const val CONFLICT_RESOLUTION_SERVER = "SERVER_WINS"
        const val CONFLICT_RESOLUTION_MERGE = "MERGE"
    }
}

@Entity(
    tableName = "sync_queue",
    indices = [
        Index(value = ["entity_type", "entity_id"], unique = true),
        Index(value = ["priority"]),
        Index(value = ["scheduled_at"])
    ]
)
data class SyncQueueEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "entity_type")
    val entityType: String,

    @ColumnInfo(name = "entity_id")
    val entityId: String,

    @ColumnInfo(name = "operation")
    val operation: String,

    @ColumnInfo(name = "payload")
    val payload: String,

    @ColumnInfo(name = "priority")
    val priority: Int,

    @ColumnInfo(name = "scheduled_at")
    val scheduledAt: Long,

    @ColumnInfo(name = "attempted_at")
    val attemptedAt: Long?,

    @ColumnInfo(name = "attempt_count")
    val attemptCount: Int,

    @ColumnInfo(name = "max_attempts")
    val maxAttempts: Int,

    @ColumnInfo(name = "last_error")
    val lastError: String?,

    @ColumnInfo(name = "created_at")
    val createdAt: Long
)

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/dao/PurchaseHistoryDao.kt ===
package com.example.offlinefirst.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.offlinefirst.data.local.entity.PurchaseHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseHistoryDao {
    fun getAllPurchases(): Flow<List<PurchaseHistoryEntity>>
    fun getPurchaseById(purchaseId: String): Flow<PurchaseHistoryEntity?>
    suspend fun getPurchaseByIdSync(purchaseId: String): PurchaseHistoryEntity?
    fun getPurchasesByStatus(status: String): Flow<List<PurchaseHistoryEntity>>
    fun getPurchasesByProduct(productId: String): Flow<List<PurchaseHistoryEntity>>
    fun getPurchasesByDateRange(startDate: Long, endDate: Long): Flow<List<PurchaseHistoryEntity>>
    suspend fun getPurchasesToSync(): List<PurchaseHistoryEntity>
    suspend fun getPurchasesUpdatedSince(lastSyncTimestamp: Long): List<PurchaseHistoryEntity>
    fun getActivePurchasesSince(sinceTimestamp: Long): Flow<List<PurchaseHistoryEntity>>
    suspend fun insertPurchase(purchase: PurchaseHistoryEntity)
    suspend fun insertPurchases(purchases: List<PurchaseHistoryEntity>)
    suspend fun updatePurchase(purchase: PurchaseHistoryEntity)
    suspend fun deletePurchase(purchase: PurchaseHistoryEntity)
    suspend fun deletePurchaseById(purchaseId: String)
    suspend fun deleteAllPurchases()
    suspend fun updateSyncStatus(purchaseId: String, syncStatus: String)
    suspend fun updateStatus(purchaseId: String, status: String, updatedAt: Long)
    suspend fun updateShippingInfo(purchaseId: String, tracking: String?, deliveryDate: Long?, updatedAt: Long)
    suspend fun updatePurchaseStatus(purchaseId: String, status: String)
    suspend fun markPurchaseForDeletion(purchaseId: String)
    fun getPendingPurchases(): Flow<List<PurchaseHistoryEntity>>
    suspend fun deletePendingPurchase(purchaseId: String)
    @Query("SELECT COUNT(*) FROM purchase_history")
    fun getPurchaseCount(): Flow<Int>
    @Query("SELECT SUM(totalPrice) FROM purchase_history WHERE status = 'COMPLETED' AND purchaseDate BETWEEN :startDate AND :endDate")
    fun getTotalSpentInRange(startDate: Long, endDate: Long): Flow<Double?>
    @Query("SELECT SUM(quantity) FROM purchase_history WHERE productId = :productId")
    fun getTotalQuantityPurchasedForProduct(productId: String): Flow<Int?>
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/dao/UserPreferencesDao.kt ===
package com.example.offlinefirst.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.offlinefirst.data.local.entity.UserPreferencesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserPreferencesDao {
    fun getUserPreferences(): Flow<UserPreferencesEntity?>
    suspend fun getUserPreferencesSync(): UserPreferencesEntity?
    suspend fun getPreferences(): UserPreferencesEntity?
    suspend fun insertPreferences(preferences: UserPreferencesEntity)
    suspend fun insertOrUpdatePreferences(preferences: UserPreferencesEntity)
    suspend fun updatePreferences(preferences: UserPreferencesEntity)
    suspend fun updateSyncStatus(syncStatus: String)
    suspend fun markAsSynced(timestamp: Long)
    suspend fun deletePreferences()
    suspend fun clearAllPreferences()
    suspend fun getSyncStatus(): String?
    suspend fun getLastSyncTimestamp(): Long?
    suspend fun updateThemeMode(themeMode: String, updatedAt: Long)
    suspend fun updateNotificationsEnabled(enabled: Boolean, updatedAt: Long)
    suspend fun updateAutoSyncEnabled(enabled: Boolean, updatedAt: Long)
    suspend fun updateSyncOnWifiOnly(wifiOnly: Boolean, updatedAt: Long)
    suspend fun updateDefaultPaymentMethod(paymentMethod: String, updatedAt: Long)
    suspend fun updateLanguage(language: String, updatedAt: Long)
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/entity/PurchaseHistoryEntity.kt ===
package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.offlinefirst.domain.model.PurchaseHistory
import com.example.offlinefirst.domain.model.PaymentMethod
import com.example.offlinefirst.domain.model.PurchaseStatus
import java.math.BigDecimal

@Entity(tableName = "purchase_history")
data class PurchaseHistoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "product_id")
    val productId: String,

    @ColumnInfo(name = "product_name")
    val productName: String,

    @ColumnInfo(name = "quantity")
    val quantity: Int,

    @ColumnInfo(name = "unit_price")
    val unitPrice: BigDecimal,

    @ColumnInfo(name = "total_price")
    val totalPrice: BigDecimal,

    @ColumnInfo(name = "payment_method")
    val paymentMethod: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "purchase_date")
    val purchaseDate: Long,

    @ColumnInfo(name = "shipping_address")
    val shippingAddress: String?,

    @ColumnInfo(name = "shipping_city")
    val shippingCity: String?,

    @ColumnInfo(name = "shipping_postal_code")
    val shippingPostalCode: String?,

    @ColumnInfo(name = "tracking_number")
    val trackingNumber: String?,

    @ColumnInfo(name = "delivery_date")
    val deliveryDate: Long?,

    @ColumnInfo(name = "last_updated")
    val lastUpdated: Long,

    @ColumnInfo(name = "sync_status")
    val syncStatus: String
) {
    fun toDomain(): PurchaseHistory {
        return PurchaseHistory(
            id = id,
            productId = productId,
            productName = productName,
            quantity = quantity,
            unitPrice = unitPrice,
            totalPrice = totalPrice,
            paymentMethod = try { PaymentMethod.valueOf(paymentMethod) } catch (e: Exception) { PaymentMethod.CREDIT_CARD },
            status = try { PurchaseStatus.valueOf(status) } catch (e: Exception) { PurchaseStatus.PENDING },
            purchaseDate = purchaseDate,
            shippingAddress = shippingAddress,
            shippingCity = shippingCity,
            shippingPostalCode = shippingPostalCode,
            trackingNumber = trackingNumber,
            deliveryDate = deliveryDate
        )
    }
}

data class PurchaseHistoryWithProductEntity(
    @ColumnInfo(name = "purchase")
    val purchase: PurchaseHistoryEntity,
    @ColumnInfo(name = "product")
    val product: ProductEntity?
)

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/local/entity/UserPreferencesEntity.kt ===
package com.example.offlinefirst.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.offlinefirst.domain.model.UserPreferences

@Entity(tableName = "user_preferences")
data class UserPreferencesEntity(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val userId: String,

    @ColumnInfo(name = "user_name")
    val userName: String,

    @ColumnInfo(name = "user_email")
    val userEmail: String,

    @ColumnInfo(name = "notifications_enabled")
    val notificationsEnabled: Boolean,

    @ColumnInfo(name = "dark_mode_enabled")
    val darkModeEnabled: Boolean,

    @ColumnInfo(name = "auto_sync_enabled")
    val autoSyncEnabled: Boolean,

    @ColumnInfo(name = "sync_on_wifi_only")
    val syncOnWifiOnly: Boolean,

    @ColumnInfo(name = "last_sync_time")
    val lastSyncTime: Long,

    @ColumnInfo(name = "selected_currency")
    val selectedCurrency: String,

    @ColumnInfo(name = "selected_language")
    val selectedLanguage: String
) {
    fun toDomain(): UserPreferences {
        return UserPreferences(
            userId = userId,
            userName = userName,
            userEmail = userEmail,
            notificationsEnabled = notificationsEnabled,
            darkModeEnabled = darkModeEnabled,
            autoSyncEnabled = autoSyncEnabled,
            syncOnWifiOnly = syncOnWifiOnly,
            lastSyncTime = lastSyncTime,
            selectedCurrency = selectedCurrency,
            selectedLanguage = selectedLanguage
        )
    }
}

data class ShippingAddressJson(
    val street: String,
    val city: String,
    val state: String,
    val postalCode: String,
    val country: String
) {
    fun toDomain(): UserPreferences.ShippingAddress {
        return UserPreferences.ShippingAddress(
            street = street,
            city = city,
            state = state,
            postalCode = postalCode,
            country = country
        )
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/repository/PurchaseRepositoryImpl.kt ===
package com.example.offlinefirst.data.repository

import com.example.offlinefirst.data.local.dao.PurchaseHistoryDao
import com.example.offlinefirst.data.local.entity.PurchaseHistoryEntity
import com.example.offlinefirst.data.remote.api.OfflineFirstApiService
import com.example.offlinefirst.data.remote.dto.CreatePurchaseRequest
import com.example.offlinefirst.data.remote.dto.PurchaseDto
import com.example.offlinefirst.domain.model.PurchaseHistory
import com.example.offlinefirst.domain.model.PurchaseStatus
import com.example.offlinefirst.domain.model.PaymentMethod
import com.example.offlinefirst.domain.model.SyncStatus
import com.example.offlinefirst.domain.model.SyncState
import com.example.offlinefirst.domain.repository.PurchaseRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PurchaseRepositoryImpl @Inject constructor(
    private val purchaseDao: PurchaseHistoryDao,
    private val apiService: OfflineFirstApiService
) : PurchaseRepository {

    override fun getPurchaseHistory(): Flow<Resource<List<PurchaseHistory>>> = flow {
        emit(Resource.Loading())
        
        try {
            val localPurchases = purchaseDao.getAllPurchases().first()
            
            if (localPurchases.isEmpty()) {
                val response = apiService.getPurchases()
                if (response.isSuccessful && response.body() != null) {
                    val remotePurchases = response.body()!!.purchases
                    val entities = remotePurchases.map { it.toEntity() }
                    purchaseDao.insertPurchases(entities)
                    emit(Resource.Success(entities.map { it.toDomain() }))
                } else {
                    emit(Resource.Error("Error al cargar historial: ${response.message()}"))
                }
            } else {
                try {
                    val response = apiService.getPurchases()
                    if (response.isSuccessful && response.body() != null) {
                        val remotePurchases = response.body()!!.purchases
                        val mergedPurchases = mergePurchases(localPurchases, remotePurchases)
                        purchaseDao.insertPurchases(mergedPurchases)
                        emit(Resource.Success(mergedPurchases.map { it.toDomain() }))
                    } else {
                        emit(Resource.Success(localPurchases.map { it.toDomain() }))
                    }
                } catch (e: Exception) {
                    emit(Resource.Success(localPurchases.map { it.toDomain() }))
                }
            }
        } catch (e: Exception) {
            val localPurchases = purchaseDao.getAllPurchases().first()
            if (localPurchases.isNotEmpty()) {
                emit(Resource.Success(localPurchases.map { it.toDomain() }))
            } else {
                emit(Resource.Error("Error de conexión: ${e.localizedMessage}"))
            }
        }
    }

    override fun getPurchaseById(id: String): Flow<Resource<PurchaseHistory>> = flow {
        emit(Resource.Loading())
        
        val localPurchase = purchaseDao.getPurchaseById(id).first()
        if (localPurchase != null) {
            emit(Resource.Success(localPurchase.toDomain()))
            return@flow
        }
        
        try {
            val response = apiService.getPurchaseById(id)
            if (response.isSuccessful && response.body() != null) {
                val purchase = response.body()!!
                purchaseDao.insertPurchase(purchase.toEntity())
                emit(Resource.Success(purchase.toDomain()))
            } else {
                emit(Resource.Error("Compra no encontrada"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error de conexión: ${e.localizedMessage}"))
        }
    }

    override suspend fun createPurchase(purchase: PurchaseHistory): Resource<PurchaseHistory> {
        return try {
            val request = CreatePurchaseRequest(
                productId = purchase.productId,
                quantity = purchase.quantity,
                unitPrice = purchase.unitPrice.toDouble(),
                totalPrice = purchase.totalPrice.toDouble(),
                paymentMethod = purchase.paymentMethod.name,
                shippingAddress = purchase.shippingAddress,
                shippingCity = purchase.shippingCity,
                shippingPostalCode = purchase.shippingPostalCode
            )
            
            val response = apiService.createPurchase(request)
            if (response.isSuccessful && response.body() != null) {
                val createdPurchase = response.body()!!
                purchaseDao.insertPurchase(createdPurchase.toEntity())
                Resource.Success(createdPurchase.toDomain())
            } else {
                val pendingEntity = purchase.toPendingEntity()
                purchaseDao.insertPurchase(pendingEntity)
                Resource.Success(purchase)
            }
        } catch (e: Exception) {
            val pendingEntity = purchase.toPendingEntity()
            purchaseDao.insertPurchase(pendingEntity)
            Resource.Success(purchase)
        }
    }

    override suspend fun cancelPurchase(id: String): Resource<Unit> {
        return try {
            val response = apiService.cancelPurchase(id)
            if (response.isSuccessful) {
                purchaseDao.updatePurchaseStatus(id, PurchaseStatus.CANCELLED.name)
                Resource.Success(Unit)
            } else {
                purchaseDao.markPurchaseForDeletion(id)
                Resource.Success(Unit)
            }
        } catch (e: Exception) {
            purchaseDao.markPurchaseForDeletion(id)
            Resource.Success(Unit)
        }
    }

    override fun getActivePurchases(): Flow<Resource<List<PurchaseHistory>>> = flow {
        emit(Resource.Loading())
        
        val activePurchases = purchaseDao.getPurchasesByStatus(
            listOf(PurchaseStatus.PENDING.name, PurchaseStatus.CONFIRMED.name, PurchaseStatus.SHIPPED.name)
        ).first()
        
        emit(Resource.Success(activePurchases.map { it.toDomain() }))
    }

    override fun getPendingPurchases(): Flow<List<PurchaseHistory>> {
        return purchaseDao.getPendingPurchases().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun syncPendingPurchases(): Resource<Int> {
        val pendingPurchases = purchaseDao.getPendingPurchases().first()
        var syncedCount = 0
        
        for (purchase in pendingPurchases) {
            try {
                val request = CreatePurchaseRequest(
                    productId = purchase.productId,
                    quantity = purchase.quantity,
                    unitPrice = purchase.unitPrice.toDouble(),
                    totalPrice = purchase.totalPrice.toDouble(),
                    paymentMethod = purchase.paymentMethod,
                    shippingAddress = purchase.shippingAddress,
                    shippingCity = purchase.shippingCity,
                    shippingPostalCode = purchase.shippingPostalCode
                )
                val response = apiService.createPurchase(request)
                if (response.isSuccessful) {
                    purchaseDao.deletePendingPurchase(purchase.id)
                    syncedCount++
                }
            } catch (e: Exception) {
                // Continuar con el siguiente
            }
        }
        
        return Resource.Success(syncedCount)
    }

    private fun mergePurchases(
        local: List<PurchaseHistoryEntity>,
        remote: List<PurchaseHistoryEntity>
    ): List<PurchaseHistoryEntity> {
        val merged = mutableMapOf<String, PurchaseHistoryEntity>()
        
        local.forEach { merged[it.id] = it }
        remote.forEach { remotePurchase ->
            val localPurchase = merged[remotePurchase.id]
            if (localPurchase == null) {
                merged[remotePurchase.id] = remotePurchase
            } else if (remotePurchase.lastUpdated > localPurchase.lastUpdated) {
                merged[remotePurchase.id] = remotePurchase
            }
        }
        
        return merged.values.toList().sortedByDescending { it.purchaseDate }
    }

    private fun PurchaseDto.toEntity(): PurchaseHistoryEntity {
        return PurchaseHistoryEntity(
            id = id,
            productId = productId,
            productName = productName,
            quantity = quantity,
            unitPrice = BigDecimal(unitPrice.toString()),
            totalPrice = BigDecimal(totalPrice.toString()),
            paymentMethod = paymentMethod,
            status = parseStatus(status).name,
            purchaseDate = purchaseDate,
            shippingAddress = shippingAddress,
            shippingCity = shippingCity,
            shippingPostalCode = shippingPostalCode,
            trackingNumber = trackingNumber,
            deliveryDate = deliveryDate,
            lastUpdated = System.currentTimeMillis(),
            syncStatus = SyncState.SYNCED.name
        )
    }

    private fun PurchaseHistoryEntity.toDomain(): PurchaseHistory {
        return PurchaseHistory(
            id = id,
            productId = productId,
            productName = productName,
            quantity = quantity,
            unitPrice = unitPrice,
            totalPrice = totalPrice,
            paymentMethod = try { PaymentMethod.valueOf(paymentMethod) } catch (e: Exception) { PaymentMethod.CREDIT_CARD },
            status = try { PurchaseStatus.valueOf(status) } catch (e: Exception) { PurchaseStatus.PENDING },
            purchaseDate = purchaseDate,
            shippingAddress = shippingAddress,
            shippingCity = shippingCity,
            shippingPostalCode = shippingPostalCode,
            trackingNumber = trackingNumber,
            deliveryDate = deliveryDate
        )
    }

    private fun PurchaseHistory.toEntity(): PurchaseHistoryEntity {
        return PurchaseHistoryEntity(
            id = id,
            productId = productId,
            productName = productName,
            quantity = quantity,
            unitPrice = unitPrice,
            totalPrice = totalPrice,
            paymentMethod = paymentMethod.name,
            status = status.name,
            purchaseDate = purchaseDate,
            shippingAddress = shippingAddress,
            shippingCity = shippingCity,
            shippingPostalCode = shippingPostalCode,
            trackingNumber = trackingNumber,
            deliveryDate = deliveryDate,
            lastUpdated = System.currentTimeMillis(),
            syncStatus = SyncState.SYNCED.name
        )
    }

    private fun PurchaseHistory.toPendingEntity(): PurchaseHistoryEntity {
        return PurchaseHistoryEntity(
            id = id,
            productId = productId,
            productName = productName,
            quantity = quantity,
            unitPrice = unitPrice,
            totalPrice = totalPrice,
            paymentMethod = paymentMethod.name,
            status = status.name,
            purchaseDate = purchaseDate,
            shippingAddress = shippingAddress,
            shippingCity = shippingCity,
            shippingPostalCode = shippingPostalCode,
            trackingNumber = trackingNumber,
            deliveryDate = deliveryDate,
            lastUpdated = System.currentTimeMillis(),
            syncStatus = SyncState.PENDING.name
        )
    }

    private fun PurchaseDto.toDomain(): PurchaseHistory {
        return PurchaseHistory(
            id = id,
            productId = productId,
            productName = productName,
            quantity = quantity,
            unitPrice = BigDecimal(unitPrice.toString()),
            totalPrice = BigDecimal(totalPrice.toString()),
            paymentMethod = try { PaymentMethod.valueOf(paymentMethod) } catch (e: Exception) { PaymentMethod.CREDIT_CARD },
            status = parseStatus(status),
            purchaseDate = purchaseDate,
            shippingAddress = shippingAddress,
            shippingCity = shippingCity,
            shippingPostalCode = shippingPostalCode,
            trackingNumber = trackingNumber,
            deliveryDate = deliveryDate
        )
    }

    private fun PurchaseDto.parseStatus(status: String): PurchaseStatus {
        return try {
            PurchaseStatus.valueOf(status)
        } catch (e: Exception) {
            PurchaseStatus.PENDING
        }
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/repository/UserPreferencesRepositoryImpl.kt ===
package com.example.offlinefirst.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.offlinefirst.data.local.dao.UserPreferencesDao
import com.example.offlinefirst.data.local.entity.UserPreferencesEntity
import com.example.offlinefirst.domain.model.UserPreferences
import com.example.offlinefirst.domain.repository.UserPreferencesRepository
import com.example.offlinefirst.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val userPreferencesDao: UserPreferencesDao
) : UserPreferencesRepository {

    companion object {
        private val KEY_USER_ID = stringPreferencesKey("user_id")
        private val KEY_USER_NAME = stringPreferencesKey("user_name")
        private val KEY_USER_EMAIL = stringPreferencesKey("user_email")
        private val KEY_NOTIFICATIONS_ENABLED = booleanPreferencesKey("notifications_enabled")
        private val KEY_DARK_MODE = booleanPreferencesKey("dark_mode")
        private val KEY_AUTO_SYNC = booleanPreferencesKey("auto_sync")
        private val KEY_SYNC_ON_WIFI_ONLY = booleanPreferencesKey("sync_on_wifi_only")
        private val KEY_LAST_SYNC_TIME = stringPreferencesKey("last_sync_time")
        private val KEY_SELECTED_CURRENCY = stringPreferencesKey("selected_currency")
        private val KEY_SELECTED_LANGUAGE = stringPreferencesKey("selected_language")
    }

    override fun getUserPreferences(): Flow<Resource<UserPreferences>> = flow {
        emit(Resource.Loading())
        
        try {
            val preferences = dataStore.data.first()
            val userPreferences = UserPreferences(
                userId = preferences[KEY_USER_ID] ?: "",
                userName = preferences[KEY_USER_NAME] ?: "",
                userEmail = preferences[KEY_USER_EMAIL] ?: "",
                notificationsEnabled = preferences[KEY_NOTIFICATIONS_ENABLED] ?: true,
                darkModeEnabled = preferences[KEY_DARK_MODE] ?: false,
                autoSyncEnabled = preferences[KEY_AUTO_SYNC] ?: true,
                syncOnWifiOnly = preferences[KEY_SYNC_ON_WIFI_ONLY] ?: false,
                lastSyncTime = preferences[KEY_LAST_SYNC_TIME]?.toLongOrNull() ?: 0L,
                selectedCurrency = preferences[KEY_SELECTED_CURRENCY] ?: "USD",
                selectedLanguage = preferences[KEY_SELECTED_LANGUAGE] ?: "es"
            )
            
            emit(Resource.Success(userPreferences))
        } catch (e: Exception) {
            emit(Resource.Error("Error al cargar preferencias: ${e.localizedMessage}"))
        }
    }

    override suspend fun saveUserPreferences(preferences: UserPreferences): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                preferences.userId.let { prefs[KEY_USER_ID] = it }
                preferences.userName.let { prefs[KEY_USER_NAME] = it }
                preferences.userEmail.let { prefs[KEY_USER_EMAIL] = it }
                preferences.notificationsEnabled.let { prefs[KEY_NOTIFICATIONS_ENABLED] = it }
                preferences.darkModeEnabled.let { prefs[KEY_DARK_MODE] = it }
                preferences.autoSyncEnabled.let { prefs[KEY_AUTO_SYNC] = it }
                preferences.syncOnWifiOnly.let { prefs[KEY_SYNC_ON_WIFI_ONLY] = it }
                preferences.lastSyncTime.toString().let { prefs[KEY_LAST_SYNC_TIME] = it }
                preferences.selectedCurrency.let { prefs[KEY_SELECTED_CURRENCY] = it }
                preferences.selectedLanguage.let { prefs[KEY_SELECTED_LANGUAGE] = it }
            }
            
            userPreferencesDao.insertOrUpdatePreferences(preferences.toEntity())
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al guardar preferencias: ${e.localizedMessage}")
        }
    }

    override suspend fun updateDarkMode(enabled: Boolean): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_DARK_MODE] = enabled
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar modo oscuro: ${e.localizedMessage}")
        }
    }

    override suspend fun updateNotifications(enabled: Boolean): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_NOTIFICATIONS_ENABLED] = enabled
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar notificaciones: ${e.localizedMessage}")
        }
    }

    override suspend fun updateAutoSync(enabled: Boolean): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_AUTO_SYNC] = enabled
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar sincronización automática: ${e.localizedMessage}")
        }
    }

    override suspend fun updateSyncOnWifiOnly(enabled: Boolean): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_SYNC_ON_WIFI_ONLY] = enabled
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar sincronización WiFi: ${e.localizedMessage}")
        }
    }

    override suspend fun updateLastSyncTime(timestamp: Long): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_LAST_SYNC_TIME] = timestamp.toString()
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar tiempo de sincronización: ${e.localizedMessage}")
        }
    }

    override suspend fun updateCurrency(currency: String): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_SELECTED_CURRENCY] = currency
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar moneda: ${e.localizedMessage}")
        }
    }

    override suspend fun updateLanguage(language: String): Resource<Unit> {
        return try {
            dataStore.edit { prefs ->
                prefs[KEY_SELECTED_LANGUAGE] = language
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al actualizar idioma: ${e.localizedMessage}")
        }
    }

    override fun observeDarkMode(): Flow<Boolean> {
        return dataStore.data.map { prefs ->
            prefs[KEY_DARK_MODE] ?: false
        }
    }

    override fun observeAutoSync(): Flow<Boolean> {
        return dataStore.data.map { prefs ->
            prefs[KEY_AUTO_SYNC] ?: true
        }
    }

    override suspend fun getCachedPreferences(): UserPreferences? {
        return try {
            val cached = userPreferencesDao.getPreferences()
            cached?.toDomain()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun clearPreferences(): Resource<Unit> {
        return try {
            dataStore.edit { it.clear() }
            userPreferencesDao.clearAllPreferences()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Error al limpiar preferencias: ${e.localizedMessage}")
        }
    }

    private fun UserPreferences.toEntity(): UserPreferencesEntity {
        return UserPreferencesEntity(
            userId = userId,
            userName = userName,
            userEmail = userEmail,
            notificationsEnabled = notificationsEnabled,
            darkModeEnabled = darkModeEnabled,
            autoSyncEnabled = autoSyncEnabled,
            syncOnWifiOnly = syncOnWifiOnly,
            lastSyncTime = lastSyncTime,
            selectedCurrency = selectedCurrency,
            selectedLanguage = selectedLanguage
        )
    }

    private fun UserPreferencesEntity.toDomain(): UserPreferences {
        return UserPreferences(
            userId = userId,
            userName = userName,
            userEmail = userEmail,
            notificationsEnabled = notificationsEnabled,
            darkModeEnabled = darkModeEnabled,
            autoSyncEnabled = autoSyncEnabled,
            syncOnWifiOnly = syncOnWifiOnly,
            lastSyncTime = lastSyncTime,
            selectedCurrency = selectedCurrency,
            selectedLanguage = selectedLanguage
        )
    }
}


// === ARCHIVO: app/src/main/java/com/example/offlinefirst/data/sync/SyncWorker.kt ===
package com.example.offlinefirst.data.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.offlinefirst.data.repository.ProductRepositoryImpl
import com.example.offlinefirst.data.repository.PurchaseRepositoryImpl
import com.example.offlinefirst.data.repository.UserPreferencesRepositoryImpl
import com.example.offlinefirst.util.Resource
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val productRepository: ProductRepositoryImpl,
    private val purchaseRepository: PurchaseRepositoryImpl,
    private val userPreferencesRepository: UserPreferencesRepositoryImpl,
    private val networkConnectivityManager: NetworkConnectivityManager,
    private val syncManager: SyncManager
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            if (!networkConnectivityManager.isCurrentlyConnected()) {
                return@withContext Result.retry()
            }

            networkConnectivityManager.setSyncing(true)
            
            val wifiOnly = try {
                val prefs = userPreferencesRepository.getUserPreferences().first()
                prefs.data?.syncOnWifiOnly == true
            } catch (e: Exception) {
                false
            }

            if (wifiOnly && !networkConnectivityManager.isCurrentlyConnectedViaWifi()) {
                return@withContext Result.retry()
            }

            var syncResult = Result.success()
            
            coroutineScope {
                val syncJobs = listOf(
                    async { syncProducts() },
                    async { syncPurchases() }
                )
                
                val results = syncJobs.awaitAll()
                if (results.any { it == Result.retry() }) {
                    syncResult = Result.retry()
                }
            }
            
            userPreferencesRepository.updateLastSyncTime(System.currentTimeMillis())
            
            networkConnectivityManager.setSyncing(false)
            
            syncResult
        } catch (e: Exception) {
            networkConnectivityManager.setSyncing(false)
            
            if (runAttemptCount < MAX_RETRY_COUNT) {
                Result.retry()
            } else {
                Result.failure()
            }
        }
    }

    private suspend fun syncProducts(): Result {
        return try {
            val pendingProducts = productRepository.getPendingProducts().first()
            if (pendingProducts.isNotEmpty()) {
                val syncResult = productRepository.syncPendingProducts()
                if (syncResult is Resource.Error) {
                    return Result.retry()
                }
            }
            
            val refreshResult = productRepository.getProducts(forceRefresh = true).first()
            when (refreshResult) {
                is Resource.Success -> Result.success()
                is Resource.Error -> Result.retry()
                is Resource.Loading -> Result.success()
            }
        } catch (e: Exception) {
            Result.retry()
        }
    }

    private suspend fun syncPurchases(): Result {
        return try {
            val pendingPurchases = purchaseRepository.getPendingPurchases().first()
            if (pendingPurchases.isNotEmpty()) {
                val syncResult = purchaseRepository.syncPendingPurchases()
                if (syncResult is Resource.Error) {
                    return Result.retry()
                }
            }
            
            val refreshResult = purchaseRepository.getPurchaseHistory().first()
            when (refreshResult) {
                is Resource.Success -> Result.success()
                is Resource.Error -> Result.retry()
                is Resource.Loading -> Result.success()
            }
        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        const val WORK_NAME = "sync_worker""
        private const val MAX_RETRY_COUNT = 3
        private const val SYNC_INTERVAL_MINUTES = 15L

        fun schedule(context: Context, wifiOnly: Boolean = false) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(
                    if (wifiOnly) NetworkType.UNMETERED else NetworkType.CONNECTED
                )
                .setRequiresBatteryNotLow(true)
                .build()

            val syncRequest = PeriodicWorkRequestBuilder<SyncWorker>(
                SYNC_INTERVAL_MINUTES, TimeUnit.MINUTES
            )
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.UPDATE,
                syncRequest
            )
        }

        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
        }

        fun runOnce(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val syncRequest = androidx.work.OneTimeWorkRequestBuilder<SyncWorker>()
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context).enqueue(syncRequest)
        }
    }
}

// === ARCHIVO: app/src/main/java/com/example/offlinefirst/di/RepositoryModule.kt ===
package com.example.offlinefirst.di

import com.example.offlinefirst.data.repository.ProductRepositoryImpl
import com.example.offlinefirst.data.repository.PurchaseRepositoryImpl
import com.example.offlinefirst.data.repository.UserPreferencesRepositoryImpl
import com.example.offlinefirst.domain.repository.ProductRepository
import com.example.offlinefirst.domain.repository.PurchaseRepository
import com.example.offlinefirst.domain.repository.UserPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindPurchaseRepository(
        purchaseRepositoryImpl: PurchaseRepositoryImpl
    ): PurchaseRepository

    @Binds
    @Singleton
    abstract fun bindUserPreferencesRepository(
        userPreferencesRepositoryImpl: UserPreferencesRepositoryImpl
    ): UserPreferencesRepository
}

```
