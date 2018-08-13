package bloder.com.domain.payloads.sentiment.response

import bloder.com.domain.models.sentiment.Sentiment
import com.google.gson.annotations.SerializedName

data class SentimentPayload(@SerializedName("score") private val score: Double) {

    fun toModel() : Sentiment = Sentiment(score)
}