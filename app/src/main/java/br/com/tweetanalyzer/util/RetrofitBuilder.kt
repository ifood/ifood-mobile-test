package br.com.tweetanalyzer.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by gabrielsamorim
 * on 19/05/18.
 */
class RetrofitBuilder {
    fun getRetrofit(url: String): Retrofit = Retrofit
            .Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}