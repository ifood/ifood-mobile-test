package com.rlino.ifoodtwitterchallenge.data.google

import com.rlino.ifoodtwitterchallenge.model.Sentiment
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SentimentRepository @Inject constructor(
        private val sentimentDataSource: SentimentDataSource
) {

    fun getSentimentFromText(text: String): Single<Sentiment> {
        return Single.create<Sentiment> { emitter ->

            val score = sentimentDataSource.analyzeText(text).documentSentiment?.score ?: 0.0f

            emitter.onSuccess(when (score) {
                in -1.0f..-0.25f -> Sentiment.Negative
                in -0.25f..0.25f -> Sentiment.Neutral
                in 0.25f..1.0f -> Sentiment.Positive
                else -> Sentiment.Neutral
            })
        }
    }


}