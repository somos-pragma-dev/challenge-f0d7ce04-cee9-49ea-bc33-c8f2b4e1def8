package com.example.offlinefirst.data.sync

sealed class Failed<out T> {
    data class Failed(val error: String, val retryable: Boolean = false) : Failed<Nothing>()
    data class NetworkError<T>(val message: String) : Failed<T>()
    data class ServerError<T>(val code: Int, val message: String) : Failed<T>()
}