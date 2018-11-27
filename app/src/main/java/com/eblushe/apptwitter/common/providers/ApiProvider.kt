package com.eblushe.apptwitter.common.providers

import com.eblushe.apptwitter.BuildConfig
import com.eblushe.apptwitter.common.models.OAuthToken
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object ApiProvider {
    lateinit var twitterClient: Retrofit

    fun initTwitterClient(apiUrl: String) {
        var oAuthToken: OAuthToken? = loadOAuthToken()
        val httpClient = OkHttpClient.Builder()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(loggingInterceptor)
        httpClient.addInterceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()

            if (oAuthToken?.isNotValid() == true) {
                oAuthToken = loadOAuthToken()
            }

            val authType= if (oAuthToken?.isValid() == true) {
                oAuthToken?.authorization
            } else {
                Credentials.basic(
                    BuildConfig.TWITTER_API_KEY,
                    BuildConfig.TWITTER_API_SECRET_KEY
                )
            }

            builder.addHeader("Authorization", authType!!)
            builder.addHeader("content-type", "application/json")
            chain.proceed(builder.build())
        }


        twitterClient = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
    }


    private fun loadOAuthToken(): OAuthToken? {
        var oAuthToken: OAuthToken? = null
        StorageProvider.readPreference {
            it.getString(OAuthToken.ACCESS_TOKEN_TAG, null).let { token ->
                oAuthToken = OAuthToken(token)
            }
        }

        return oAuthToken
    }
}