package com.study.vipoliveira.tweetanalyst.ui.tweet

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.study.vipoliveira.tweetanalyst.R
import com.study.vipoliveira.tweetanalyst.model.TweetResponse

class TweetsListAdapter (val items : MutableList<TweetResponse>) : RecyclerView.Adapter<TweetListViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TweetListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_tweet_item, parent, false)
        return TweetListViewHolder(view)
    }
}