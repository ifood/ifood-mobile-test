package com.rlino.ifoodtwitterchallenge.model

sealed class Sentiment(
        val color: String,
        val emoji: Int
) {
    object Negative: Sentiment("#0000FF", 0x1f614)
    object Positive: Sentiment("#00ff00", 0x1f603)
    object Neutral: Sentiment("#D3D3D3", 0x1f610)

    override fun toString(): String {
        return javaClass.simpleName
    }
}