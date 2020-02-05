package com.ifood.challenge.home.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnalyzeSentimentRequest(
    @Json(name = "document") val document: Document,
    @Json(name = "encodingType") val encodingType: String
) {
    constructor(content: String) : this(Document(content), "UTF8")
}

@JsonClass(generateAdapter = true)
data class Document(
    @Json(name = "content") val content: String,
    @Json(name = "type") val type: String = "PLAIN_TEXT"
)

