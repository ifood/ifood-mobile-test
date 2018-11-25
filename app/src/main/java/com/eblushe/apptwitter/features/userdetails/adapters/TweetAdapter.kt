package com.eblushe.apptwitter.features.userdetails.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eblushe.apptwitter.R
import com.eblushe.apptwitter.common.models.Tweet
import kotlinx.android.synthetic.main.adapter_user_tweet.view.*

class TweetAdapter(var items: List<Tweet>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val layoutId: Int = R.layout.adapter_user_tweet
    var onItemClick: (tweet: Tweet) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(viewItem = view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        holder.itemView.textTextView.text = item.text
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    // ViewHolder

    class ViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem)
}