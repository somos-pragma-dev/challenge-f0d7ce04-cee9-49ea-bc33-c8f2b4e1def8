package com.example.offlinefirst.util

sealed class Loading<out T> {
    data object Loading : Loading<Nothing>()
    data class LoadingWithMessage<T>(val message: String) : Loading<T>()
}