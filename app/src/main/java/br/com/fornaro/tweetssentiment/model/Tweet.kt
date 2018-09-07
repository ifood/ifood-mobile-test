package br.com.fornaro.tweetssentiment.model

data class Tweet(
        val id: Long,
        val text: String,
        var sentiment: Sentiment = Sentiment.None
)