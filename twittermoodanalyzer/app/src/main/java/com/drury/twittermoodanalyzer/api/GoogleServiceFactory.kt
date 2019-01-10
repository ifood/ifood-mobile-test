package com.drury.twittermoodanalyzer.api

import com.drury.twittermoodanalyzer.utils.AppConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class GoogleServiceFactory {

        private var baseUrl: String = AppConstants.BASE_URL_GCP_CLOUD_LANGUAGE_NATURAL

        private var builder = getRetrofitBuilder()

        private var retrofit: Retrofit? = null

        private fun buildClient(): OkHttpClient {

            return OkHttpClient.Builder()
                    .connectTimeout(AppConstants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(AppConstants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .build()

        }

        private fun getRetrofitBuilder(): Retrofit.Builder {
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(buildClient())
                    .baseUrl(baseUrl)

        }

        fun <S> createService(serviceClass: Class<S>): S {
            if (retrofit == null) {
                retrofit = builder.build()
            }
            return retrofit!!.create(serviceClass)
        }
}