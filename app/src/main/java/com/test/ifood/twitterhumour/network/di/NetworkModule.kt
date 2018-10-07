package com.test.ifood.twitterhumour.network.di

import com.test.ifood.twitterhumour.BuildConfig
import com.test.ifood.twitterhumour.network.api.TwitterApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor


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

        val consumer = OkHttpOAuthConsumer(BuildConfig.TWITTER_CONSUMER_API_KEY, BuildConfig.TWITTER_CONSUMER_API_SECRET_KEY)
        consumer.setTokenWithSecret(BuildConfig.TWITTER_ACCESS_TOKEN, BuildConfig.TWITTER_SECRET_ACCESS_TOKEN)
        client.addInterceptor(SigningInterceptor(consumer))

        return Retrofit.Builder()
                .baseUrl(BuildConfig.TWITTER_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client.build())
                .build()
                .create(TwitterApi::class.java)
    }
}