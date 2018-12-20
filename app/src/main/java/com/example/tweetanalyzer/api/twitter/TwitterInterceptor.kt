package com.example.tweetanalyzer.api.twitter

import android.util.Base64
import com.example.tweetanalyzer.util.TwitterTokenPreferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class TwitterInterceptor @Inject constructor(val twitterTokenPreferences: TwitterTokenPreferences) : Interceptor {

    private val apiKey = "IXKu6GIessR9Y5Ll53oe3rBHf"
    private val apiSecretKey = "gTgqiL3xmZH9JOw94mfYMXTGSShQ02eErJHE89aMFChYiiwV5M"

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = createRequest(original)
        return chain.proceed(request)
    }

    private fun createRequest(original: Request): Request {
        val apiAuth = Base64.encodeToString("$apiKey:$apiSecretKey".toByteArray(), Base64.NO_WRAP)
        val token = twitterTokenPreferences.getToken()
        val builder = original.newBuilder()

        if (token.isNotEmpty()){
            addHeader(builder, "Authorization", "Bearer $token")
        }else {
            addHeader(builder, "Authorization", "Basic $apiAuth")

        }

        return builder.build()
    }

    private fun addHeader(builder: Request.Builder?, key: String, value: String?) {
        if (value != null) {
            builder?.addHeader(key, value)
        }
    }

}