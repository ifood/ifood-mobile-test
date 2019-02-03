package com.spider.twitteranalyzer.base.network.api.twitter.dto

import com.google.gson.annotations.SerializedName

data class TweetResponse(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("user")
        val user: User,
        @SerializedName(value = "text", alternate = ["full_text"])
        val text: String
) {

    data class User(@SerializedName("screen_name") val name: String)
}