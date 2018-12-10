package com.shrpereira.tweetsense.data.remote.sentiment

import com.google.gson.annotations.SerializedName

data class SentimentResponse(
	@SerializedName("documentSentiment") val documentSentiment: DocumentResponse
)

data class DocumentResponse(
	@SerializedName("score") val score: Double?
)