package com.example.offlinefirst.util

sealed class Sync<out T> {
    data object Idle : Sync<Nothing>()
    data class Syncing<T>(val progress: Float = 0f) : Sync<T>()
    data class Completed<T>(val data: T) : Sync<T>()
    data class Failed<T>(val error: String) : Sync<T>()
}