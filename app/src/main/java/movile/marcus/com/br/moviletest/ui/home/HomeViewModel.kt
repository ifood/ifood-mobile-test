package movile.marcus.com.br.moviletest.ui.home

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import movile.marcus.com.br.moviletest.model.data.ContentSentimentRequest
import movile.marcus.com.br.moviletest.model.data.SentimentRequest
import movile.marcus.com.br.moviletest.model.data.SentimentResult
import movile.marcus.com.br.moviletest.model.data.TweetData
import movile.marcus.com.br.moviletest.model.remote.GoogleApi
import movile.marcus.com.br.moviletest.model.remote.TwitterApi
import movile.marcus.com.br.moviletest.ui.BaseViewModel
import movile.marcus.com.br.moviletest.util.ResourceLiveData
import movile.marcus.com.br.moviletest.util.toHandlerFlowable
import okhttp3.MediaType
import okhttp3.RequestBody
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val twitterApi: TwitterApi,
    private val googleApi: GoogleApi,
    private val moshi: Moshi
) : BaseViewModel() {

    val tweetResult = ResourceLiveData<List<TweetData>>()
    val googleResult = ResourceLiveData<SentimentResult>()

    fun getTweetByUser(user: String) {
        twitterApi.getTweetsByUsername(user)
            .doOnSubscribe { loading.postValue(true) }
            .doFinally { loading.postValue(false) }
            .toHandlerFlowable()
            .subscribeLiveData(this, tweetResult)
    }

    fun getTextAnalyzer(text: String) {
        googleApi
            .analyzeTweetMood(createSentimentRequest(text))
            .toHandlerFlowable()
            .subscribeLiveData(this, googleResult)
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