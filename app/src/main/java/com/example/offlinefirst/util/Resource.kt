package com.example.offlinefirst.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
 * Wrapper genérico para representar el estado de una operación asíncrona.
 * Proporciona una forma tipada de manejar loading, éxito y errores.
 */
sealed class Resource<out T> {
    /**
     * Estado inicial o en progreso de la operación.
     * Puede携带 datos parciales o mensaje de estado.
     */
    data class Loading<T>(
        val message: String = "Cargando...",
        val data: T? = null
    ) : Resource<T>() {
        fun <R> map(transform: (T) -> R): Resource<R> = Loading(message, data?.let(transform))
    }

    /**
     * Operación completada exitosamente.
     * Contiene los datos resultantes.
     */
    data class Success<T>(
        val data: T,
        val message: String? = null
    ) : Resource<T>() {
        fun <R> map(transform: (T) -> R): Resource<R> = Success(transform(data), message)
    }

    /**
     * Operación fallida con información del error.
     */
    data class Error<T>(
        val message: String,
        val throwable: Throwable? = null,
        val data: T? = null
    ) : Resource<T>() {
        fun <R> map(transform: (T) -> R): Resource<R> = Error(message, throwable, data?.let(transform))
    }

    /**
     * Verifica si el estado actual es de carga.
     */
    fun isLoading(): Boolean = this is Loading

    /**
     * Verifica si la operación fue exitosa.
     */
    fun isSuccess(): Boolean = this is Success

    /**
     * Verifica si hubo un error.
     */
    fun isError(): Boolean = this is Error

    /**
     * Obtiene los datos si están disponibles, null en caso contrario.
     */
    fun getOrNull(): T? = when (this) {
        is Success -> data
        is Loading -> data
        is Error -> data
    }

    /**
     * Obtiene los datos o lanza una excepción.
     */
    fun getOrThrow(): T = when (this) {
        is Success -> data
        is Error -> throw throwable ?: IllegalStateException(message)
        is Loading -> throw IllegalStateException("Resource is still loading")
    }

    /**
     * Obtiene los datos o un valor por defecto.
     */
    fun getOrDefault(default: T): T = when (this) {
        is Success -> data
        else -> default
    }

    companion object {
        /**
         * Crea un estado de carga inicial.
         */
        fun <T> loading(message: String = "Cargando..."): Resource<T> = Loading(message)

        /**
         * Crea un estado de éxito con datos.
         */
        fun <T> success(data: T, message: String? = null): Resource<T> = Success(data, message)

        /**
         * Crea un estado de error.
         */
        fun <T> error(message: String, throwable: Throwable? = null): Resource<T> =
            Error(message, throwable)
    }
}

/**
 * Extensión para convertir un Flow<T> en Flow<Resource<T>>.
 * Maneja automáticamente errores y estados de carga.
 */
fun <T> Flow<T>.asResource(): Flow<Resource<T>> =
    this
        .onStart { emit(Resource.loading()) }
        .map { Resource.success(it) }
        .catch { emit(Resource.error(it.message ?: "Error desconocido", it)) }

/**
 * Extensión para ejecutar una acción solo si es éxito.
 */
inline fun <T> Resource<T>.onSuccess(action: (T) -> Unit): Resource<T> {
    if (this is Resource.Success) action(data)
    return this
}

/**
 * Extensión para ejecutar una acción solo si hay error.
 */
inline fun <T> Resource<T>.onError(action: (String, Throwable?) -> Unit): Resource<T> {
    if (this is Resource.Error) action(message, throwable)
    return this
}

/**
 * Extensión para ejecutar una acción solo si está cargando.
 */
inline fun <T> Resource<T>.onLoading(action: (String) -> Unit): Resource<T> {
    if (this is Resource.Loading) action(message)
    return this
}

/**
 * Operador para encadenar operaciones que retornan Resource.
 * Si la operación actual es exitosa, transforma los datos.
 * Si es error o loading, pasa el estado sin transformar.
 */
fun <T, R> Resource<T>.flatMap(transform: (T) -> Resource<R>): Resource<R> = when (this) {
    is Resource.Success -> transform(data)
    is Resource.Error -> Resource.Error(message, throwable)
    is Resource.Loading -> Resource.Loading(message)
}

/**
 * Convierte Resource<Resource<T>> a Resource<T> extrayendo el valor anidado.
 */
fun <T> Resource<Resource<T>>.flatten(): Resource<T> = when (this) {
    is Resource.Success -> data
    is Resource.Error -> Resource.Error(message, throwable)
    is Resource.Loading -> Resource.Loading(message)
}