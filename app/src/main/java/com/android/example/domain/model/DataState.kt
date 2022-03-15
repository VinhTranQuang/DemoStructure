package com.android.example.domain.model

sealed class DataState<out T> {

    // Data state for loading to show/hide loading progress
    data class Loading<out T>(val isLoading: Boolean = true, val isRefresh: Boolean = false) : DataState<T>()

    // Data state for emitting data to Observer
    data class Success<out T>(val data: T, val isComplete: Boolean = false) : DataState<T>()

    // Data state for emitting error when get throwable
    data class Failure<out T>(val throwable: Throwable? = null) : DataState<T>()
}