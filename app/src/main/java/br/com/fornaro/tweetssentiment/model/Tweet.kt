package br.com.fornaro.tweetssentiment.model

import java.util.*

data class Tweet(
        val text: String,
        val createdAt: Date,
        var sentiment: Sentiment = Sentiment.None
)