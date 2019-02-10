package movile.marcus.com.br.moviletest.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import movile.marcus.com.br.moviletest.BuildConfig
import movile.marcus.com.br.moviletest.model.remote.GoogleApi
import movile.marcus.com.br.moviletest.model.remote.TwitterApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named("HTTP_TWITTER_URL")
    fun provideTwitterHttpUrl() = BuildConfig.TWITTER_API

    @Provides
    @Singleton
    @Named(value = "HTTP_GOOGLE_URL")
    fun provideGoogleHttpUrl() = BuildConfig.GOOGLE_API

    @Provides
    @Singleton
    @Named("TWITTER_CLIENT")
    fun provideOkHttpTwitterClient(@Named(value = "OKHTTP_TWITTER") builder: OkHttpClient.Builder): OkHttpClient =
        builder.build()

    @Provides
    @Singleton
    @Named("GOOGLE_CLIENT")
    fun provideOkHttpGoogleClient(@Named(value = "OKHTTP_GOOGLE") builder: OkHttpClient.Builder): OkHttpClient =
        builder.build()

    @Provides
    @IntoSet
    fun provideInterceptor() = Interceptor { chain ->
        val request = chain.request()
        chain.proceed(request)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    @Named(value = "OKHTTP_TWITTER")
    fun provideOkHttpTwitterBuilder(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        interceptors.forEach { builder.addInterceptor(it) }
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        val consumer = OkHttpOAuthConsumer(
            BuildConfig.CONSUMER_KEY,
            BuildConfig.CONSUMER_SECRET
        )
        consumer.setTokenWithSecret(
            BuildConfig.ACCESS_TOKEN,
            BuildConfig.ACCESS_TOKEN_SECRET
        )

        builder.addInterceptor(SigningInterceptor(consumer))
        return builder
    }

    @Provides
    @Singleton
    @Named(value = "OKHTTP_GOOGLE")
    fun provideOkHttpGoogleBuilder(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        interceptors.forEach { builder.addInterceptor(it) }
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return builder
    }

    @Provides
    @Singleton
    fun provideTwitterApi(@Named(value = "TWITTER_CLIENT") client: OkHttpClient, moshi: Moshi, @Named("HTTP_TWITTER_URL") url: String): TwitterApi {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .build()
            .create(TwitterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGoogleApi(@Named(value = "GOOGLE_CLIENT") client: OkHttpClient, moshi: Moshi, @Named("HTTP_GOOGLE_URL") url: String): GoogleApi {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .build()
            .create(GoogleApi::class.java)
    }
}