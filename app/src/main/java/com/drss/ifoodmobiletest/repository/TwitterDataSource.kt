package com.drss.ifoodmobiletest.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterApiClient
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.models.Tweet
import javax.inject.Inject

class TwitterDataSource @Inject constructor(){

    fun getUserTimeline(screenName: String): LiveData<List<Tweet>> {
        val data = MutableLiveData<List<Tweet>>()
        TwitterApiClient().statusServiceFromScreenName(screenName).enqueue(object : Callback<List<Tweet>>() {

            override fun success(result: Result<List<Tweet>>?) {
                data.value = result?.data
            }

            override fun failure(exception: TwitterException?) {
                Log.e("TESTE", "BLA")
            }

        })
        return data
    }

}


fun TwitterApiClient.statusServiceFromScreenName(screenName: String): retrofit2.Call<List<Tweet>> = this.statusesService.userTimeline(
    null,
    screenName, null, null, null, null, true, null, false
)