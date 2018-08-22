package bloder.com.domain.repository.production.resources

import bloder.com.domain.api.sentiment.SentimentApi
import bloder.com.domain.api_response.sentiment.handleSentimentResponse
import bloder.com.domain.models.sentiment.Sentiment
import bloder.com.domain.payloads.sentiment.request.SentimentAnalyzerRequest
import bloder.com.domain.payloads.sentiment.request.SentimentDocumentRequest
import bloder.com.domain.repository.resources.SentimentRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SentimentRepositoryProduction : SentimentRepository {

    override fun getSentimentFor(tweet: String): Single<Sentiment> = SentimentApi().service()
            .analyzeTweet(request = SentimentAnalyzerRequest(document = SentimentDocumentRequest(content = tweet)))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .flatMap { response ->
                Single.create<Sentiment> {
                    response.handleSentimentResponse(it, response.code())
                }
            }
}