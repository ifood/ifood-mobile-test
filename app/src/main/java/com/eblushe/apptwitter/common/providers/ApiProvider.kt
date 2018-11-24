package com.eblushe.apptwitter.common.providers

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object ApiProvider {
    lateinit var retrofit: Retrofit

    fun init(apiUrl: String) {
        val httpClient = OkHttpClient.Builder()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(AppInterceptor())
        httpClient.addInterceptor(loggingInterceptor)

        retrofit = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
    }
}

class AppInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("AppInterceptor -->", chain.toString())

        val builder = chain.request().newBuilder()
        builder.addHeader("Authorization", "bear QWERTY")
        builder.addHeader("Content-Type", "application/json")
        return chain.proceed(builder.build())
    }
}