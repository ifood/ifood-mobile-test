package br.com.fornaro.tweetssentiment.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.content.Context
import br.com.fornaro.tweetssentiment.R
import br.com.fornaro.tweetssentiment.model.Tweet
import br.com.fornaro.tweetssentiment.model.User
import br.com.fornaro.tweetssentiment.repository.NaturalLanguageRepository
import br.com.fornaro.tweetssentiment.repository.Resource
import br.com.fornaro.tweetssentiment.repository.TweetsRepository
import br.com.fornaro.tweetssentiment.utils.InternetUtils
import br.com.fornaro.tweetssentiment.view.tweets.TweetsCallback
import java.text.SimpleDateFormat
import java.util.*

class TweetsViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        private const val DATE_FORMAT = "EEE MMM dd HH:mm:ss ZZZZZ yyyy"
    }

    private var tweetsLiveData: LiveData<List<Tweet>>? = null
    private var userLiveData: LiveData<Resource<User>>? = null
    private val tweetsRepository = TweetsRepository()
    private val naturalLanguageRepository = NaturalLanguageRepository()
    var callback: TweetsCallback? = null

    fun getUser(username: String): LiveData<Resource<User>> {
        if (userLiveData == null) {
            userLiveData = tweetsRepository.getUser(username)
        }
        return userLiveData!!
    }

    fun getTweets(username: String): LiveData<List<Tweet>> {
        if (tweetsLiveData == null) {
            tweetsLiveData = Transformations.map(tweetsRepository.getTweets(username)!!) { tweetsResponse ->
                val tweets = mutableListOf<Tweet>()
                tweetsResponse?.forEach {
                    val createdAt = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).parse(it.createdAt)
                    tweets.add(Tweet(it.text, createdAt))
                }
                tweets
            }
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