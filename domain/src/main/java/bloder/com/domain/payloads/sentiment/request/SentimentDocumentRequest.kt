package bloder.com.domain.payloads.sentiment.request

import com.google.gson.annotations.SerializedName

data class SentimentDocumentRequest(
        @SerializedName("content") val content: String,
        @SerializedName("type") val type: String = "PLAIN_TEXT"
)