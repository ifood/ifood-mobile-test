package com.drss.ifoodmobiletest.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drss.ifoodmobiletest.R
import com.drss.ifoodmobiletest.utils.formatTweetDate
import com.drss.ifoodmobiletest.view.SentimentActivity
import com.twitter.sdk.android.core.models.Tweet
import kotlinx.android.synthetic.main.tweet_item_layout.view.*
import java.util.*

class TweetsRecyclerViewAdapter: RecyclerView.Adapter<TweetsRecyclerViewAdapter.TweetViewHolder>() {

    var tweetList: List<Tweet> = emptyList()

    fun setData(items: List<Tweet>) {
        tweetList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetsRecyclerViewAdapter.TweetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TweetViewHolder(inflater.inflate(R.layout.tweet_item_layout, parent, false))
    }

    override fun getItemCount(): Int = tweetList.size

    override fun onBindViewHolder(holder: TweetsRecyclerViewAdapter.TweetViewHolder, position: Int) {
        holder.bind(tweetList[position])
    }

    class TweetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tweet: Tweet) {
            itemView.tweet_text.text = tweet.text
            itemView.tweet_date.text = tweet.createdAt.formatTweetDate()

            itemView.setOnClickListener {
                val intent = Intent(it.context, SentimentActivity::class.java)
                intent.putExtra("TWEET_TEXT", tweet.text)
                intent.putExtra("TWEET_DATE", tweet.createdAt.formatTweetDate())
                it.context.startActivity(intent)
            }
        }
    }
}