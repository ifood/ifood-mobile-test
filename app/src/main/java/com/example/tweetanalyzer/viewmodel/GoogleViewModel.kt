package com.example.tweetanalyzer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.tweetanalyzer.repository.GoogleRepository
import com.example.tweetanalyzer.model.Sentiment

class GoogleViewModel(private val googleRepository: GoogleRepository) : ViewModel() {

    private val searchTweetsInput = MutableLiveData<String>()

    val sentimentResult: LiveData<Sentiment> = Transformations.switchMap(searchTweetsInput) { tweetText -> googleRepository.getSentimentFromText(tweetText) }

    fun analyzeTweet(tweetText: String) {
        this.searchTweetsInput.value = tweetText
    }

}