package com.shrpereira.tweetsense.data.remote.sentiment

import com.google.gson.annotations.SerializedName

private const val ENCODING_TYPE = "UTF8"
private const val REQUEST_DATA_TYPE = "PLAIN_TEXT"

data class SentimentRequest(
	@SerializedName("document") val document: DocumentRequest,
	@SerializedName("encodingType") val encodingType: String = ENCODING_TYPE
)

data class DocumentRequest(
	@SerializedName("content") val content: String = "",
	@SerializedName("type") val type: String = REQUEST_DATA_TYPE
)