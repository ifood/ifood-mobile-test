package com.ifood.challenge.core.di.modules

import com.ifood.challenge.BuildConfig
import com.ifood.challenge.base.common.exception.HttpError
import com.ifood.challenge.core.ApiConfig
import com.ifood.challenge.core.di.scopes.ApplicationScope
import com.ifood.challenge.home.data.google.GoogleService
import com.ifood.challenge.home.data.twitter.TwitterService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

private const val TWITTER_API = "TWITTER_API"
private const val GOOGLE_API = "GOOGLE_API"

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    @Named(TWITTER_API)
    fun provideTwitterApi(
        converter: Converter.Factory,
        apiConfig: ApiConfig.Twitter,
        @Named(TWITTER_API) httpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiConfig.baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(converter)
        .client(httpClient)
        .build()

    @Provides
    @ApplicationScope
    @Named(GOOGLE_API)
    fun provideRetrofitRefreshToken(
        converter: Converter.Factory,
        apiConfig: ApiConfig.Google,
        @Named(GOOGLE_API) httpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiConfig.baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(converter)
        .client(httpClient)
        .build()

    @Provides
    @Singleton
    fun provideTwitterApiConfig(): ApiConfig.Twitter = ApiConfig.Twitter()

    @Provides
    @Singleton
    fun provideGoogleApiConfig(): ApiConfig.Google = ApiConfig.Google()

    @Provides
    @ApplicationScope
    fun provideConverter(moshi: Moshi): Converter.Factory = MoshiConverterFactory.create(moshi)

    @Provides
    @ApplicationScope
    fun provideLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.d(message)
            }
        })
    }

    @Provides
    @ApplicationScope
    @Named(TWITTER_API)
    fun provideTwitterClient(
        logger: HttpLoggingInterceptor,
        interceptor: RequestInterceptor,
        twitterConfig: ApiConfig.Twitter
    ): OkHttpClient {

        val consumer = OkHttpOAuthConsumer(twitterConfig.consumerKey, twitterConfig.consumerSecret)
        consumer.setTokenWithSecret(twitterConfig.accessToken, twitterConfig.tokenSecret)

        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.readTimeout(twitterConfig.networkTimeout, TimeUnit.SECONDS)
        okHttpClientBuilder.connectTimeout(twitterConfig.networkTimeout, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            logger.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(logger)
        }

        okHttpClientBuilder.addInterceptor(SigningInterceptor(consumer))
        okHttpClientBuilder.addInterceptor(interceptor)

        return okHttpClientBuilder.build()
    }

    @Provides
    @ApplicationScope
    @Named(GOOGLE_API)
    fun provideGoogleClient(
        logger: HttpLoggingInterceptor,
        interceptor: RequestInterceptor,
        googleConfig: ApiConfig.Google
    ): OkHttpClient {

        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.readTimeout(googleConfig.networkTimeout, TimeUnit.SECONDS)
        okHttpClientBuilder.connectTimeout(googleConfig.networkTimeout, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            logger.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(logger)
        }

        okHttpClientBuilder.addInterceptor(interceptor)

        return okHttpClientBuilder.build()
    }

    @Provides
    @ApplicationScope
    fun provideTwitterService(
        @Named(TWITTER_API) retrofit: Retrofit
    ): TwitterService = retrofit.create()

    @Provides
    @ApplicationScope
    fun provideGoogleService(
        @Named(GOOGLE_API) retrofit: Retrofit
    ): GoogleService = retrofit.create()

    /**
     * Custom interceptor to handle every request and map correct response
     * <b>No-Authentication</b> header parameter is missing
     */
    @Singleton
    class RequestInterceptor @Inject constructor() : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val response = chain.proceed(request)
            when (response.code) {
                in 200..206 -> Timber.d("Request success!")
                else -> throw HttpError
            }
            return response
        }
    }
}
