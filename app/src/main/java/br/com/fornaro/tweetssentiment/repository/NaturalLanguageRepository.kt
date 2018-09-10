package br.com.fornaro.tweetssentiment.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.fornaro.tweetssentiment.api.NaturalLanguageApi
import br.com.fornaro.tweetssentiment.model.Document
import br.com.fornaro.tweetssentiment.model.SentimentRequest
import br.com.fornaro.tweetssentiment.model.SentimentResponse
import br.com.fornaro.tweetssentiment.model.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NaturalLanguageRepository {

    private val api = Retrofit.Builder()
            .baseUrl("https://language.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NaturalLanguageApi::class.java)

    fun analyzeTweet(tweet: Tweet): LiveData<Tweet> {
        val data = MutableLiveData<Tweet>()

        api.analyzeSentiment("AIzaSyCSzI5ttkTqKCtCaAuRWgDk-q-41F8ukdg", SentimentRequest(Document(tweet.text)))
                .enqueue(object : Callback<SentimentResponse> {
                    override fun onFailure(call: Call<SentimentResponse>?, t: Throwable?) {
                    }

                    override fun onResponse(call: Call<SentimentResponse>?, response: Response<SentimentResponse>?) {
                        if (response?.isSuccessful!!) {
                            tweet.sentiment = response.body()?.getSentiment()!!
                            data.value = tweet
                        }
                    }
                })

        return data
    }
}