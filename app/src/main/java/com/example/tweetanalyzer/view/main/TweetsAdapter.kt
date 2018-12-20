package com.example.tweetanalyzer.view.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tweetanalyzer.R
import com.example.tweetanalyzer.model.Tweet

class TweetsAdapter(context: Context, private val onItemClick: (tweetText:String)->Unit = {}) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val tweets = mutableListOf<Tweet>()
    private val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = layoutInflater.inflate(R.layout.item_tweet, parent, false)
        return TweetItemViewHolder(view)
    }

    override fun getItemCount(): Int = tweets.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as TweetItemViewHolder
        viewHolder.render(tweets[position], onItemClick)
    }

    fun updateTweets(tweetList: List<Tweet>){
        this.tweets.clear()
        this.tweets.addAll(tweetList)
        notifyDataSetChanged()
    }

    fun clear(){
        this.tweets.clear()
        notifyDataSetChanged()
    }

    inner class TweetItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val rootView:View = itemView.findViewById(R.id.rootViewTweet)
        private val tweetText:TextView = itemView.findViewById(R.id.textTweet)

        fun render(tweet: Tweet, onItemClick: (tweetText:String) -> Unit){
            tweetText.text = tweet.text
            rootView.setOnClickListener { onItemClick(tweet.text) }
        }
    }
}