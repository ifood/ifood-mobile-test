package com.example.tweetanalyzer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tweetanalyzer.api.twitter.TwitterApi
import com.example.tweetanalyzer.model.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TwitterRepository @Inject constructor(private val twitterApi: TwitterApi) {

    fun getTweetsByUser(userName: String) : LiveData<List<Tweet>>{

        val result = MutableLiveData<List<Tweet>>()

        twitterApi.getTweetsByUserName(userName).enqueue(object  : Callback<List<Tweet>>{
            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                result.value = null
            }

            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                if(response.isSuccessful){
                    val tweets = response.body() as List<Tweet>
                    result.value = tweets
                }
            }

        })

        return result
    }

}