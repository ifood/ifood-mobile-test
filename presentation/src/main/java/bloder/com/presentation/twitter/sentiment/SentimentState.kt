package bloder.com.presentation.twitter.sentiment

import bloder.com.domain.models.sentiment.SENTIMENT

sealed class SentimentState {

    class SentimentGenerated(val sentiment: SENTIMENT) : SentimentState()
    class ErrorWhenGenerateSentiment(val errorMessage: String) : SentimentState()
}