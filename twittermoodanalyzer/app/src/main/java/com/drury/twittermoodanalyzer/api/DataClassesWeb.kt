package com.drury.twittermoodanalyzer.api

import com.twitter.sdk.android.core.models.Tweet

data class TweetResult(val statuses : List<Tweet>)

data class SentimentResult(
    val documentSentiment: DocumentSentiment,
    val language: String,
    val sentences: List<Sentence>
)

data class DocumentSentiment(
    val magnitude: Double,
    val score: Double
)

data class Sentence(
    val sentiment: Sentiment,
    val text: Text
)

data class Sentiment(
    val magnitude: Double,
    val score: Double
)

data class Text(
    val beginOffset: Int,
    val content: String
)