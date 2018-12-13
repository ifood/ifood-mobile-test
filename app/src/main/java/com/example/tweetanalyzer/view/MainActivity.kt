package com.example.tweetanalyzer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tweetanalyzer.R
import com.example.tweetanalyzer.api.google.GoogleCNLApi
import com.example.tweetanalyzer.api.twitter.TwitterApi
import com.example.tweetanalyzer.model.SentimentResponse
import com.example.tweetanalyzer.model.Tweet
import com.example.tweetanalyzer.model.TweetResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val twitterApi = TwitterApi()
    private val googleCNLApi = GoogleCNLApi()
    private val tweetList = mutableListOf<Tweet>()

    private lateinit var adapter: TweetsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = TweetsAdapter(this){ tweetText->
            analizeTweet(tweetText)
        }

        recyclerTweets.layoutManager = LinearLayoutManager(this)
        recyclerTweets.adapter = adapter

        buttonFindTweets.setOnClickListener {
            val userName = inputUserName.text.toString()
            if (userName.isNotEmpty()){
                adapter.clear()
                twitterApi.getTweetsByUserName(userName).enqueue(object : Callback<TweetResponse> {
                    override fun onFailure(call: Call<TweetResponse>, t: Throwable) {
                        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<TweetResponse>, response: Response<TweetResponse>) {
                        val tweetResponse = response.body() as TweetResponse
                        tweetList.clear()
                        tweetList.addAll(tweetResponse.tweets)
                        adapter.updateTweets(tweetList)
                    }

                })
            }
        }

    }

    private fun analizeTweet(tweetText: String) {
        if (tweetText.isNotEmpty()){
            googleCNLApi.sendTextToSentimentAnalize(tweetText).enqueue(object : Callback<SentimentResponse>{
                override fun onFailure(call: Call<SentimentResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<SentimentResponse>, response: Response<SentimentResponse>) {
                    val sentimentResponse = response.body() as SentimentResponse
                    Toast.makeText(this@MainActivity, sentimentResponse.documentSentiment.score.toString(), Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}
