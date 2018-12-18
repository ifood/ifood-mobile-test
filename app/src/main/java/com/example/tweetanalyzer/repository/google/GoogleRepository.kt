package com.example.tweetanalyzer.repository.google

import androidx.lifecycle.LiveData
import com.example.tweetanalyzer.model.Sentiment

interface GoogleRepository {
    fun getSentimentFromText(tweetText: String) : LiveData<Sentiment>
}