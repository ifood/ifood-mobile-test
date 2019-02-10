package movile.marcus.com.br.moviletest.ui.home

import movile.marcus.com.br.moviletest.model.data.SentimentResult
import movile.marcus.com.br.moviletest.model.data.TweetData
import movile.marcus.com.br.moviletest.model.repository.GoogleRepository
import movile.marcus.com.br.moviletest.model.repository.TwitterRepository
import movile.marcus.com.br.moviletest.ui.BaseViewModel
import movile.marcus.com.br.moviletest.util.ResourceLiveData
import movile.marcus.com.br.moviletest.util.toHandlerFlowable
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val twitterRepository: TwitterRepository,
    private val googleRepository: GoogleRepository
) : BaseViewModel() {

    val tweetResult = ResourceLiveData<List<TweetData>>()
    val googleResult = ResourceLiveData<SentimentResult>()

    fun getTweetByUser(user: String) {
        twitterRepository
            .getTweetByUser(user)
            .doOnSubscribe { loading.postValue(true) }
            .doFinally { loading.postValue(false) }
            .toHandlerFlowable()
            .subscribeLiveData(this, tweetResult)
    }

    fun getTextAnalyzer(text: String) {
        googleRepository
            .getTextAnalyzer(text)
            .toHandlerFlowable()
            .subscribeLiveData(this, googleResult)
    }
}