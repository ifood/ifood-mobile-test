package com.example.tweetanalyzer.repository.twitter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tweetanalyzer.api.twitter.TwitterApi
import com.example.tweetanalyzer.model.TokenResponse
import com.example.tweetanalyzer.model.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TwitterRepositoryImpl @Inject constructor(private val twitterApi: TwitterApi) : TwitterRepository{

    override fun getToken(): LiveData<TokenResponse> {
        val result = MutableLiveData<TokenResponse>()

        twitterApi.getToken().enqueue(object : Callback<TokenResponse>{
            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                result.value = null
            }

            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                if (response.isSuccessful){
                    val tokenResponse = response.body() as TokenResponse
                    result.value = tokenResponse
                }
            }

        })

        return result
    }

    override fun getTweetsByUser(userName: String) : LiveData<List<Tweet>>{

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