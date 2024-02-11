package com.example.cocktails.utils.network

/**
 * Data state for processing api response Loading, Success and Error
 */
sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: Throwable?) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}