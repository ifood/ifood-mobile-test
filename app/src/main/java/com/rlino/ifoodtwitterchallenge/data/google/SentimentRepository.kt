package com.rlino.ifoodtwitterchallenge.data.google

import com.google.cloud.language.v1.Document
import com.google.cloud.language.v1.Document.Type
import com.google.cloud.language.v1.LanguageServiceClient
import io.reactivex.Single
import java.util.*
import java.util.concurrent.ThreadLocalRandom


class SentimentRepository {

//    fun getSentimentFromText(text: String): SentimentType {
//        LanguageServiceClient.create().use {
//            val doc = Document.newBuilder()
//                    .setContent(text)
//                    .setType(Type.PLAIN_TEXT)
//                    .build()
//            val response = it.analyzeSentiment(doc)
//            val sentiment = response.documentSentiment
//            return if (sentiment == null) {
//                SentimentType.Neutral
//            } else {
//                when(sentiment.magnitude) {
//                    in -1.0..-0.25 -> SentimentType.Negative
//                    in -0.25..0.25 -> SentimentType.Neutral
//                    in 0.25..1.0 -> SentimentType.Positive
//                    else -> SentimentType.Neutral
//                }
//            }
//        }
//    }

    fun getSentimentFromText(text: String): Single<SentimentType> {
        val result = ThreadLocalRandom.current().nextDouble(0.0, 2.0)
        return Single.just(when(result) {
            in 0.0..0.75 -> SentimentType.Negative
            in 0.75..1.25 -> SentimentType.Neutral
            in 1.25..2.0 -> SentimentType.Positive
            else -> SentimentType.Neutral
        })
    }


}

sealed class SentimentType(
        val color: String,
        val emoji: Int
) {
    object Negative: SentimentType("#0000FF", 0x1f614)
    object Positive: SentimentType("#00ff00", 0x1f603)
    object Neutral: SentimentType("#D3D3D3", 0x1f610)

    override fun toString(): String {
        return javaClass.simpleName
    }
}