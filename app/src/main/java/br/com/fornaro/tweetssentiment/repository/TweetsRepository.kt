package br.com.fornaro.tweetssentiment.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.fornaro.tweetssentiment.api.NaturalLanguageApi
import br.com.fornaro.tweetssentiment.api.TweeterApi
import br.com.fornaro.tweetssentiment.model.Document
import br.com.fornaro.tweetssentiment.model.SentimentRequest
import br.com.fornaro.tweetssentiment.model.SentimentResponse
import br.com.fornaro.tweetssentiment.model.Tweet
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor

class TweetsRepository {

    fun getTweets(username: String): LiveData<List<Tweet>>? {
        val data = MutableLiveData<List<Tweet>>()

        val api = Retrofit.Builder()
                .baseUrl("https://api.twitter.com/")
                .client(getRequestClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TweeterApi::class.java)

        api.getTweets(username)
                .enqueue(object : Callback<List<Tweet>> {
                    override fun onFailure(call: Call<List<Tweet>>?, t: Throwable?) {
                    }

                    override fun onResponse(call: Call<List<Tweet>>?, response: Response<List<Tweet>>?) {
                        if (response?.isSuccessful!!) {
                            data.value = response.body()
                        }
                    }
                })

        return data
    }

    private fun getRequestClient(): OkHttpClient {
        val consumer = OkHttpOAuthConsumer("DLvdcgSJ1fdA3TSnSrQLVInVP",
                "YA6ZNKsFIwswFxvgBf0jzF3D0gpdFUcxSzod9KrkFvOhoLaD1X")
        consumer.setTokenWithSecret("1000221381454376962-G3SwGQWvB18gN4EljJcSLWvqCpobiy",
                "FMr04toyVx3auYd3PvFu1MsOcdKt2PewPDsZSRihpSSSp")

        return OkHttpClient.Builder()
                .addInterceptor(SigningInterceptor(consumer))
                .build()
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
                            tweet.sentiment = response.body()?.getSentiment()!!
                            data.value = tweet
                        }
                    }
                })

        return data
    }
}