package com.rlino.ifoodtwitterchallenge.ui.timelinesearch

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.rlino.ifoodtwitterchallenge.R
import com.rlino.ifoodtwitterchallenge.data.google.SentimentType
import com.rlino.ifoodtwitterchallenge.model.Tweet
import com.rlino.ifoodtwitterchallenge.ui.blink
import com.rlino.ifoodtwitterchallenge.ui.hideLoadingOverlay
import com.rlino.ifoodtwitterchallenge.ui.showLoadingOverlay
import com.rlino.ifoodtwitterchallenge.ui.toEmojiString
import kotlinx.android.synthetic.main.activity_timeline_search.*

class TimelineSearchActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(TimelineSearchViewModel::class.java) }

    private val layoutManager by lazy { LinearLayoutManager(this) }

    private val adapter by lazy { TweetsAdapter(::tweetClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline_search)

        viewModel.tweets.observe(this, Observer {
            it?.apply {
                adapter.submitList(tweets)
            }
        })

        viewModel.snackbarMessage.observe(this, Observer {
            it?.apply { Snackbar.make(container, it, Snackbar.LENGTH_SHORT).show() }
        })

        viewModel.isLoading.observe(this, Observer {
            when(it) {
                true -> container.showLoadingOverlay()
                false -> container.hideLoadingOverlay()
            }
        })

        viewModel.tweetMeaning.observe(this, Observer {
            it?.apply {
                showSentiment(this)
            }
        })

        search.setOnClickListener {
            viewModel.searchTweetsForUsername(usernameField.toString())
        }

        tweetsList.layoutManager = layoutManager
        tweetsList.adapter = adapter
    }

    private fun tweetClicked(tweet: Tweet) {
        viewModel.analyzeTweet(tweet.text)
    }

    private fun showSentiment(sentiment: SentimentType) {
        sentiment.apply {
            emojiField.text = emoji.toEmojiString()
            sentimentIndicatorLayout.setBackgroundColor(Color.parseColor(color))
            sentimentIndicatorLayout.blink()
        }
    }
}