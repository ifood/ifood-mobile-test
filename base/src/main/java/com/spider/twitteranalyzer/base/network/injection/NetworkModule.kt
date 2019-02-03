package com.spider.twitteranalyzer.base.network.injection

import android.content.Context
import com.moczul.ok2curl.CurlInterceptor
import com.spider.twitteranalyzer.base.BuildConfig
import com.spider.twitteranalyzer.base.injection.qualifiers.GoogleOkHttp
import com.spider.twitteranalyzer.base.injection.qualifiers.TwitterOkHttp
import com.spider.twitteranalyzer.base.injection.scopes.PerApplication
import com.spider.twitteranalyzer.base.network.http.interceptor.NetworkMonitorInterceptor
import com.spider.twitteranalyzer.base.network.utils.NetworkMonitor
import com.spider.twitteranalyzer.base.network.utils.NetworkingConfig.TIMEOUT_CONNECT_IN_SECONDS
import com.spider.twitteranalyzer.base.network.utils.NetworkingConfig.TIMEOUT_READ_IN_SECONDS
import com.spider.twitteranalyzer.base.network.utils.NetworkingConfig.TIMEOUT_WRITE_IN_SECONDS
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit


@Module
class NetworkModule {

    @Provides
    @PerApplication
    fun provideRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder()

    @TwitterOkHttp
    @Provides
    @PerApplication
    fun provideTwitterOkHttpClientBuilder(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        curlInterceptor: CurlInterceptor,
        networkMonitorInterceptor: NetworkMonitorInterceptor
    ): OkHttpClient.Builder =
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(httpLoggingInterceptor)
                addInterceptor(curlInterceptor)
            }
            addInterceptor(networkMonitorInterceptor)
            connectTimeout(TIMEOUT_CONNECT_IN_SECONDS, TimeUnit.SECONDS)
            readTimeout(TIMEOUT_READ_IN_SECONDS, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT_WRITE_IN_SECONDS, TimeUnit.SECONDS)
        }

    @GoogleOkHttp
    @Provides
    @PerApplication
    fun provideGoogleOkHttpClientBuilder(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        curlInterceptor: CurlInterceptor,
        networkMonitorInterceptor: NetworkMonitorInterceptor
    ): OkHttpClient.Builder =
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(httpLoggingInterceptor)
                addInterceptor(curlInterceptor)
            }
            addInterceptor(networkMonitorInterceptor)
            connectTimeout(TIMEOUT_CONNECT_IN_SECONDS, TimeUnit.SECONDS)
            readTimeout(TIMEOUT_READ_IN_SECONDS, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT_WRITE_IN_SECONDS, TimeUnit.SECONDS)
        }

    @Provides
    @PerApplication
    fun provideNetworkMonitor(context: Context): NetworkMonitor =
        NetworkMonitor.Impl(context)

    @Provides
    @PerApplication
    fun provideNetworkMonitorInterceptor(networkMonitor: NetworkMonitor): NetworkMonitorInterceptor =
        NetworkMonitorInterceptor(networkMonitor)

    @Provides
    @PerApplication
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()


    @Provides
    @PerApplication
    fun provideConverterFactory(): Converter.Factory =
        GsonConverterFactory.create()

    @Provides
    @PerApplication
    fun provideCurlInterceptor(): CurlInterceptor =
        CurlInterceptor { message -> Timber.tag("Curl").d(message) }


}
