package com.example.tweetanalyzer.model

import com.google.gson.annotations.SerializedName


class TweetResponse(
        @SerializedName("statuses")
        val tweets: MutableList<Tweet>
)
