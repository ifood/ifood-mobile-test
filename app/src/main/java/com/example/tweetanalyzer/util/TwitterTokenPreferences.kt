package com.example.tweetanalyzer.util

import android.content.SharedPreferences
import javax.inject.Inject

class TwitterTokenPreferences @Inject constructor(private val preferences: SharedPreferences){

    private val TWITTER_TOKEN = "twitterToken"

    fun updateToken(token: String) {
        preferences.edit().putString(TWITTER_TOKEN, token).apply()
    }

    fun getToken(): String = preferences.getString(TWITTER_TOKEN, "") ?: ""


}