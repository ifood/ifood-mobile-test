package com.ifood.challenge.home.model

data class Tweet(
    val createdAt: String,
    val text: String,
    var sentiment: Sentiment = Sentiment.None,
    var isLoading: Boolean = false
)
