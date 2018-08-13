package bloder.com.domain.interactor

import bloder.com.domain.SingleUseCase
import bloder.com.domain.binding.SingleUseCaseBinding
import bloder.com.domain.models.sentiment.Sentiment

class SentimentInteractor : SingleUseCase() {

    fun getSentimentFrom(tweet: String) : SingleUseCaseBinding<Sentiment> {
        return run(repository.forSentiment().getSentimentFor(tweet))
    }
}