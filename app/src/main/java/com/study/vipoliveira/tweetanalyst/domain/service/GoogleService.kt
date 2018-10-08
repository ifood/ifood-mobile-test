package com.study.vipoliveira.tweetanalyst.domain.service

import com.study.vipoliveira.tweetanalyst.model.AnalyzeRequest
import com.study.vipoliveira.tweetanalyst.model.AnalyzeResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleService {
    @POST("/v1/documents:analyzeSentiment")
    fun getAnalysis(@Body request: AnalyzeRequest): Single<AnalyzeResponse>
}