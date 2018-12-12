package com.example.tweetanalyzer.data

import com.google.gson.annotations.SerializedName


class TweetResponse(
        @SerializedName("statuses")
        val tweets: MutableList<Tweet>
)
