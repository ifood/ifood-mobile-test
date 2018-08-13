package bloder.com.domain.repository.test.resources

import bloder.com.domain.models.sentiment.Sentiment
import bloder.com.domain.repository.resources.SentimentRepository
import io.reactivex.Single

class SentimentRepositoryTest : SentimentRepository {

    override fun getSentimentFor(tweet: String): Single<Sentiment> = Single.just(
            Sentiment(-0.3)
    )
}