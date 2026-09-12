package com.example.offlinefirst.domain.model

sealed class Success<out T> {
    data class Success<T>(val data: T) : Success<T>()
    data object Empty : Success<Nothing>()
}