package com.example.tweetanalyzer.api

import com.example.tweetanalyzer.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseApi {

    fun build(url: String, interceptor: Interceptor): Retrofit {
        val builder = OkHttpClient.Builder()

        builder.addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)


        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }

        val client = builder.build()

        val retrofitBuilder = Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(defaultConverterFactory())

        return retrofitBuilder.build()
    }

    private fun defaultConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }

}