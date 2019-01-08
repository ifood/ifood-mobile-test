package com.drury.twittermoodanalyzer.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.drury.twittermoodanalyzer.R
import com.drury.twittermoodanalyzer.model.TweetModel
import com.twitter.sdk.android.core.models.Tweet
import kotlinx.android.synthetic.main.tweet_item.view.*

class TweetAdapter(var dataset: MutableList<TweetModel>, var context: Context) :
        RecyclerView.Adapter<TweetAdapter.TweetViewHolder>() {

    class TweetViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(tweet: TweetModel) {
            view.textViewTweet.text = tweet.text
            view.textViewDate.text = tweet.created
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): TweetAdapter.TweetViewHolder {

        val view = LayoutInflater.from(context)
                .inflate(R.layout.tweet_item, parent, false)

        return TweetViewHolder(view)
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() = dataset.size

    fun updateData(data: List<TweetModel>) {
        dataset.clear()
        dataset.addAll(data)
        notifyDataSetChanged()
    }
}