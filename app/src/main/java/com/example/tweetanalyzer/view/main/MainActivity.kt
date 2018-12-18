package com.example.tweetanalyzer.view.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tweetanalyzer.R
import com.example.tweetanalyzer.TweetAnalyzerApplication
import com.example.tweetanalyzer.util.TwitterTokenPreferences
import com.example.tweetanalyzer.viewmodel.CustomViewModelFactory
import com.example.tweetanalyzer.viewmodel.GoogleViewModel
import com.example.tweetanalyzer.viewmodel.TwitterViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: TweetsAdapter

    @Inject
    lateinit var twitterTokenPreferences: TwitterTokenPreferences

    @Inject
    lateinit var customViewModelFactory: CustomViewModelFactory
    private lateinit var twitterViewModel: TwitterViewModel
    private lateinit var googleViewModel: GoogleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (this.application as TweetAnalyzerApplication).component.inject(this)

        adapter = TweetsAdapter(this,
                onItemClick = { tweetText -> googleViewModel.analyzeTweet(tweetText) }
        )

        recyclerTweets.layoutManager = LinearLayoutManager(this)
        recyclerTweets.adapter = adapter

        twitterViewModel = ViewModelProviders.of(this, customViewModelFactory).get(TwitterViewModel::class.java)
        googleViewModel = ViewModelProviders.of(this, customViewModelFactory).get(GoogleViewModel::class.java)

        twitterViewModel.searchTweetsResult.observe(this, Observer { tweets->
            adapter.updateTweets(tweets)
        })

        googleViewModel.sentimentResult.observe(this, Observer { sentiment ->
            Toast.makeText(this@MainActivity, sentiment.score.toString(), Toast.LENGTH_SHORT).show()
        })

        verifyToken()

        buttonFindTweets.setOnClickListener {
            val userName = inputUserName.text.toString()
            if (userName.isNotEmpty()){
                adapter.clear()
                twitterViewModel.searchTweetsByUserName(userName)
            }
        }

    }

    private fun verifyToken(){
        if(twitterTokenPreferences.getToken().isEmpty()){
            twitterViewModel.getTwitterToken().observe(this, Observer {token->
                twitterTokenPreferences.updateToken(token.accessToken)
            })
        }
    }
}
