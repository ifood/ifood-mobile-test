package com.eblushe.apptwitter.common.providers

import com.eblushe.apptwitter.BuildConfig
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object ApiProvider {
    lateinit var twitterClient: Retrofit

    fun initTwitterClient(apiUrl: String) {
        val httpClient = OkHttpClient.Builder()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(TwitterInterceptor())
        httpClient.addInterceptor(loggingInterceptor)


        twitterClient = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
    }
}

class TwitterInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val credentials = Credentials.basic(
            BuildConfig.TWITTER_API_KEY,
            BuildConfig.TWITTER_API_SECRET_KEY
        )

        builder.addHeader("Authorization", credentials)
        builder.addHeader("content-type", "application/json")
        return chain.proceed(builder.build())
    }
}