package com.rlino.ifoodtwitterchallenge.domain.google

import com.rlino.ifoodtwitterchallenge.data.google.SentimentRepository
import com.rlino.ifoodtwitterchallenge.domain.SingleUseCase
import com.rlino.ifoodtwitterchallenge.model.Sentiment
import io.reactivex.Single
import javax.inject.Inject

class GetSentimentUseCase @Inject constructor(
        private val sentimentRepository: SentimentRepository
) : SingleUseCase<String, Sentiment>() {

    override fun execute(parameters: String): Single<Sentiment> {
        return sentimentRepository.getSentimentFromText(parameters)
                .map {
                    return@map when (it.score) {
                        in -1.0f..-0.25f -> Sentiment.Negative
                        in -0.25f..0.25f -> Sentiment.Neutral
                        in 0.25f..1.0f -> Sentiment.Positive
                        else -> Sentiment.Neutral
                    }
                }
    }

}