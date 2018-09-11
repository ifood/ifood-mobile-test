package com.rlino.ifoodtwitterchallenge.model

import java.util.*


data class Tweets(
        val tweets: List<Tweet>
)

data class Tweet(
    val createdAt: Date,
    val id: Long,
    val text: String
)