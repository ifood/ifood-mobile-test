package com.study.vipoliveira.tweetanalyst.domain

import com.study.vipoliveira.tweetanalyst.domain.repositories.GoogleRepository
import com.study.vipoliveira.tweetanalyst.domain.model.Sentiment
import io.reactivex.Single

class GoogleUseCasesImp
constructor(private val googleRepository: GoogleRepository): GoogleUseCases {

    override fun analyzeTweet(tweet: String): Single<Sentiment> {
        return googleRepository.analyzeText(tweet).map { mapToSentimentType(it.documentSentiment.score) }
    }

    private fun mapToSentimentType(score: Double): Sentiment {
        return when {
            score < -0.33f -> Sentiment.SAD
            score > 0.33f -> Sentiment.HAPPY
            else -> Sentiment.NEUTRAL
        }
    }
}
