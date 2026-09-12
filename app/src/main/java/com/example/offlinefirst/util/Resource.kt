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