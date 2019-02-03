package com.spider.twitteranalyzer.feature.list.viewslice.list.adapter

import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.feature.list.R
import com.spider.twitteranalyzer.feature.list.viewslice.list.ListViewSlice
import javax.inject.Inject


abstract class TweetAdapter : RecyclerView.Adapter<TweetViewHolder>() {

    abstract fun setTweets(tweets: List<Tweet>)

    abstract fun addTweets(tweets: List<Tweet>)

    abstract fun clearTweets()

    class Impl @Inject constructor(
        private val actionLiveData: MutableLiveData<ListViewSlice.Action>
    ) : TweetAdapter() {


        private var tweets: MutableList<Tweet> = mutableListOf()

        override fun getItemViewType(position: Int) = R.layout.view_holder_tweet

        override fun getItemCount() = tweets.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_tweet, parent, false)
            return TweetViewHolder(itemView, actionLiveData)
        }

        override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
            holder.bind(tweets[position])
        }

        override fun setTweets(tweets: List<Tweet>) {
            addTweets(tweets)
        }

        override fun clearTweets() {
            this.tweets.clear()
        }

        override fun addTweets(tweets: List<Tweet>) {
            this.tweets = tweets.toMutableList()
            notifyDataSetChanged()
        }
    }
}
