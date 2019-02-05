package com.drss.ifoodmobiletest.repository

import com.twitter.sdk.android.core.TwitterApiClient
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.models.Tweet
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


class TwitterDataSource {

    companion object {
        val instance = TwitterDataSource()
    }

   fun getUserTimeline(screenName: String): Call<List<Tweet>> {
       return TwitterApiClient().statusesService.userTimeline(null,
           screenName, null, null, null, null, true, null, false)
   }
}