package com.example.tweetanalyzer.api.google

import com.example.tweetanalyzer.model.SentimentRequest
import com.example.tweetanalyzer.model.SentimentResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface GoogleCNLService {

    @POST("/v1beta2/documents:analyzeSentiment")
    fun sendTextForSentimentAnalysis(@Body sentimentRequest: SentimentRequest, @Query("key") key: String): Call<SentimentResponse>

}