package br.com.andreyneto.ifood_mobile_test.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.andreyneto.ifood_mobile_test.R
import br.com.andreyneto.ifood_mobile_test.data.database.TweetEntry

class TweetAdapter(private val context: Context,
                   private var tweets: List<TweetEntry>,
                   private val clickHandler: (Long) -> Unit):
        RecyclerView.Adapter<TweetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tweet_item, parent, false)
        return TweetViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tweets.size
    }

    fun swapList(newTweets: List<TweetEntry>) {
        tweets = newTweets
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val note = tweets[position]
        holder.bind(note, clickHandler)
    }

}
