package com.spider.twitteranalyzer.base.network.injection

import com.spider.twitteranalyzer.base.BuildConfig
import com.spider.twitteranalyzer.base.injection.qualifiers.GoogleOkHttp
import com.spider.twitteranalyzer.base.injection.qualifiers.GoogleOkHttpBasicAuth
import com.spider.twitteranalyzer.base.injection.scopes.PerApplication
import com.spider.twitteranalyzer.base.network.api.google.GoogleApi
import com.spider.twitteranalyzer.base.network.http.interceptor.GoogleAuthInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Module
class GoogleModule {

    @Provides
    @PerApplication
    fun provideTwitterOAuthApi(
        builder: Retrofit.Builder,
        converterFactory: Converter.Factory,
        @GoogleOkHttpBasicAuth okHttpClient: OkHttpClient
    ): GoogleApi = builder.client(okHttpClient)
        .baseUrl(BuildConfig.GOOGLE_BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(converterFactory)
        .build()
        .create(GoogleApi::class.java)

    @Provides
    @PerApplication
    @GoogleOkHttpBasicAuth
    fun provideTwitterOkHttpBasicOAuth(
        @GoogleOkHttp okHttpBuilder: OkHttpClient.Builder,
        googleAuthInterceptor: GoogleAuthInterceptor
    ): OkHttpClient = okHttpBuilder.apply {
        addInterceptor(googleAuthInterceptor)
    }.build()

    @Provides
    @PerApplication
    fun provideGoogleAuthInterceptor(): GoogleAuthInterceptor =
        GoogleAuthInterceptor()
}