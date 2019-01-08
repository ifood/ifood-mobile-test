package com.drury.twittermoodanalyzer.model

class SentimentAnalyzer(val score: Double) {
    // Check the documentation about Sentiment object on:
    // https://cloud.google.com/natural-language/docs/reference/rest/v1/Sentiment

    fun getMoodEnum(): SentimentEnum {
        return when(score) {
            // 5% margin of error
            in (-1.0)..(-0.05) -> SentimentEnum.SAD
            in (0.05)..(1.0) -> SentimentEnum.HAPPY
            else -> SentimentEnum.NEUTRAL
        }
    }
}