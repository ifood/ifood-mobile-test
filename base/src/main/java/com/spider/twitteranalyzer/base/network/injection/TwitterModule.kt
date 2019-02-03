package com.spider.twitteranalyzer.base.network.injection

import com.spider.twitteranalyzer.base.BuildConfig
import com.spider.twitteranalyzer.base.injection.qualifiers.TwitterOkHttp
import com.spider.twitteranalyzer.base.injection.qualifiers.TwitterOkHttpBasicOAuth
import com.spider.twitteranalyzer.base.injection.qualifiers.TwitterOkHttpOAuth
import com.spider.twitteranalyzer.base.injection.scopes.PerApplication
import com.spider.twitteranalyzer.base.network.api.twitter.TwitterApi
import com.spider.twitteranalyzer.base.network.api.twitter.TwitterOAuthApi
import com.spider.twitteranalyzer.base.network.http.TokenStorage
import com.spider.twitteranalyzer.base.network.http.interceptor.BasicOAuthInterceptor
import com.spider.twitteranalyzer.base.network.http.interceptor.TwitterOauthInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Named

@Module
class TwitterModule {

    @Provides
    @Named("Twitter")
    @PerApplication
    fun provideBasicOAuthInterceptor(@Named("Twitter") tokenStorage: TokenStorage): BasicOAuthInterceptor =
        BasicOAuthInterceptor.Twitter(tokenStorage.bearerToken)

    @Provides
    @PerApplication
    fun provideTwitterOauthInterceptor(
        twitterOAuthApi: TwitterOAuthApi,
        @Named("Twitter") tokenStorage: TokenStorage
    ): TwitterOauthInterceptor =
        TwitterOauthInterceptor(tokenStorage, twitterOAuthApi)

    @Provides
    @Named("Twitter")
    @PerApplication
    fun provideTwitterStorage(): TokenStorage = TokenStorage.TwitterStorage()

    @Provides
    @PerApplication
    fun provideTwitterOAuthApi(
        builder: Retrofit.Builder,
        converterFactory: Converter.Factory,
        @TwitterOkHttpBasicOAuth okHttpClient: OkHttpClient
    ): TwitterOAuthApi {


        return builder.client(okHttpClient)
            .baseUrl(BuildConfig.TWITTER_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(converterFactory)
            .build()
            .create(TwitterOAuthApi::class.java)
    }

    @Provides
    @PerApplication
    @TwitterOkHttpBasicOAuth
    fun provideTwitterOkHttpBasicOAuth(
        @Named("Twitter")
        basicOAuthInterceptor: BasicOAuthInterceptor,
        @TwitterOkHttp okHttpBuilder: OkHttpClient.Builder
    ): OkHttpClient {
        return okHttpBuilder.apply {
            addInterceptor(basicOAuthInterceptor)
        }.build()
    }

    @Provides
    @PerApplication
    fun provideTwitterApi(
        builder: Retrofit.Builder,
        converterFactory: Converter.Factory,
        @TwitterOkHttpOAuth okHttpClient: OkHttpClient
    ): TwitterApi {

        return builder.client(okHttpClient)
            .baseUrl(BuildConfig.TWITTER_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(converterFactory)
            .build()
            .create(TwitterApi::class.java)
    }


    @Provides
    @PerApplication
    @TwitterOkHttpOAuth
    fun provideTwitterOkHttpOAuth(
        twitterOauthInterceptor: TwitterOauthInterceptor,
        @TwitterOkHttp okHttpBuilder: OkHttpClient.Builder
    ): OkHttpClient {
        return okHttpBuilder.apply {
            addInterceptor(twitterOauthInterceptor)
        }.build()
    }
}