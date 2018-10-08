package com.study.vipoliveira.tweetanalyst.ui.tweet

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.study.vipoliveira.tweetanalyst.R
import com.study.vipoliveira.tweetanalyst.domain.model.TweetResponse
import com.study.vipoliveira.tweetanalyst.ui.utils.SEARCH
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.layout_network_state.*
import kotlinx.android.synthetic.main.tweet_activity.*
import javax.inject.Inject

class TweetsActivity : AppCompatActivity() {

    var viewModelFactory: TweetsViewModelFactory? = null
    @Inject set

    private var viewModel: TweetsViewModel? = null
    private val tweetsList: MutableList<TweetResponse> = mutableListOf()
    private val adapter: TweetsListAdapter by lazy {
        TweetsListAdapter(tweetsList) {
            viewModel!!.analyzeTweet(it)
        }
    }

    private var searchName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tweet_activity)
        setSupportActionBar(findViewById(R.id.toolbar_pr))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        searchName = intent.getCharSequenceExtra(SEARCH).toString()
        title = searchName
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TweetsViewModel::class.java)

        setupObservers()

        initAdapter()
        viewModel!!.getTweets(searchName!!)
    }

    private fun setupObservers() {
        viewModel!!.isLoading().observe(this, Observer {showLoader(it) })
        viewModel!!.googleData().observe(this, Observer { processGoogleResponse(it!!) })
        viewModel!!.tweetData().observe(this, Observer { processTweetsResponse(it!!) })
        viewModel!!.tweetError().observe(this, Observer { processTweetsError(it!!) })
        viewModel!!.googleError().observe(this, Observer { processGoogleError() })
        viewModel!!.userNotFound().observe(this, Observer { userNotFound(it) })
    }

    private fun userNotFound(userNotFound: Boolean?) {
        when(userNotFound){
            true -> {
                error_msg_txt.visibility = View.VISIBLE
                error_msg_txt.text = getString(R.string.no_user)
                loading_progress_bar.visibility = View.GONE
            }
            false -> error_msg_txt.visibility = View.GONE


        }
    }

    private fun processGoogleError() {
        showLoader(false)
        Toast.makeText(this, getString(R.string.analysis_error), Toast.LENGTH_SHORT).show()
    }

    private fun processTweetsError(throwable: Throwable) {
        error_msg_txt.visibility = View.VISIBLE
        retry_button.visibility = View.VISIBLE
        throwable.message.let { error_msg_txt.text = throwable.message }
        retry_button.setOnClickListener { viewModel!!.getTweets(searchName!!)}
    }

    private fun noError(){
        error_msg_txt.visibility = View.GONE
        retry_button.visibility = View.GONE
    }

    private fun showLoader(loading: Boolean?) {
        noError()
        when(loading){
            false -> loading_progress_bar.visibility = View.GONE
            true -> loading_progress_bar.visibility = View.VISIBLE
        }
    }

    private fun initAdapter() {
        val linearLayoutManager = LinearLayoutManager(this)
        tweet_recyclerview.layoutManager = linearLayoutManager
        tweet_recyclerview.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(tweet_recyclerview.context,
                linearLayoutManager.orientation)
        tweet_recyclerview.addItemDecoration(dividerItemDecoration)
    }

    private fun processGoogleResponse(googleResponse: TweetResponse) {
        showLoader(false)
        adapter.updateItem(googleResponse)
    }

    private fun processTweetsResponse(tweetsResponse: MutableList<TweetResponse>) {
        noError()
        showLoader(false)
        if(tweetsResponse.isEmpty()){
            error_msg_txt.visibility = View.VISIBLE
            error_msg_txt.text = getString(R.string.no_tweets)
        } else {
            adapter.setTweets(tweetsResponse)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}