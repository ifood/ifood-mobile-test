package bloder.com.domain.payloads.sentiment.response

import com.google.gson.annotations.SerializedName

data class SentimentAnalyzerResponse(
        @SerializedName("documentSentiment") private val documentSentiment: Unit
)