package com.example.offlinefirst.util

sealed class Error<out T> {
    data class Error(val message: String, val cause: Throwable? = null) : Error<Nothing>()
    data class ServerError<T>(val code: Int, val message: String) : Error<T>()
    data class NetworkError<T>(val message: String) : Error<T>()
}