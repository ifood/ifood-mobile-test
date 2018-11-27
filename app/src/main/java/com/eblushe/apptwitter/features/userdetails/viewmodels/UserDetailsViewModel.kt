package com.eblushe.apptwitter.features.userdetails.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eblushe.apptwitter.common.models.DataHolder
import com.eblushe.apptwitter.common.models.Tweet
import com.eblushe.apptwitter.common.providers.RxProvider
import com.eblushe.apptwitter.common.repositories.TweetRepository

class UserDetailsViewModel(
    var tweetsLiveData: MutableLiveData<DataHolder<List<Tweet>>> = MutableLiveData(),
    val tweetRepository: TweetRepository,
    val rxProvider: RxProvider) : ViewModel() {

    fun requestTweetsFromScreenName(screenName: String) {
        if (tweetsLiveData.value?.state == DataHolder.State.FINISHED) {
            return
        }

        tweetsLiveData.value = DataHolder(state = DataHolder.State.LOADING)
        val disposable = tweetRepository.getUserTimeLine(screenName)
            .subscribe(
                ::onRequestTweetsFromScreenNameSuccess,
                ::onRequestTweetsFromScreenNameError
            )

        rxProvider.addDisposable(disposable)
    }

    private fun onRequestTweetsFromScreenNameSuccess(tweets: List<Tweet>) {
        if (tweets.isEmpty()) {
            tweetsLiveData.value = DataHolder(tweets, DataHolder.State.EMPTY)
        } else {
            tweetsLiveData.value = DataHolder(tweets, DataHolder.State.LOADED)
        }
    }

    private fun onRequestTweetsFromScreenNameError(throwable: Throwable) {
        throwable.printStackTrace()
        tweetsLiveData.value = DataHolder(state = DataHolder.State.ERROR, error = Exception(throwable))
    }

    override fun onCleared() {
        rxProvider.clearAllDisposable()
        super.onCleared()
    }
}