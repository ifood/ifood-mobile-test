package br.com.fornaro.tweetssentiment.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.fornaro.tweetssentiment.api.NaturalLanguageApi
import br.com.fornaro.tweetssentiment.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TweetsRepository {

    fun getTweets(username: String): LiveData<List<Tweet>>? {
        val data = MutableLiveData<List<Tweet>>()

        val list = mutableListOf<Tweet>()
        list.add(Tweet(1, "This is a sad tweet"))
        list.add(Tweet(2, "This is a newtral tweet"))
        list.add(Tweet(3, "This is a happy tweet"))
        data.value = list

        return data
    }

    fun analyzeTweet(tweet: Tweet): LiveData<Tweet> {
        val data = MutableLiveData<Tweet>()

        val api = Retrofit.Builder()
                .baseUrl("https://language.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NaturalLanguageApi::class.java)

        api.analyzeSentiment("AIzaSyCSzI5ttkTqKCtCaAuRWgDk-q-41F8ukdg", SentimentRequest(Document(tweet.text)))
                .enqueue(object : Callback<SentimentResponse> {
                    override fun onFailure(call: Call<SentimentResponse>?, t: Throwable?) {
                    }

                    override fun onResponse(call: Call<SentimentResponse>?, response: Response<SentimentResponse>?) {
                        if (response?.isSuccessful!!) {
                            tweet.sentiment = Sentiment.Happy
                            data.value = tweet
                        }
                    }
                })

        return data
    }
}