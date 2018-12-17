package com.example.tweetanalyzer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.tweetanalyzer.repository.TwitterRepository
import com.example.tweetanalyzer.model.Tweet

class TwitterViewModel(private val twitterRepository: TwitterRepository) : ViewModel() {

    private val searchTweetsInput = MutableLiveData<String>()

    val searchTweetsResult: LiveData<List<Tweet>> = Transformations.switchMap(searchTweetsInput) { id -> twitterRepository.getTweetsByUser(id) }

    fun searchTweetsByUserName(userName: String) {
        this.searchTweetsInput.value = userName
    }

}