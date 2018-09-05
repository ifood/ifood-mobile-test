package com.rlino.ifoodtwitterchallenge.ui.timelinesearch

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.rlino.ifoodtwitterchallenge.data.google.SentimentRepository
import com.rlino.ifoodtwitterchallenge.data.google.SentimentType
import com.rlino.ifoodtwitterchallenge.data.twitter.TwitterRepository
import com.rlino.ifoodtwitterchallenge.model.Tweets
import com.rlino.ifoodtwitterchallenge.ui.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TimelineSearchViewModel(
        private val twitterRepository: TwitterRepository = TwitterRepository.getInstance(),
        private val sentimentRepository: SentimentRepository = SentimentRepository.getInstance()
) : BaseViewModel() {

    private val _snackbarMessage: MutableLiveData<String> = MutableLiveData()
    val snackbarMessage: LiveData<String>
        get() = _snackbarMessage

    private val _tweets = MutableLiveData<Tweets>()
    val tweets: LiveData<Tweets>
        get() = _tweets

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _tweetMeaning = MutableLiveData<SentimentType>()
    val tweetMeaning: LiveData<SentimentType>
        get() = _tweetMeaning


    fun searchTweetsForUsername(username: String) {
        disposable.add(twitterRepository.getTweetsFromUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _isLoading.value = true }
                .doOnEvent { _, _ ->  _isLoading.value = false }
                .subscribe(_tweets::setValue) { t -> _snackbarMessage.value = t.message })
    }

    fun analyzeTweet(text: String) {
        disposable.add(sentimentRepository.getSentimentFromText(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEvent { _, _ ->  _isLoading.value = false }
                .subscribe( { s -> _tweetMeaning.value = s },
                        { t -> _snackbarMessage.value = t.message } ))
    }

}