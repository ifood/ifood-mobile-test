package bloder.com.domain.payloads.sentiment

import com.google.gson.annotations.SerializedName

data class SentimentAnalyzerRequest(
        @SerializedName("document") val document: SentimentAnalyzerRequest,
        @SerializedName("encodingType") val encodingType: String = "UTF8"
)