package com.example.tweetanalyzer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tweetanalyzer.R
import com.example.tweetanalyzer.api.twitter.TwitterApi
import com.example.tweetanalyzer.data.TweetResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val twiterrApi = TwitterApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonFindTweets.setOnClickListener {
            val userName = inputUserName.text.toString()
            if (userName.isNotEmpty()){
                tweets.text = ""
                twiterrApi.getTweetsByUserName(userName).enqueue(object : Callback<TweetResponse> {
                    override fun onFailure(call: Call<TweetResponse>, t: Throwable) {
                        tweets.text = t.message
                    }

                    override fun onResponse(call: Call<TweetResponse>, response: Response<TweetResponse>) {
                        val tweetResponse = response.body() as TweetResponse
                        tweetResponse.tweets.forEach {
                            tweets.text = "${tweets.text} \n ${it.text}"
                        }
                    }

                })
            }
        }

    }
}
