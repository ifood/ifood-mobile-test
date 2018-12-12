package com.example.tweetanalyzer.api.twitter

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer

class TwitterInterceptor : Interceptor {

    private val apiKey = "IXKu6GIessR9Y5Ll53oe3rBHf"
    private val apiSecretKey = "gTgqiL3xmZH9JOw94mfYMXTGSShQ02eErJHE89aMFChYiiwV5M"
    private val accessToken = "1072640124171759618-Wt7PvqwnbYjB71OrqTVidhjv5gHC3F"
    private val accessTokenSecret = "2wf6GPS7uSKFVHgXzUrpnymSQIol0nd2qwl2LZYVPT8Ty"

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = createRequest(original)
        return chain.proceed(request)
    }

    private fun createRequest(original: Request): Request {

        val consumer = OkHttpOAuthConsumer(apiKey, apiSecretKey)
        consumer.setTokenWithSecret(accessToken, accessTokenSecret)

        return consumer.sign(original).unwrap() as Request
    }

}