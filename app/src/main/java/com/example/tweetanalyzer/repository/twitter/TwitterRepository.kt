package com.example.tweetanalyzer.repository.twitter

import androidx.lifecycle.LiveData
import com.example.tweetanalyzer.model.TokenResponse
import com.example.tweetanalyzer.model.Tweet

interface TwitterRepository {
    fun getToken() : LiveData<TokenResponse>
    fun getTweetsByUser(userName: String) : LiveData<List<Tweet>>
}