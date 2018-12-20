package com.example.tweetanalyzer.util

import com.example.tweetanalyzer.R
import java.io.IOException

data class Resource<out T>(val status: Status, val data: T?, val stringResource: Int?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(throwable: Throwable, data: T?): Resource<T> {
            val msg = when (throwable) {
                is IOException -> R.string.error_no_internet_connection
                else -> R.string.error_default
            }
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> error(errorCode: Int, data: T?): Resource<T> {
            val msg = when(errorCode){
                401 -> R.string.error_not_authorized
                404 -> R.string.error_not_found
                else -> R.string.error_default
            }
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}