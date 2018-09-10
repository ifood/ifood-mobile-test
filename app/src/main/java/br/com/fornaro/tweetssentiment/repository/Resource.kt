package br.com.fornaro.tweetssentiment.repository

// A generic class that contains data and status about loading this data.
class Resource<T> private constructor(val status: Status,
                                      val data: T?,
                                      val errorCode: Int? = null) {

    enum class Status {
        Success, Error, Loading
    }

    fun isError() = status == Status.Error
    fun isSuccess() = status == Status.Success

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.Success, data)
        }

        fun <T> error(data: T?, errorCode: Int): Resource<T> {
            return Resource(Status.Error, data, errorCode)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.Loading, data)
        }
    }
}