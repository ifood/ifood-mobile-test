package com.spider.twitteranalyzer.base.network.api.google.dto

import com.google.gson.annotations.SerializedName

data class SentimentResponse(
        @SerializedName("documentSentiment") val documentSentiment: DocumentSentiment
) {
    data class DocumentSentiment(
            @SerializedName("magnitude") val magnitude: Double,
            @SerializedName("score") val score: Double
    )
}

