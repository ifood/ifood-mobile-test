package bloder.com.presentation.twitter.sentiment

import bloder.com.domain.interactor.SentimentInteractor
import bloder.com.presentation.AppViewModel


class SentimentViewModel(private val interactor: SentimentInteractor) : AppViewModel<SentimentState>() {

    fun getSentimentFor(tweet: String) {
        interactor.getSentimentFrom(tweet).subscribe {
            onSuccess { sentiment ->
                dispatch(SentimentState.SentimentGenerated(sentiment.getSentiment()))
            }
            onError {
                dispatch(SentimentState.ErrorWhenGenerateSentiment("Could not calculate the feeling"))
            }
        }
    }
}