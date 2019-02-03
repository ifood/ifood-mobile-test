package com.spider.twitteranalyzer.feature.detail.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.base.extension.getContentView
import com.spider.twitteranalyzer.base.extension.observe
import com.spider.twitteranalyzer.base.view.BaseActivity
import com.spider.twitteranalyzer.feature.detail.R
import com.spider.twitteranalyzer.feature.detail.viewmodel.DetailViewModel
import com.spider.twitteranalyzer.feature.detail.viewslice.detail.DetailViewSlice
import com.spider.twitteranalyzer.feature.detail.viewslice.state.StateViewSlice
import javax.inject.Inject

@SuppressLint("Registered")
class TweetDetailActivity constructor(override val layoutResourceId: Int = R.layout.activity_tweet_detail) :
    BaseActivity() {

    @Inject
    lateinit var viewModel: DetailViewModel
    @Inject
    lateinit var stateViewSlice: StateViewSlice
    @Inject
    lateinit var detailViewSlice: DetailViewSlice

    private val tweet: Tweet? by lazy {
        intent?.extras?.getParcelable(Tweet.EXTRA_TWEET) as? Tweet?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        initViewSlices()
        setUpViewSliceActionObservers()
        setUpViewModelStateObservers()
        fillData()
    }

    private fun fillData() {
        detailViewSlice.fillTweetDetail(tweet)
    }

    private fun initToolbar() {
        val toolbar =
            findViewById<Toolbar>(R.id.toolbar).apply { setNavigationIcon(R.drawable.ic_action_up_navigation) }
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initViewSlices() {
        stateViewSlice.init(lifecycle, getContentView())
        detailViewSlice.init(lifecycle, getContentView())
    }

    private fun setUpViewSliceActionObservers() {
        observe(detailViewSlice.getAction()) { onActionChanged(it) }
    }

    private fun onActionChanged(action: DetailViewSlice.Action) = when (action) {
        is DetailViewSlice.Action.AnalyzeTweet -> viewModel.analyzeTweet(action.tweet)
    }

    private fun setUpViewModelStateObservers() {
        observe(viewModel.getState()) { onStateChanged(it) }
    }

    private fun onStateChanged(state: DetailViewModel.State) = when (state) {
        is DetailViewModel.State.TweetAnalyzeDone -> {
            detailViewSlice.fillSentiment(state.sentiment)
            stateViewSlice.showContent()
        }
        is DetailViewModel.State.ShowLoading -> stateViewSlice.showLoading()
        is DetailViewModel.State.ShowError -> stateViewSlice.showError(state.throwable)
    }
}