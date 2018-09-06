package com.rlino.ifoodtwitterchallenge.ui.timelinesearch

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.rlino.ifoodtwitterchallenge.data.google.SentimentErrorHandler
import com.rlino.ifoodtwitterchallenge.data.google.SentimentRepository
import com.rlino.ifoodtwitterchallenge.data.twitter.TweetsFetchErrorHandler
import com.rlino.ifoodtwitterchallenge.data.twitter.TwitterRepository
import com.rlino.ifoodtwitterchallenge.model.Sentiment
import com.rlino.ifoodtwitterchallenge.model.Tweets
import com.rlino.ifoodtwitterchallenge.ui.BaseViewModel
import com.rlino.ifoodtwitterchallenge.ui.Event
import com.rlino.ifoodtwitterchallenge.ui.defaultSchedulers
import io.reactivex.Single

class TimelineSearchViewModel(
        private val twitterRepository: TwitterRepository = TwitterRepository.getInstance(),
        private val sentimentRepository: SentimentRepository = SentimentRepository.getInstance(),
        private val tweetsErrorHandler: TweetsFetchErrorHandler = TweetsFetchErrorHandler(),
        private val sentimentErrorHandler: SentimentErrorHandler = SentimentErrorHandler()
) : BaseViewModel() {

    private val _snackbarMessage: MutableLiveData<Event<Int>> = MutableLiveData()
    val snackbarMessage: LiveData<Event<Int>>
        get() = _snackbarMessage

    private val _tweets = MutableLiveData<Tweets>()
    val tweets: LiveData<Tweets>
        get() = _tweets

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _tweetSentiment = MutableLiveData<Event<Sentiment>>()
    val tweetSentiment: LiveData<Event<Sentiment>>
        get() = _tweetSentiment


    fun searchTweetsForUsername(username: String) {
        disposable.add(twitterRepository.getTweetsFromUser(username)
                .defaultSchedulers()
                .updateLoading()
                .subscribe(_tweets::setValue) { t -> _snackbarMessage.value = tweetsErrorHandler.logThenHandle(t) })
    }

    fun analyzeTweet(text: String) {
        disposable.add(sentimentRepository.getSentimentFromText(text)
                .defaultSchedulers()
                .updateLoading()
                .subscribe( { s -> _tweetSentiment.value = Event(s) },
                        { t -> _snackbarMessage.value = sentimentErrorHandler.logThenHandle(t) } ))
    }

    private fun <T> Single<T>.updateLoading(): Single<T> = doOnSubscribe { _isLoading.value = true }
                .doOnEvent { _, _ -> _isLoading.value = false}

}