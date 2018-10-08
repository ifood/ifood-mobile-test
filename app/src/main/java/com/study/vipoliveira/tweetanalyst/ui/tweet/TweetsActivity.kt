package com.study.vipoliveira.tweetanalyst.ui.tweet

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.study.vipoliveira.tweetanalyst.R
import com.study.vipoliveira.tweetanalyst.model.TweetResponse
import com.study.vipoliveira.tweetanalyst.ui.viewentity.TweetsResponse
import com.study.vipoliveira.tweetanalyst.utils.SEARCH
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
        TweetsListAdapter(tweetsList)
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

        viewModel!!.tweetsResponse().observe(this,
                Observer { tweetResponse -> processTweetsResponse(tweetResponse!!) })

        val linearLayoutManager = LinearLayoutManager(this)
        tweet_recyclerview.layoutManager = linearLayoutManager
        tweet_recyclerview.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(tweet_recyclerview.context,
                linearLayoutManager.orientation)
        tweet_recyclerview.addItemDecoration(dividerItemDecoration)
        viewModel!!.getTweets(searchName!!)
    }

    private fun processTweetsResponse(tweetsResponse: TweetsResponse) {
        error_msg_txt.visibility = if (tweetsResponse.error != null) View.VISIBLE else View.GONE
        if (tweetsResponse.error?.message != null) {
            error_msg_txt.text = tweetsResponse.error.message
        }

        retry_button.visibility = if (tweetsResponse.status == TweetsResponse.Status.ERROR) View.VISIBLE else View.GONE
        loading_progress_bar.visibility = if (tweetsResponse.status == TweetsResponse.Status.LOADING) View.VISIBLE else View.GONE

        if(tweetsResponse.status == TweetsResponse.Status.SUCCESS){
            if(tweetsResponse.data!!.isEmpty()){
                error_msg_txt.visibility = View.VISIBLE
//                error_msg_txt.text = getString(R.string.no_pull_request)
            } else {
                tweetsList.clear()
                tweetsList.addAll(tweetsResponse.data)
                adapter.notifyDataSetChanged()
            }
        }
        retry_button.setOnClickListener { viewModel!!.getTweets(searchName!!)}
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}