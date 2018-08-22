package bloder.com.domain.api_response.sentiment

import bloder.com.domain.api_response.ResponseGod
import bloder.com.domain.models.sentiment.Sentiment
import bloder.com.domain.payloads.sentiment.response.SentimentAnalyzerResponse
import io.reactivex.SingleEmitter
import retrofit2.Response

class SentimentResponseGod(
        private val response: Response<SentimentAnalyzerResponse>,
        private val emitter: SingleEmitter<Sentiment>
) : ResponseGod {

    override fun on200() {
        response.body()?.let {
            emitter.onSuccess(it.toModel())
        }
    }

    override fun unknown(code: Int) {
        emitter.onError(Throwable())
    }
}

fun Response<SentimentAnalyzerResponse>.handleSentimentResponse(emitter: SingleEmitter<Sentiment>, code: Int) {
    SentimentResponseGod(this, emitter).handle(code)
}