package com.example.tweetanalyzer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.tweetanalyzer.util.Resource
import com.example.tweetanalyzer.model.TokenResponse
import com.example.tweetanalyzer.model.Tweet
import com.example.tweetanalyzer.repository.twitter.TwitterRepository

class TwitterViewModel(private val twitterRepository: TwitterRepository) : ViewModel() {

    private val searchTweetsInput = MutableLiveData<String>()

    val searchTweetsResult: LiveData<Resource<List<Tweet>>> = Transformations.switchMap(searchTweetsInput) { id -> twitterRepository.getTweetsByUser(id) }

    fun searchTweetsByUserName(userName: String) {
        this.searchTweetsInput.value = userName
    }

    fun getTwitterToken(): LiveData<Resource<TokenResponse>> = twitterRepository.getToken()

}