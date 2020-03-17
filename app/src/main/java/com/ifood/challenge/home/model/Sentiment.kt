package com.ifood.challenge.home.model

sealed class Sentiment {

    object Happy : Sentiment()
    object Neutral : Sentiment()
    object Sad : Sentiment()
    object None : Sentiment()

    override fun toString(): String = when (this) {
        Happy -> "Happy"
        Neutral -> "Neutral"
        Sad -> "Sad"
        None -> "None"
    }
}
