package com.study.vipoliveira.tweetanalyst.domain.model

import com.google.gson.annotations.SerializedName

data class TweetResponse(
        @SerializedName("created_at")
        val createdAt: String,
        val id: Long,
        val text: String,
        val sentiment: Sentiment?
)