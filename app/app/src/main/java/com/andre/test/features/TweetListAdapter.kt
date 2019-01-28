package com.andre.test.features

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andre.test.R
import com.twitter.sdk.android.core.models.Tweet
import kotlinx.android.synthetic.main.tweet_item_layout.view.*

class TweetListAdapter(private val clickListener: (Tweet) -> Unit) :
        ListAdapter<Tweet, TweetListAdapter.ViewHolder>(TweetDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TweetListAdapter.ViewHolder(inflater.inflate(R.layout.tweet_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: TweetListAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tweet: Tweet, clickListener: (Tweet) -> Unit) {
            itemView.user.text = tweet.user.name
            itemView.message.text = tweet.text
            itemView.setOnClickListener { clickListener(tweet)}
        }
    }
}