package com.example.tweetanalyzer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tweetanalyzer.api.google.GoogleCNLApi
import com.example.tweetanalyzer.model.Sentiment
import com.example.tweetanalyzer.model.SentimentResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GoogleRepository @Inject constructor(private val googleApi: GoogleCNLApi) {

    fun getSentimentFromText(tweetText: String) : LiveData<Sentiment>{

        val result = MutableLiveData<Sentiment>()

        googleApi.sendTextToSentimentAnalize(tweetText).enqueue(object  : Callback<SentimentResponse>{
            override fun onFailure(call: Call<SentimentResponse>, t: Throwable) {
                result.value = null
            }

            override fun onResponse(call: Call<SentimentResponse>, response: Response<SentimentResponse>) {
                if(response.isSuccessful){
                    val tweetResponse = response.body() as SentimentResponse
                    result.value = tweetResponse.documentSentiment
                }
            }

        })

        return result
    }

}