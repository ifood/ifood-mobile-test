package br.com.fornaro.tweetssentiment.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import br.com.fornaro.tweetssentiment.model.Tweet
import br.com.fornaro.tweetssentiment.model.User
import br.com.fornaro.tweetssentiment.repository.NaturalLanguageRepository
import br.com.fornaro.tweetssentiment.repository.TweetsRepository
import br.com.fornaro.tweetssentiment.utils.InternetUtils
import br.com.fornaro.tweetssentiment.view.tweets.TweetsCallback

class TweetsViewModel(application: Application) : AndroidViewModel(application) {

    private var tweetsLiveData: LiveData<List<Tweet>>? = null
    private var userLiveData: LiveData<User>? = null
    private val tweetsRepository = TweetsRepository()
    private val naturalLanguageRepository = NaturalLanguageRepository()
    var callback: TweetsCallback? = null

    fun getUser(username: String): LiveData<User> {
        if (userLiveData == null) {
            userLiveData = tweetsRepository.getUser(username)
        }
        return userLiveData!!
    }

    fun getTweets(username: String): LiveData<List<Tweet>> {
        if (tweetsLiveData == null) {
            tweetsLiveData = tweetsRepository.getTweets(username)
        }
        return tweetsLiveData!!
    }

    fun analyze(tweet: Tweet): LiveData<Tweet>? {
        if (!InternetUtils.isNetworkAvailable(getApplication())) {
            callback?.onNoInternetConnection()
            return null
        }
        return naturalLanguageRepository.analyzeTweet(tweet)
    }
}