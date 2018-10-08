package com.study.vipoliveira.tweetanalyst.domain.service

import com.study.vipoliveira.tweetanalyst.domain.model.AnalyzeRequest
import com.study.vipoliveira.tweetanalyst.domain.model.AnalyzeResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleService {
    @POST("/v1/documents:analyzeSentiment")
    fun postAnalysis(@Body request: AnalyzeRequest): Single<AnalyzeResponse>
}