package com.mungaicodes.gamehub.util

sealed class Resource<out T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(message: String) : Resource<T>(message = message)
    class Success<T>(data: T) : Resource<T>(data = data)
    class Error<T>(message: String) : Resource<T>(message = message)
}
