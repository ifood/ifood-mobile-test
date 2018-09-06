package com.rlino.ifoodtwitterchallenge.data

import com.rlino.ifoodtwitterchallenge.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object RetrofitProvider {

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BuildConfig.TWITTER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpProvider.okHttpClient)
                .build()
    }

    inline fun <reified T> create(): T {
        return retrofit.create(T::class.java)
    }

}

object OkHttpProvider {

    val okHttpClient by lazy {
        OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                  Timber.tag("OkHttp").d(it)
                }))
                .build()
    }

}