package com.spider.twitteranalyzer.feature.list.viewslice.list

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v7.widget.LinearLayoutManager
import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.base.viewslice.BaseViewSlice
import com.spider.twitteranalyzer.base.viewslice.ViewSlice
import com.spider.twitteranalyzer.feature.list.viewslice.list.adapter.TweetAdapter
import kotlinx.android.synthetic.main.activity_tweet_list.*
import javax.inject.Inject


interface ListViewSlice : ViewSlice {

    sealed class Action {
        data class TweetClicked(val tweet: Tweet) : Action()
    }

    fun getAction(): LiveData<Action>

    fun fillData(tweet: List<Tweet>)

    class Impl @Inject constructor(
        private val actionLiveData: MutableLiveData<Action>,
        private val layoutManager: LinearLayoutManager,
        private val adapter: TweetAdapter
    ) : BaseViewSlice(),
        ListViewSlice {

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate() {
            setUpRecyclerView()
        }

        private fun setUpRecyclerView() {
            tweets_recycler_view.layoutManager = layoutManager
            tweets_recycler_view.adapter = adapter
        }

        override fun getAction(): LiveData<Action> = actionLiveData

        override fun fillData(tweet: List<Tweet>) {
            adapter.setTweets(tweet)
        }
    }

}
