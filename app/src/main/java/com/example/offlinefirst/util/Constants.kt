package com.example.offlinefirst.util

/**
 * Constantes de configuración global de la aplicación.
 * Agrupa timeouts, umbrales, límites y configuraciones de sincronización.
 */
object Constants {

    /**
     * Configuración de red y timeouts.
     */
    object Network {
        const val CONNECT_TIMEOUT_SECONDS = 30L
        const val READ_TIMEOUT_SECONDS = 30L
        const val WRITE_TIMEOUT_SECONDS = 30L
        const val RETRY_MAX_ATTEMPTS = 3
        const val RETRY_DELAY_MILLIS = 1000L
        const val RETRY_EXPONENTIAL_BACKOFF_MULTIPLIER = 2.0f
        const val MAX_CONCURRENT_REQUESTS = 5
        const val REQUEST_TIMEOUT_SECONDS = 60L
    }

    /**
     * Configuración de sincronización.
     */
    object Sync {
        const val SYNC_INTERVAL_MINUTES = 5L
        const val SYNC_WORK_NAME = "periodic_sync_work"
        const val SYNC_FLEX_INTERVAL_MINUTES = 2L
        const val MAX_PENDING_OPERATIONS = 100
        const val BATCH_SIZE = 50
        const val CONFLICT_RESOLUTION_STRATEGY = "server_wins"
        const val SYNC_ON_APP_START = true
        const val SYNC_ON_NETWORK_AVAILABLE = true
    }

    /**
     * Configuración de base de datos local.
     */
    object Database {
        const val DATABASE_NAME = "offline_first_db"
        const val DATABASE_VERSION = 1
        const val PAGE_SIZE = 20
        const val PREFETCH_DISTANCE = 5
        const val MAX_CACHE_SIZE_MB = 100
        const val CLEANUP_INTERVAL_HOURS = 24
        const val EXPIRED_CONTENT_RETENTION_DAYS = 7
    }

    /**
     * Configuración de caché.
     */
    object Cache {
        const val IN_MEMORY_CACHE_SIZE = 50
        const val DISK_CACHE_SIZE_MB = 50
        const val CACHE_EXPIRATION_HOURS = 24
        const val ENABLE_DISK_CACHE = true
    }

    /**
     * Configuración de UI.
     */
    object UI {
        const val ANIMATION_DURATION_MS = 300L
        const val DEBOUNCE_DELAY_MS = 300L
        const val SNACKBAR_DURATION_MS = 4000L
        const val TOAST_DURATION_MS = 2000L
        const val REFRESH_INTERVAL_MS = 5000L
        const val LAZY_LOAD_THRESHOLD = 5
    }

    /**
     * Configuración de rendimiento.
     */
    object Performance {
        const val MAX_READ_OPS_PER_SECOND = 1000
        const val MAX_WRITE_OPS_PER_SECOND = 1000
        const val LOCAL_OPERATION_MAX_LATENCY_MS = 200
        const val THREAD_POOL_SIZE = 4
        const val ENABLE_PARALLEL_PROCESSING = true
    }

    /**
     * Configuración de cola de operaciones.
     */
    object Queue {
        const val MAX_QUEUE_SIZE = 500
        const val MAX_RETRY_COUNT = 3
        const val RETRY_DELAY_BASE_MS = 500L
        const val MAX_RETRY_DELAY_MS = 30000L
        const val ENABLE_PRIORITY_QUEUE = true
    }

    /**
     * Configuración de contenido descargable.
     */
    object Content {
        const val MAX_DOWNLOAD_SIZE_MB = 500
        const val DEFAULT_DOWNLOAD_QUALITY = "high"
        const val ENABLE_AUTO_DOWNLOAD = false
        const val WIFI_ONLY_DOWNLOAD = true
        const val DOWNLOAD_NOTIFICATION_CHANNEL_ID = "download_channel"
    }

    /**
     * Configuración de seguridad.
     */
    object Security {
        const val ENCRYPT_LOCAL_DATA = true
        const val ENABLE_SSL_PINNING = false
        const val SESSION_TIMEOUT_MINUTES = 30
        const val MAX_LOGIN_ATTEMPTS = 5
        const val LOCKOUT_DURATION_MINUTES = 15
    }

    /**
     * Keys para SharedPreferences.
     */
    object Preferences {
        const val PREF_NAME = "offline_first_prefs"
        const val KEY_LAST_SYNC = "last_sync_timestamp"
        const val KEY_USER_ID = "current_user_id"
        const val KEY_AUTH_TOKEN = "auth_token"
        const val KEY_SYNC_ENABLED = "sync_enabled"
        const val KEY_WIFI_ONLY_SYNC = "wifi_only_sync"
        const val KEY_NOTIFICATIONS_ENABLED = "notifications_enabled"
    }

    /**
     * Keys para Bundle/Arguments.
     */
    object Arguments {
        const val ARG_USER_ID = "user_id"
        const val ARG_CONTENT_ID = "content_id"
        const val ARG_SYNC_STATUS = "sync_status"
        const val ARG_OPERATION_TYPE = "operation_type"
    }

    /**
     * Tags para logging.
     */
    object Tags {
        const val TAG_NETWORK = "Network"
        const val TAG_DATABASE = "Database"
        const val TAG_SYNC = "Sync"
        const val TAG_QUEUE = "Queue"
        const val TAG_VIEWMODEL = "ViewModel"
        const val TAG_REPOSITORY = "Repository"
        const val TAG_WORKER = "Worker"
    }

    /**
     * URLs de API.
     */
    object Api {
        const val BASE_URL = "https://api.offlinefirst.example.com/"
        const val USERS_ENDPOINT = "users"
        const val PREFERENCES_ENDPOINT = "preferences"
        const val CONTENT_ENDPOINT = "content"
        const val SYNC_ENDPOINT = "sync"
        const val AUTH_ENDPOINT = "auth"
    }

    /**
     * Códigos de resultado.
     */
    object ResultCodes {
        const val SUCCESS = 200
        const val CREATED = 201
        const val NO_CONTENT = 204
        const val BAD_REQUEST = 400
        const val UNAUTHORIZED = 401
        const val FORBIDDEN = 403
        const val NOT_FOUND = 404
        const val CONFLICT = 409
        const val SERVER_ERROR = 500
    }

    /**
     * Mensajes de error predefinidos.
     */
    object ErrorMessages {
        const val NETWORK_ERROR = "Error de conexión. Verifica tu conexión a internet."
        const val SERVER_ERROR = "Error del servidor. Intenta más tarde."
        const val UNKNOWN_ERROR = "Ocurrió un error inesperado."
        const val NO_DATA = "No hay datos disponibles."
        const val SYNC_FAILED = "Error al sincronizar datos."
        const val SAVE_FAILED = "Error al guardar datos."
        const val DELETE_FAILED = "Error al eliminar datos."
        const val VALIDATION_ERROR = "Datos inválidos."
    }
}