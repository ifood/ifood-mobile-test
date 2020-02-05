package com.ifood.challenge.home.data

import com.ifood.challenge.base.data.BaseMapper
import com.ifood.challenge.home.model.Sentiment
import timber.log.Timber
import javax.inject.Inject

class SentimentMapper @Inject constructor() : BaseMapper<SentimentRaw, Sentiment>() {
    override val trackException: (Throwable) -> Unit
        get() = { error ->
            Timber.e(error, "Track error on Crashlytics")
        }

    override fun assertEssentialParams(raw: SentimentRaw) {
        if (raw.documentSentiment == null) addMissingParam("sentimentDocument")
        if (raw.documentSentiment?.score == null) addMissingParam("sentimentScore")
    }

    override fun copyValues(raw: SentimentRaw): Sentiment {
        return when (raw.documentSentiment!!.score!!) {
            in 0.25..1.0 -> Sentiment.Happy
            in -0.25..0.24 -> Sentiment.Neutral
            in -1.0..-0.24 -> Sentiment.Sad
            else -> Sentiment.None
        }
    }
}
