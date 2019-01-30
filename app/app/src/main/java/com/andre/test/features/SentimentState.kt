package com.andre.test.features

sealed class SentimentState {
    object HappyTweet : SentimentState()
    object NeutralTweet : SentimentState()
    object SadTweet : SentimentState()
}