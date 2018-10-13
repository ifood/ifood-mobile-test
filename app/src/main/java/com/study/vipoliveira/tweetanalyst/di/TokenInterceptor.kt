package com.study.vipoliveira.tweetanalyst.di

import com.study.vipoliveira.tweetanalyst.store.TwitterStorePref
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor (
        private val twitterPref: TwitterStorePref) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        if (!twitterPref.getToken().isNullOrEmpty()) {
            val twitterToken = twitterPref.getToken()

            val modifiedRequest = originalRequest
                    .newBuilder()
                    .addHeader("Authorization", "Bearer $twitterToken")
                    .build()

            return chain.proceed(modifiedRequest)
        }

        return chain.proceed(originalRequest)
    }
}