package com.spider.twitteranalyzer.feature.detail.domain

import com.spider.twitteranalyzer.base.network.api.google.dto.SentimentResponse
import com.spider.twitteranalyzer.feature.detail.domain.model.Sentiment
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun map(sentimentResponse: SentimentResponse): Sentiment =
        when (sentimentResponse.documentSentiment.score) {
            in (-1.0)..(-0.25) -> Sentiment.SAD
            in (0.25)..(1.0) -> Sentiment.HAPPY
            else -> Sentiment.NEUTRAL
        }

}