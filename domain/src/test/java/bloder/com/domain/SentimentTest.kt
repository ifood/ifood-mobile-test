package bloder.com.domain

import bloder.com.domain.models.sentiment.SENTIMENT
import bloder.com.domain.models.sentiment.Sentiment
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SentimentTest {

    @Test fun getHappySentiment() {
        val sentiment = Sentiment(0.6)
        assert(sentiment.getSentiment() == SENTIMENT.HAPPY)
    }

    @Test fun getNeutralSentiment() {
        val sentiment = Sentiment(-0.1)
        assert(sentiment.getSentiment() == SENTIMENT.NEUTRAL)
    }

    @Test fun getSadSentiment() {
        val sentiment = Sentiment(-0.5)
        assert(sentiment.getSentiment() == SENTIMENT.SAD)
    }
}