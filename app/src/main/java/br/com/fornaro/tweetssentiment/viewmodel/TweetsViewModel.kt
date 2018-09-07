package br.com.fornaro.tweetssentiment.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import br.com.fornaro.tweetssentiment.model.Tweet
import br.com.fornaro.tweetssentiment.repository.TweetsRepository

class TweetsViewModel(application: Application) : AndroidViewModel(application) {

    private var tweetsLiveData: LiveData<List<Tweet>>? = null
    private val tweetsRepository = TweetsRepository()

    fun getTweets(username: String): LiveData<List<Tweet>> {
        if (tweetsLiveData == null) {
            tweetsLiveData = tweetsRepository.getTweets(username)
        }
        return tweetsLiveData!!
    }

    fun analyze(tweet: Tweet): LiveData<Tweet> {
        return tweetsRepository.analyzeTweet(tweet)
    }
}