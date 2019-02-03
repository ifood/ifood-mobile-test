package com.spider.twitteranalyzer.feature.list.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.base.domain.model.Tweet.Companion.EXTRA_TWEET
import com.spider.twitteranalyzer.base.extension.getContentView
import com.spider.twitteranalyzer.base.extension.observe
import com.spider.twitteranalyzer.base.view.BaseActivity
import com.spider.twitteranalyzer.base.view.ScreenRouter
import com.spider.twitteranalyzer.base.view.ScreenRouter.Screen.Detail
import com.spider.twitteranalyzer.feature.list.R
import com.spider.twitteranalyzer.feature.list.viewmodel.ListViewModel
import com.spider.twitteranalyzer.feature.list.viewmodel.ListViewModel.State
import com.spider.twitteranalyzer.feature.list.viewslice.list.ListViewSlice
import com.spider.twitteranalyzer.feature.list.viewslice.search.SearchViewSlice
import com.spider.twitteranalyzer.feature.list.viewslice.state.StateViewSlice
import javax.inject.Inject


@SuppressLint("Registered")
class TweetsListActivity(override val layoutResourceId: Int = R.layout.activity_tweet_list) : BaseActivity() {

    @Inject
    lateinit var screenRouter: ScreenRouter
    @Inject
    lateinit var viewModel: ListViewModel
    @Inject
    lateinit var stateViewSlice: StateViewSlice
    @Inject
    lateinit var listViewSlice: ListViewSlice
    @Inject
    lateinit var searchViewSlice: SearchViewSlice

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        initViewSlices()
        setUpViewSliceActionObservers()
        setUpViewModelStateObservers()
    }

    private fun initToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val item = menu.findItem(R.id.action_search)
        searchViewSlice.setMenuItem(item)
        return true
    }

    private fun initViewSlices() {
        stateViewSlice.init(lifecycle, getContentView())
        listViewSlice.init(lifecycle, getContentView())
        searchViewSlice.init(lifecycle, getContentView())
    }

    private fun setUpViewSliceActionObservers() {
        observe(listViewSlice.getAction()) { onActionChanged(it) }
        observe(searchViewSlice.getAction()) { onActionChanged(it) }
    }

    private fun onActionChanged(action: ListViewSlice.Action) = when (action) {
        is ListViewSlice.Action.TweetClicked -> startTweetsDetailActivity(action.tweet)
    }

    private fun onActionChanged(action: SearchViewSlice.Action) = when (action) {
        is SearchViewSlice.Action.UserNameSubmitted -> viewModel.fetchTweets(action.userName)
    }

    private fun startTweetsDetailActivity(tweet: Tweet) {
        screenRouter.getScreenIntent(this, Detail)
            ?.apply { putExtra(EXTRA_TWEET, tweet) }
            ?.run { startActivity(this) }
    }

    private fun setUpViewModelStateObservers() {
        observe(viewModel.getState()) { onStateChanged(it) }
    }

    private fun onStateChanged(state: State) {
        return when (state) {
            is State.TweetsLoaded -> {
                listViewSlice.fillData(state.tweets)
                stateViewSlice.showTweets()
            }
            is State.ShowLoading -> stateViewSlice.showLoading()
            is State.TweetsLoadedEmpty -> stateViewSlice.showEmpty()
            is State.ShowError -> stateViewSlice.showError(state.throwable)
        }
    }
}
