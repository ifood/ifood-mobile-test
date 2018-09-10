package br.com.fornaro.tweetssentiment.model

import com.google.gson.annotations.SerializedName

data class TweetResponse(
        val text: String,
        @SerializedName("created_at") val createdAt: String
)