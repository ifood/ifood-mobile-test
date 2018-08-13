package bloder.com.domain.payloads.sentiment.response

import bloder.com.domain.models.sentiment.Sentiment
import com.google.gson.annotations.SerializedName

data class SentimentAnalyzerResponse(
        @SerializedName("documentSentiment") private val documentSentiment: SentimentPayload
) {

    fun toModel() : Sentiment = documentSentiment.toModel()
}