package com.drss.ifoodmobiletest.repository.interfaces

import com.drss.ifoodmobiletest.data.GCloudSentimentBody
import com.drss.ifoodmobiletest.data.SentimentResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface GCloudApiInterface {

    @POST("/v1/documents:analyzeSentiment")
    fun analyzeDocumentSentiment(@Body body: GCloudSentimentBody,
                                 @Query("key") apiKey: String = "AIzaSyDyYJH6ZQnM4Qx6Ut9BvWbmR_rUisZn__8"): Single<SentimentResponse>

    companion object Factory {
        const val GCLOUD_API = "https://language.googleapis.com"

        fun create(): Retrofit {
            return retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(GCLOUD_API)
                .build()
        }
    }

}