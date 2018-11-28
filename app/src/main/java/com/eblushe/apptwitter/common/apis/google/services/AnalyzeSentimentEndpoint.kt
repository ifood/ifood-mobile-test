package com.eblushe.apptwitter.common.apis.twitter.services

import com.eblushe.apptwitter.BuildConfig
import com.eblushe.apptwitter.common.apis.google.requests.DocumentRequest
import com.eblushe.apptwitter.common.apis.google.responses.FeelingResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AnalyzeSentimentEndpoint {

    @Headers("Content-Type: application/json")
    @POST("/v1/documents:analyzeSentiment?key=${BuildConfig.GOOGLE_API_KEY}")
    fun postFeeling(@Body documentRequest: DocumentRequest): Single<FeelingResponse>
}