package br.com.matheusbrandino.ifoodtest.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.matheusbrandino.ifoodtest.R
import br.com.matheusbrandino.ifoodtest.model.Tweet
import kotlinx.android.synthetic.main.tweet_item.view.*

class TweetAdapter(private val tweets: List<Tweet>, private val listener: (Tweet) -> Unit) :
    RecyclerView.Adapter<TweetAdapter.ViewHolder>() {


    override fun getItemCount(): Int = tweets.size


    override fun onCreateViewHolder(group: ViewGroup, type: Int): ViewHolder {
        val inflater = LayoutInflater.from(group.context)
        val view = inflater.inflate(R.layout.tweet_item, group, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tweet = tweets[position]
        holder.bind(tweet)
    }

    class ViewHolder(
        itemView: View,
        val listener: (Tweet) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(tweet: Tweet) {
            itemView.itemTweetContent.text = tweet.text
            itemView.btnAnalyze.setOnClickListener { listener(tweet) }
        }

    }

}
