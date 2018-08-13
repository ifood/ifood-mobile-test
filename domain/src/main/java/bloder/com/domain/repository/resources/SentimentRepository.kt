package bloder.com.domain.repository.resources

import bloder.com.domain.models.sentiment.Sentiment
import io.reactivex.Single


interface SentimentRepository {

    fun getSentimentFor(tweet: String) : Single<Sentiment>
}