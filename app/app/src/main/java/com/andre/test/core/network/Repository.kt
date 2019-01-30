package com.andre.test.core.network

import retrofit2.Call

abstract class Repository {
    protected fun <T> request(call: Call<T>) : NetworkResponse {
        return try {
            val response = call.execute()
            if (response.isSuccessful) {
                NetworkResponse.Success(response.body())
            }  else {
                NetworkResponse.NetworkFailure()
            }
        } catch (exception: Throwable) {
            NetworkResponse.NetworkFailure()
        }
    }
}