package com.spider.twitteranalyzer.base.test

import com.spider.twitteranalyzer.base.network.api.google.GoogleApi
import com.spider.twitteranalyzer.base.network.api.twitter.TwitterApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Fixtures {

    fun twitterApi(path: String): TwitterApi {
        val timeout = 1
        val client = okHttpClient(timeout)
        val retrofit = Retrofit.Builder()
            .baseUrl(path)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(TwitterApi::class.java)
    }

    fun googleApi(path: String): GoogleApi {
        val timeout = 1
        val client = okHttpClient(timeout)
        val retrofit = Retrofit.Builder()
            .baseUrl(path)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(GoogleApi::class.java)
    }

    private fun okHttpClient(timeout: Int): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
            .readTimeout(timeout.toLong(), TimeUnit.SECONDS)
            .writeTimeout(timeout.toLong(), TimeUnit.SECONDS)
            .build()
    }
}