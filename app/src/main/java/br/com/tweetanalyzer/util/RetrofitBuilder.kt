package br.com.tweetanalyzer.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by gabrielsamorim
 * on 19/05/18.
 */
class RetrofitBuilder {

    private fun getGson(): Gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()

    fun getRetrofit(url: String): Retrofit =
            Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build()
}