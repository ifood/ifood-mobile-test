package com.spider.twitteranalyzer.base.network.http.interceptor

import com.spider.twitteranalyzer.base.extension.encode
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

abstract class BasicOAuthInterceptor(private val secret: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = chain.request().newBuilder().apply {
        header("Authorization", "Basic ${secret.encode()}")
        header("Content-Type", "application/x-www-form-urlencoded")
    }.let {
        chain.proceed(it.build())
    }

    class Twitter(secret: String) : BasicOAuthInterceptor(secret)
}