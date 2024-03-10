package com.projects.movierater.data.network.model

sealed class NetworkResponse<out T: Any, out E : Any> {

    data class Success<T : Any>(val body: T) : NetworkResponse<T, Nothing>()

    data class ApiError<E : Exception>(val body: E) : NetworkResponse<Nothing, Nothing>()

}
