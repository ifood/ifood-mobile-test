package com.study.vipoliveira.tweetanalyst.domain

import com.study.vipoliveira.tweetanalyst.domain.model.Sentiment
import io.reactivex.Single

interface GoogleUseCases {
    fun analyzeTweet(tweet: String): Single<Sentiment>
}