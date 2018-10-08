package com.study.vipoliveira.tweetanalyst.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.study.vipoliveira.tweetanalyst.BuildConfig
import com.study.vipoliveira.tweetanalyst.domain.service.GoogleService
import com.study.vipoliveira.tweetanalyst.domain.service.TwitterService
import com.study.vipoliveira.tweetanalyst.store.TwitterStorePref
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class NetworkModule {
    @Provides
    fun provideOkHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
                .addInterceptor(tokenInterceptor)

        return httpClient.build()
    }
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    fun provideRetrofit(httpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.TWITTER_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build()
    }

    @Provides
    @Named("GoogleOkHttp")
    fun provideGoogleOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("key", BuildConfig.GOOGLE_API_KEY)
                    .build()

            chain.proceed(original.newBuilder().url(url).build())
        }

        return httpClient.build()
    }

    @Provides
    @Named("GoogleRetrofit")
    fun provideGoogleRetrofit(@Named("GoogleOkHttp") httpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.GOOGLE_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build()
    }

    @Provides

    fun provideTwitterService(retrofit:  Retrofit) : TwitterService {
        return retrofit.create<TwitterService>(TwitterService::class.java)
    }

    @Provides
    fun provideGoogleService(@Named("GoogleRetrofit") retrofit:  Retrofit) : GoogleService {
        return retrofit.create<GoogleService>(GoogleService::class.java)
    }

    @Provides
    fun provideTokenInterceptor(storePref: TwitterStorePref) : TokenInterceptor {
        return TokenInterceptor(storePref)
    }
}
