package com.study.vipoliveira.tweetanalyst.ui.tweet

import android.support.v7.widget.RecyclerView
import android.view.View
import com.study.vipoliveira.tweetanalyst.model.TweetResponse
import kotlinx.android.synthetic.main.layout_tweet_item.view.*

class TweetListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: TweetResponse) = with(itemView) {
        with(item){
            tweet_description.text = text
            tweet_date.text = createdAt
            tweet_analyzer.setOnClickListener {  }
        }
    }
}