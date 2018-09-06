package com.rlino.ifoodtwitterchallenge.ui.timelinesearch

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import com.rlino.ifoodtwitterchallenge.R
import com.rlino.ifoodtwitterchallenge.model.Sentiment
import com.rlino.ifoodtwitterchallenge.model.Tweet
import com.rlino.ifoodtwitterchallenge.ui.*
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

        viewModel.snackbarMessage.observe(this, EventObserver {
            Snackbar.make(container, it, Snackbar.LENGTH_SHORT).show()
        })

        viewModel.isLoading.observe(this, Observer {
            when(it) {
                true -> container.showLoadingOverlay()
                false -> container.hideLoadingOverlay()
            }
        })

        viewModel.tweetSentiment.observe(this, EventObserver{
            showSentiment(it)
        })

        tweetsList.layoutManager = layoutManager
        tweetsList.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val myActionMenuItem = menu.findItem(R.id.action_search)
        val searchView = myActionMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchTweetsForUsername(query)
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })
        return true
    }

    private fun tweetClicked(tweet: Tweet) {
        viewModel.analyzeTweet(tweet.text)
    }

    private fun showSentiment(sentiment: Sentiment) {
        sentiment.apply {
            emojiField.text = emoji.toEmojiString()
            sentimentIndicatorLayout.setBackgroundColor(Color.parseColor(color))
            sentimentIndicatorLayout.blink()
        }
    }
}