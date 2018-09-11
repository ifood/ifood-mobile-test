package com.rlino.ifoodtwitterchallenge.data.sharedprefs

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TwitterSharedPrefs @Inject constructor(
        private val context: Context
) {

    private val prefs = context.applicationContext.getSharedPreferences(TWITTER_PREFS, Context.MODE_PRIVATE)


    companion object {
        const val TWITTER_PREFS = "TWITTER_PREFS"
        const val TWITTER_BEARER_TOKEN = "TWITTER_BEARER_TOKEN"
    }

    fun updateToken(token: String) {
        prefs.edit()
                .putString(TWITTER_BEARER_TOKEN, token)
                .apply()
    }

    fun getToken(): String {
        return prefs.getString(TWITTER_BEARER_TOKEN, "")
    }

}