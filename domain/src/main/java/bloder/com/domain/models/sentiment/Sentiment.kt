package bloder.com.domain.models.sentiment

class Sentiment(private val score: Double) {

    fun getSentiment() : SENTIMENT = when {
        score < -0.2 -> SENTIMENT.SAD
        score < 0.5 -> SENTIMENT.NEUTRAL
        else -> SENTIMENT.HAPPY
    }
}

enum class SENTIMENT {
    HAPPY, NEUTRAL, SAD
}