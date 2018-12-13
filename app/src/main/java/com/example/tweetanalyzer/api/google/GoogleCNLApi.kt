package com.example.tweetanalyzer.api.google

import com.example.tweetanalyzer.api.BaseApi
import com.example.tweetanalyzer.model.Document
import com.example.tweetanalyzer.model.SentimentRequest
import com.example.tweetanalyzer.model.SentimentResponse
import retrofit2.Call

class GoogleCNLApi : BaseApi() {

    private val baseUrl = "https://language.googleapis.com"

    private val api = build(baseUrl).create(GoogleCNLService::class.java)

    fun sendTextToSentimentAnalize(text: String): Call<SentimentResponse> {
        val sentimentRequest = SentimentRequest(Document(text))
        val key = "AIzaSyAtYNy6fLOgxPOqGNUzdjg35poAp_WAUSs"
        return api.sendTextToSentimentAnalises(sentimentRequest, key)
    }

}