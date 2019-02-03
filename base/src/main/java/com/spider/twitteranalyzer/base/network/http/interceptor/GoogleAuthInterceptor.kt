package com.spider.twitteranalyzer.base.network.http.interceptor

import com.spider.twitteranalyzer.base.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


class GoogleAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.request()
            .url()
            .newBuilder()
            .addQueryParameter("key", BuildConfig.GOOGLE_KEY)
            .build()
            .let {
                chain.request().newBuilder()
                        .url(it)
                        .build()
            }
            .let { chain.proceed(it) }

}