package com.ifood.challenge.home.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TweetRaw(
    @Json(name = "created_at") val createdAt: String?,
    @Json(name = "text") val text: String?
)
