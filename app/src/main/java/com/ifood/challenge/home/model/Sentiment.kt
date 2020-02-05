package com.ifood.challenge.home.model

sealed class Sentiment {

    object Happy : Sentiment()
    object Neutral : Sentiment()
    object Sad : Sentiment()
    object None : Sentiment()
}
