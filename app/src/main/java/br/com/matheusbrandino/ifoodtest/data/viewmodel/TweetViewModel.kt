package br.com.matheusbrandino.ifoodtest.data.viewmodel

import android.arch.lifecycle.ViewModel
import br.com.matheusbrandino.ifoodtest.data.repository.TweetRepository

class TweetViewModel(private val repository: TweetRepository) : ViewModel() {

    fun searchTweetsFrom(username: String) = repository.searchTweets(username)
    fun error() = repository.error
    fun tweets() = repository.tweets


}