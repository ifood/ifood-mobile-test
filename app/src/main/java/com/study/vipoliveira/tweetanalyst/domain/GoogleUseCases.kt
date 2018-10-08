package com.study.vipoliveira.tweetanalyst.domain

import com.study.vipoliveira.tweetanalyst.model.AnalyzeResponse
import io.reactivex.Single

interface GoogleUseCases {
    fun analyzeTweet(tweet: String): Single<AnalyzeResponse>
}