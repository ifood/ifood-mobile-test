package br.com.fornaro.tweetssentiment.model

data class DocumentSentiment(
        val magnitude: Double,
        val score: Double
)

data class SentimentResponse(
        val documentSentiment: DocumentSentiment
) {
    fun getSentiment(): Sentiment {
        return when {
            documentSentiment.score > 0.33 -> Sentiment.Happy
            documentSentiment.score < -0.33 -> Sentiment.Sad
            else -> Sentiment.Neutral
        }
    }
}