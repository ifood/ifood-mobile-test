package br.com.fornaro.tweetssentiment.model

data class SentimentResponse(
        val documentSentiment: DocumentSentiment
)

data class DocumentSentiment(
        val magnitude: Double,
        val score: Double
)