package com.ifood.challenge.home.data.google

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface GoogleService {

    @POST("v1/documents:analyzeSentiment")
    fun analyzeSentiment(
        @Query("key") key: String,
        @Body analyzeSentimentRequest: AnalyzeSentimentRequest
    ): Single<SentimentRaw>

}
