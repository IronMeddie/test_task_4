package com.example.utils

sealed class DataResource<out T> {
    data class Success<out T>(val data: T) : DataResource<T>()
    data class Failure(
        val errorCode: Int?,
        val errorBody: String,
    ) : DataResource<Nothing>()
    object Loading : DataResource<Nothing>()
}