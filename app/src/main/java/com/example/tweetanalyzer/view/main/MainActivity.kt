package com.example.tweetanalyzer.view.main

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tweetanalyzer.R
import com.example.tweetanalyzer.TweetAnalyzerApplication
import com.example.tweetanalyzer.model.Sentiment
import com.example.tweetanalyzer.util.Status
import com.example.tweetanalyzer.util.TwitterTokenPreferences
import com.example.tweetanalyzer.util.hideKeyboard
import com.example.tweetanalyzer.util.onTextChange
import com.example.tweetanalyzer.viewmodel.GoogleViewModel
import com.example.tweetanalyzer.viewmodel.TwitterViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_show_sentiment.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: TweetsAdapter

    @Inject
    lateinit var twitterTokenPreferences: TwitterTokenPreferences

    @Inject
    lateinit var customViewModelFactory: ViewModelProvider.Factory
    private lateinit var twitterViewModel: TwitterViewModel
    private lateinit var googleViewModel: GoogleViewModel

    private lateinit var upToCenter: ObjectAnimator
    private lateinit var downToBotton: ObjectAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (this.application as TweetAnalyzerApplication).component.inject(this)

        initViews()

        createAnimations()

        configureClickListeners()

    }

    private fun initViews() {
        adapter = TweetsAdapter(this,
                onItemClick = { tweetText ->
                    rootSentimentLayout.visibility = View.VISIBLE
                    googleViewModel.analyzeTweet(tweetText)
                }
        )

        recyclerTweets.layoutManager = LinearLayoutManager(this)
        recyclerTweets.adapter = adapter

        twitterViewModel = ViewModelProviders.of(this, customViewModelFactory).get(TwitterViewModel::class.java)
        googleViewModel = ViewModelProviders.of(this, customViewModelFactory).get(GoogleViewModel::class.java)

        twitterViewModel.searchTweetsResult.observe(this, Observer { tweetsResources ->
            when(tweetsResources.status){
                Status.LOADING -> { recyclerTweets.showShimmerAdapter()}
                Status.SUCCESS -> {

                    if(tweetsResources.data.isNullOrEmpty()) showEmptyTweetList()
                    else adapter.updateTweets(tweetsResources.data)

                    recyclerTweets.hideShimmerAdapter()
                }
                Status.ERROR -> {
                    recyclerTweets.hideShimmerAdapter()
                    showSnackBar(resources.getString(tweetsResources.stringResource!!))
                }
            }

        })

        googleViewModel.sentimentResult.observe(this, Observer { sentimentResource ->
            when(sentimentResource.status){
                Status.LOADING -> { rootSentimentLayout.visibility = View.VISIBLE }
                Status.SUCCESS -> {
                    rootSentimentLayout.visibility = View.GONE
                    showSentimentPopUp(sentimentResource.data!!.tweetText, sentimentResource.data)
                }
                Status.ERROR -> {
                    rootSentimentLayout.visibility = View.GONE
                    showSnackBar(resources.getString(sentimentResource.stringResource!!))
                }
            }
        })

        inputUserName.onTextChange { clearTweetList() }
        verifyToken()
        rootSentimentLayout.visibility = View.GONE
    }

    private fun clearTweetList() {
        textEmpityList.visibility = View.GONE
        recyclerTweets.hideShimmerAdapter()
        adapter.clear()
    }

    private fun showEmptyTweetList() {
        adapter.clear()
        textEmpityList.visibility = View.VISIBLE
    }

    private fun configureClickListeners() {

        buttonFindTweets.setOnClickListener {
            this.hideKeyboard()
            clearTweetList()
            val userName = inputUserName.text.toString()

            if(twitterTokenPreferences.getToken().isEmpty()) verifyToken()
            else{
                if (userName.isNotEmpty()) {
                    twitterViewModel.searchTweetsByUserName(userName)
                }else{
                    showSnackBar(resources.getString(R.string.error_empty_field))
                }
            }
        }

        buttonSentimentCard.setOnClickListener {
            hideSentimentPopUp()
        }
    }

    private fun createAnimations() {
        upToCenter = ObjectAnimator
                .ofFloat(tweetSentimentCard, View.TRANSLATION_Y, 1000f, 0f)
                .setDuration(400)

        downToBotton = ObjectAnimator
                .ofFloat(tweetSentimentCard, View.TRANSLATION_Y, 0f, 1000f)
                .setDuration(300)
    }

    private fun slideAnimation() {
        progressCircularSentiment.visibility = View.GONE
        if(tweetSentimentCard.visibility != View.VISIBLE){
            upToCenter.start()
            tweetSentimentCard.visibility = View.VISIBLE
        }else {
            downToBotton.addListener(object : Animator.AnimatorListener{
                override fun onAnimationRepeat(p0: Animator?) {}

                override fun onAnimationEnd(p0: Animator?) {
                    tweetSentimentCard.visibility = View.GONE
                    rootSentimentLayout.visibility = View.GONE
                    progressCircularSentiment.visibility = View.VISIBLE
                }

                override fun onAnimationCancel(p0: Animator?) {}

                override fun onAnimationStart(p0: Animator?) {
                    Log.d("Popup", "Animation Started")
                }

            })
            downToBotton.start()
        }
    }

    private fun showSentimentPopUp(tweet: String, sentiment: Sentiment){
        rootSentimentLayout.visibility = View.VISIBLE
        layoutSentimentCard.background = ContextCompat.getDrawable(this, sentiment.toColor())
        textSentiment.text = sentiment.toText()
        textSentimentTweet.text = tweet
        slideAnimation()
    }

    private fun hideSentimentPopUp(){
        slideAnimation()
    }

    private fun showSnackBar(message: String){
        Snackbar.make(rootLayout, message, Snackbar.LENGTH_LONG).show()
    }

    private fun verifyToken(){
        if(twitterTokenPreferences.getToken().isEmpty()){
            twitterViewModel.getTwitterToken().observe(this, Observer {tokenResource->
                when (tokenResource.status){
                    Status.LOADING -> {}
                    Status.SUCCESS -> {
                        twitterTokenPreferences.updateToken(tokenResource.data!!.accessToken)
                    }
                    Status.ERROR -> {
                        showSnackBar(resources.getString(tokenResource.stringResource!!))
                    }
                }
            })
        }
    }
}
