package movile.marcus.com.br.moviletest.model.repository

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import movile.marcus.com.br.moviletest.model.data.ContentSentimentRequest
import movile.marcus.com.br.moviletest.model.data.SentimentRequest
import movile.marcus.com.br.moviletest.model.data.SentimentResult
import movile.marcus.com.br.moviletest.model.api.GoogleApi
import okhttp3.MediaType
import okhttp3.RequestBody
import javax.inject.Inject

class GoogleRepository @Inject constructor(
    private val googleApi: GoogleApi,
    private val moshi: Moshi
) {

    fun getTextAnalyzer(text: String): Flowable<SentimentResult> {
        return googleApi
            .analyzeTweetMood(createSentimentRequest(text))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun createSentimentRequest(text: String): RequestBody {
        val adapter: JsonAdapter<SentimentRequest> = moshi.adapter<SentimentRequest>(SentimentRequest::class.java)
        val sentimentRequest = SentimentRequest(ContentSentimentRequest(text))
        return createRequestBody(adapter.toJson(sentimentRequest))
    }

    private fun createRequestBody(text: String): RequestBody {
        return RequestBody.create(MediaType.parse("application/json"), text)
    }
}