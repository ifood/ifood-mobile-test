package com.spider.twitteranalyzer.base.network.api.google.dto

import com.google.gson.annotations.SerializedName

data class SentimentPayload(
        @SerializedName("document")
        val document: DocumentPayload,
        @SerializedName("encodingType")
        val encodingType: String = "UTF8"
) {
    data class DocumentPayload(
            @SerializedName("content") val text: String,
            @SerializedName("type") val textType: String = "PLAIN-TEXT"
    )
}

