package com.example.tweetanalyzer.viewmodel

import androidx.lifecycle.ViewModel

import androidx.lifecycle.ViewModelProvider
import com.example.tweetanalyzer.repository.GoogleRepository
import com.example.tweetanalyzer.repository.TwitterRepository
import javax.inject.Inject

class CustomViewModelFactory
            @Inject constructor(
                    private val twitterRepository: TwitterRepository,
                    private val googleRepository: GoogleRepository
            ) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TwitterViewModel::class.java) -> TwitterViewModel(twitterRepository) as T
            modelClass.isAssignableFrom(GoogleViewModel::class.java) -> GoogleViewModel(googleRepository) as T
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
