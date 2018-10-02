package br.com.andreyneto.ifood_mobile_test.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.andreyneto.ifood_mobile_test.data.database.TweetEntry
import kotlinx.android.synthetic.main.tweet_item.view.*

class TweetViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView) {
    fun bind(tweet: TweetEntry, clickListener: (Long) -> Unit) {
        itemView.textView.text = tweet.text
        itemView.view.visibility = if (tweet.sentimentChecked) View.VISIBLE else View.GONE
        itemView.setOnClickListener { clickListener(tweet.tweetID)}
    }
}