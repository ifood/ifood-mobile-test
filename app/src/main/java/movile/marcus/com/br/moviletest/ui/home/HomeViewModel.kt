package movile.marcus.com.br.moviletest.ui.home

import movile.marcus.com.br.moviletest.model.Api
import movile.marcus.com.br.moviletest.model.data.TweetData
import movile.marcus.com.br.moviletest.ui.BaseViewModel
import movile.marcus.com.br.moviletest.util.ResourceLiveData
import movile.marcus.com.br.moviletest.util.toHandlerFlowable
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val api: Api) : BaseViewModel() {

    val tweetResult = ResourceLiveData<List<TweetData>>()

    fun getTweetByUser(user: String) {
        api.getTweetsByUsername(user)
            .doOnSubscribe { loading.postValue(true) }
            .doFinally { loading.postValue(false) }
            .toHandlerFlowable()
            .subscribeLiveData(this, tweetResult)
    }
}