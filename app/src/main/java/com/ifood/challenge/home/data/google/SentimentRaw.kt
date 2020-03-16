package com.ifood.challenge.home.data.google

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SentimentRaw(
    @Json(name = "documentSentiment") val documentSentiment: DocumentSentimentRaw?
)

@JsonClass(generateAdapter = true)
data class DocumentSentimentRaw(
    @Json(name = "score") val score: Double?,
    @Json(name = "magnitude") val magnitude: Double?
)
