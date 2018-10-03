package com.test.ifood.twitterhumour.network.di

import com.test.ifood.twitterhumour.BuildConfig
import com.test.ifood.twitterhumour.network.api.TwitterApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkModule {

    @Provides
    fun providesTwitterApi(): TwitterApi {
        val client = OkHttpClient.Builder()

        if(BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            client.addInterceptor(loggingInterceptor)
        }

        return Retrofit.Builder()
                .baseUrl(BuildConfig.TWITTER_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client.build())
                .build()
                .create(TwitterApi::class.java)
    }
}