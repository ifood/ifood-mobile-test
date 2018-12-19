package com.example.tweetanalyzer.view.main

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tweetanalyzer.R
import com.example.tweetanalyzer.TweetAnalyzerApplication
import com.example.tweetanalyzer.model.Sentiment
import com.example.tweetanalyzer.util.TwitterTokenPreferences
import com.example.tweetanalyzer.viewmodel.CustomViewModelFactory
import com.example.tweetanalyzer.viewmodel.GoogleViewModel
import com.example.tweetanalyzer.viewmodel.TwitterViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_show_sentiment.*
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
            showSentimentPopUp(sentiment.score.toString(), sentiment)
        })

        verifyToken()

        rootSentimentLayout.visibility = View.GONE

        buttonFindTweets.setOnClickListener {
            val userName = inputUserName.text.toString()
            if (userName.isNotEmpty()){
                adapter.clear()
                twitterViewModel.searchTweetsByUserName(userName)
            }
        }

        buttonSentimentCard.setOnClickListener {
            hideSentimentPopUp()
        }

        //sendMessageProgressBar.indeterminateDrawable.setColorFilter(ContextCompat.getColor(context!!, R.color.white), PorterDuff.Mode.SRC_IN)
    }

    private fun showSentimentPopUp(tweet: String, sentiment: Sentiment){
        rootSentimentLayout.visibility = View.VISIBLE
        tweetSentimentCard.visibility = View.VISIBLE
        layoutSentimentCard.background = ContextCompat.getDrawable(this, sentiment.toColor())
        textSentiment.text = sentiment.toText()
        textSentimentTweet.text = tweet
    }

    private fun hideSentimentPopUp(){
        tweetSentimentCard.visibility = View.GONE
        rootSentimentLayout.visibility = View.GONE
    }

    private fun verifyToken(){
        if(twitterTokenPreferences.getToken().isEmpty()){
            twitterViewModel.getTwitterToken().observe(this, Observer {token->
                twitterTokenPreferences.updateToken(token.accessToken)
            })
        }
    }
}
