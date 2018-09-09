package com.rlino.ifoodtwitterchallenge.data.google

import com.google.api.services.language.v1.model.Sentiment
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SentimentRepository @Inject constructor(
        private val sentimentDataSource: SentimentDataSource
) {

    fun getSentimentFromText(text: String): Single<Sentiment> {
        return Single.fromCallable { sentimentDataSource.analyzeText(text) }
    }

}