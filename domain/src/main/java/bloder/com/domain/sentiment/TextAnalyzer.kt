package bloder.com.domain.sentiment

import com.google.cloud.language.v1.Document
import com.google.cloud.language.v1.LanguageServiceClient


class TextAnalyzer {

    fun analyze(text: String) : SENTIMENT {
        val document = Document.newBuilder().setContent(text).setType(Document.Type.PLAIN_TEXT).build()
        return buildSentimentByScore(LanguageServiceClient.create().analyzeSentiment(document).documentSentiment.score)
    }

    private fun buildSentimentByScore(score: Float) : SENTIMENT = when {
        score >= -1.0 && score < -0.25 -> SENTIMENT.SAD
        score >= -0.25 && score < 0.25 -> SENTIMENT.NEUTRAL
        score >= 0.25 && score <= 1.0 -> SENTIMENT.HAPPY
        else -> SENTIMENT.NEUTRAL
    }
}