package com.spider.twitteranalyzer.base.network.api.google

import com.spider.twitteranalyzer.base.network.api.google.dto.SentimentPayload
import com.spider.twitteranalyzer.base.network.api.google.dto.SentimentResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST


interface GoogleApi {

    @POST("v1/documents:analyzeSentiment")
    fun moodAnalyze(@Body requestBody: SentimentPayload): Single<SentimentResponse>
}