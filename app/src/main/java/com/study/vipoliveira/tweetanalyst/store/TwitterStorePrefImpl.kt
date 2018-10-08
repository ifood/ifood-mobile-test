package com.study.vipoliveira.tweetanalyst.store

import android.content.Context

class TwitterStorePrefImpl(context: Context) : TwitterStorePref{

    private val storeTokenPref = "storeTokenPref"
    private val twitterOauthToken = "twitterOauthToken"

    private val sharedPref = context.applicationContext.getSharedPreferences(storeTokenPref, Context.MODE_PRIVATE)

    override fun saveToken(token: String) {
        sharedPref.edit()
                .putString(twitterOauthToken, token)
                .apply()    }

    override fun getToken(): String {
        return sharedPref.getString(twitterOauthToken, "")!!
    }




}