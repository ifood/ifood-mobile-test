package com.example.tweetanalyzer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.tweetanalyzer.util.Resource
import com.example.tweetanalyzer.model.Sentiment
import com.example.tweetanalyzer.repository.google.GoogleRepository

class GoogleViewModel(private val googleRepository: GoogleRepository) : ViewModel() {

    private val searchTweetsInput = MutableLiveData<String>()

    val sentimentResult: LiveData<Resource<Sentiment>> = Transformations.switchMap(searchTweetsInput) { tweetText -> googleRepository.getSentimentFromText(tweetText) }

    fun analyzeTweet(tweetText: String) {
        this.searchTweetsInput.value = tweetText
    }

}