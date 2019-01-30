package com.andre.test.core.network

sealed class NetworkResponse {
    data class Success<T>(val response: T) : NetworkResponse()
    class NetworkFailure : NetworkResponse()
    class FetchFailure : NetworkResponse()

}
