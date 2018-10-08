package com.study.vipoliveira.tweetanalyst.ui.tweet

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.vipoliveira.tweetanalyst.R
import com.study.vipoliveira.tweetanalyst.domain.model.TweetResponse
import com.study.vipoliveira.tweetanalyst.ui.utils.Utils
import kotlinx.android.synthetic.main.layout_tweet_item.view.*

class TweetsListAdapter (val items : MutableList<TweetResponse>, val listener: (TweetResponse) -> Unit) : RecyclerView.Adapter<TweetsListAdapter.TweetListViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TweetListViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    fun getAdapterItems(): List<TweetResponse> {
        return items.toList()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_tweet_item, parent, false)
        return TweetListViewHolder(view)
    }

    fun updateItem(data: TweetResponse) {
        val index = items.indexOfFirst { it.id == data.id }
        items[index] = data
        notifyItemChanged(index)
    }

    fun setTweets(newItems: MutableList<TweetResponse>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class TweetListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: TweetResponse, listener: (TweetResponse) -> Unit) = with(itemView) {
            with(item){
                tweet_description.text = text
                tweet_date.text = Utils.formatTimeStamp(createdAt)
                sentiment?.let {tweet_analyzer.text = Utils.defineSentiment(sentiment)} ?: run {tweet_analyzer.text = resources.getString(R.string.analyze_tweet)}
                setOnClickListener { listener(item) }
            }
        }
    }
}