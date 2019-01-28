package com.andre.test.core

sealed class NetworkResponse {
    data class Success<T>(val response: T) : NetworkResponse()
    class NetworkFailure : NetworkResponse()
    class FetchFailure : NetworkResponse()

}
