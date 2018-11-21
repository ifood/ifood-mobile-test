package br.com.matheusbrandino.ifoodtest.data.repository

import android.arch.lifecycle.MutableLiveData
import br.com.matheusbrandino.ifoodtest.api.twitter.TwitterApi
import br.com.matheusbrandino.ifoodtest.model.Tweet

class TweetRepository(private val api: TwitterApi) {


    val tweets: MutableLiveData<List<Tweet>> = MutableLiveData()
    val error: MutableLiveData<Throwable> = MutableLiveData()


    fun searchTweets(username: String) = api.searchTweets(username, sucess, fallback)


    private val sucess: (List<Tweet>) -> Unit = { tweets.postValue(it) }

    private val fallback: (Throwable) -> Unit = { error.postValue(it) }

}