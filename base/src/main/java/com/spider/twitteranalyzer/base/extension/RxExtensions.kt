package com.spider.twitteranalyzer.base.extension

import com.spider.twitteranalyzer.base.domain.NetworkError
import com.spider.twitteranalyzer.base.domain.TimeoutError
import io.reactivex.Single
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun <T> Single<T>.onNetworkErrorResumeNext(): Single<T> = this.onErrorResumeNext {
    when (it) {
        is UnknownHostException, is ConnectException -> Single.error(NetworkError())
        is SocketTimeoutException -> Single.error(TimeoutError())
        else -> Single.error(it)
    }
}