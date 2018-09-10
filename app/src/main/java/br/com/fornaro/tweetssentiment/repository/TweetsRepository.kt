package br.com.fornaro.tweetssentiment.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.fornaro.tweetssentiment.api.TweeterApi
import br.com.fornaro.tweetssentiment.model.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor

class TweetsRepository {

    private val api = Retrofit.Builder()
            .baseUrl("https://api.twitter.com/")
            .client(getRequestClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TweeterApi::class.java)

    fun getUser(username: String): LiveData<Resource<User>> {
        val data = MutableLiveData<Resource<User>>()

        api.getUser(username)
                .enqueue(object : Callback<User> {
                    override fun onFailure(call: Call<User>?, t: Throwable?) {
                    }

                    override fun onResponse(call: Call<User>?, response: Response<User>?) {
                        if (response?.isSuccessful!!) {
                            data.value = Resource.success(response.body()!!)
                        } else {
                            data.value = Resource.error(response.body(), response.code())
                        }
                    }
                })

        return data
    }

    fun getTweets(username: String): LiveData<List<TweetResponse>?>? {
        val data = MutableLiveData<List<TweetResponse>?>()

        api.getTweets(username)
                .enqueue(object : Callback<List<TweetResponse>> {
                    override fun onFailure(call: Call<List<TweetResponse>>?, t: Throwable?) {
                    }

                    override fun onResponse(call: Call<List<TweetResponse>>?, response: Response<List<TweetResponse>>?) {
                        if (response?.isSuccessful!!) {
                            data.value = response.body()
                        } else {
                            data.value = null
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
}