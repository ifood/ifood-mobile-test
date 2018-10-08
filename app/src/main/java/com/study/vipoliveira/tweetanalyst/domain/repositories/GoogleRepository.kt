package com.study.vipoliveira.tweetanalyst.domain.repositories

import com.study.vipoliveira.tweetanalyst.domain.model.AnalyzeResponse
import io.reactivex.Single

interface GoogleRepository {
    fun analyzeText(tweet: String): Single<AnalyzeResponse>
}