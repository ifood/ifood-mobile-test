package com.spider.twitteranalyzer.feature.detail.domain.model

enum class Sentiment(val emojiCode: String, val color: String) {
    HAPPY("\ud83d\ude00", "#804FFF3B"),
    SAD("\u2639\ufe0f", "#80FF3B3B"),
    NEUTRAL("\ud83d\ude10", "#80FFEB3B"),
}