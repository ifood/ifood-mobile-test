package com.rlino.ifoodtwitterchallenge.data.interceptor

import com.rlino.ifoodtwitterchallenge.data.sharedprefs.TwitterSharedPrefs
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TwitterAuthInterceptor @Inject constructor(
        private val twitterSharedPrefs: TwitterSharedPrefs
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return if(!twitterSharedPrefs.getToken().isNullOrEmpty()) {
            val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${twitterSharedPrefs.getToken()}")
                    .build()
            chain.proceed(newRequest)
        } else {
            chain.proceed(chain.request())
        }
    }


}