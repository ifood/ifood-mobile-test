package com.eblushe.apptwitter.common.apis.google.responses

import com.google.gson.annotations.SerializedName

class FeelingResponse {
    @SerializedName("documentSentiment")
    var DocumentSentimentResponse: DocumentSentimentResponse? = null
}

class DocumentSentimentResponse {
    @SerializedName("score")
    var score: Double? = null
}