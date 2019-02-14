package movile.marcus.com.br.moviletest.model.data

import com.squareup.moshi.Json

data class SentimentRequest(
    @field:Json(name = "document") val document: ContentSentimentRequest
)

data class ContentSentimentRequest(
    @field:Json(name = "content") val content: String,
    @field:Json(name = "type") val type: String = "PLAIN_TEXT"
)

data class SentimentResult(
    @field:Json(name = "documentSentiment") val documentSentiment: Sentiment?
)

data class Sentiment(
    @field:Json(name = "magnitude") val magnitude: Double,
    @field:Json(name = "score") val score: Double
)