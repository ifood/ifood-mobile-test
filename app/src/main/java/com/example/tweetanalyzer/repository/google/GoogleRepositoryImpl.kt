package com.example.tweetanalyzer.repository.google

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tweetanalyzer.api.google.GoogleCNLApi
import com.example.tweetanalyzer.util.Resource
import com.example.tweetanalyzer.model.Sentiment
import com.example.tweetanalyzer.model.SentimentResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GoogleRepositoryImpl @Inject constructor(private val googleApi: GoogleCNLApi) : GoogleRepository {

    override fun getSentimentFromText(tweetText: String) : LiveData<Resource<Sentiment>> {

        val result = MutableLiveData<Resource<Sentiment>>()

        googleApi.sendTextToSentimentAnalize(tweetText).enqueue(object  : Callback<SentimentResponse>{
            override fun onFailure(call: Call<SentimentResponse>, t: Throwable) {
                result.value = Resource.error(t, null)
            }

            override fun onResponse(call: Call<SentimentResponse>, response: Response<SentimentResponse>) {
                if(response.isSuccessful){
                    val tweetResponse = response.body() as SentimentResponse
                    tweetResponse.documentSentiment.tweetText = tweetText
                    result.value = Resource.success(tweetResponse.documentSentiment)
                }else{
                    result.value = Resource.error(response.code(), null)
                }
            }

        })

        return result
    }

}