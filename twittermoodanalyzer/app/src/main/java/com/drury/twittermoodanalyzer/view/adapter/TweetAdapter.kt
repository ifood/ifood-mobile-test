package com.drury.twittermoodanalyzer.view.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.drury.twittermoodanalyzer.R
import com.drury.twittermoodanalyzer.extension.toSimpleString
import com.drury.twittermoodanalyzer.model.TweetModel
import com.drury.twittermoodanalyzer.utils.AppConstants
import com.drury.twittermoodanalyzer.view.MoodActivity
import kotlinx.android.synthetic.main.tweet_item.view.*
import java.util.*

class TweetAdapter(var dataset: MutableList<TweetModel>, var activity: Activity) :
        RecyclerView.Adapter<TweetAdapter.TweetViewHolder>() {

    class TweetViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(tweet: TweetModel) {
            view.textViewTweet.text = tweet.text
            view.textViewDate.text = Date(tweet.created).toSimpleString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): TweetAdapter.TweetViewHolder {

        val view = LayoutInflater.from(activity)
                .inflate(R.layout.tweet_item, parent, false)

        return TweetViewHolder(view).listen { pos, type ->
            val item = dataset[pos]
            val intent = Intent(activity, MoodActivity::class.java).putExtra(AppConstants.TWEET, item)
            activity.startActivity(intent)
        }
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() = dataset.size
}

fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(adapterPosition, itemViewType)
    }
    return this
}