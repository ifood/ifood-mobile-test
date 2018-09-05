package com.rlino.ifoodtwitterchallenge.data

import retrofit2.Retrofit

object RetrofitProvider {

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl("https://api.twitter.com/1.1/")
                .build()
    }

    inline fun <reified T> create(): T {
        return retrofit.create(T::class.java)
    }

}