package com.rlino.ifoodtwitterchallenge.ui.timelinesearch

import android.support.v4.view.PagerAdapter
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rlino.ifoodtwitterchallenge.R
import com.rlino.ifoodtwitterchallenge.model.Tweet
import kotlinx.android.synthetic.main.tweet_item.view.*

class TweetsAdapter(
        val onClick: (tweet: Tweet) -> Unit
) : ListAdapter<Tweet, TweetViewHolder>(TweetDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tweet_item, parent, false)
        val vh = TweetViewHolder(view)

        vh.itemView.setOnClickListener {
            val pos = vh.adapterPosition
            if(pos != PagerAdapter.POSITION_NONE) {
                onClick(getItem(pos))
            }
        }

        return vh
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class TweetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(tweet: Tweet) {
        itemView.apply {
            tweetId.text = tweet.id.toString()
            tweetText.text = tweet.text
        }
    }

}

object TweetDiffCallback : DiffUtil.ItemCallback<Tweet>() {

    override fun areContentsTheSame(oldItem: Tweet?, newItem: Tweet?): Boolean {
        if(oldItem != null && newItem != null) {
            return oldItem.text == newItem.text && oldItem.createdAt == newItem.createdAt
        }
        return false
    }

    override fun areItemsTheSame(oldItem: Tweet?, newItem: Tweet?): Boolean {
        if(oldItem != null && newItem != null) {
            return oldItem.id == newItem.id
        }
        return false
    }

}