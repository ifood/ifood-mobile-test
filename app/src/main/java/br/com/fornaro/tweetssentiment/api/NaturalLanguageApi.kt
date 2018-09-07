package br.com.fornaro.tweetssentiment.api

import br.com.fornaro.tweetssentiment.model.SentimentRequest
import br.com.fornaro.tweetssentiment.model.SentimentResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface NaturalLanguageApi {

    @POST("/v1/documents:analyzeSentiment")
    fun analyzeSentiment(@Query("key") key: String, @Body body: SentimentRequest): Call<SentimentResponse>
}