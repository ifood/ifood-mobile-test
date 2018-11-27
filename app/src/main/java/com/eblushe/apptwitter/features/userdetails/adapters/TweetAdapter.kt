package com.eblushe.apptwitter.features.userdetails.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eblushe.apptwitter.R
import com.eblushe.apptwitter.common.models.Tweet
import kotlinx.android.synthetic.main.adapter_user_tweet.view.*

class TweetAdapter(var items: List<Tweet>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var onItemClick: (tweet: Tweet) -> Unit = {}

    private val VIEW_TYPE_DATE = 1
    private val VIEW_TYPE_EMPTY = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutId = getLayoutByType(viewType)
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(viewItem = view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (items.isEmpty()) return

        val item = items[position]
        holder.itemView.textTextView.text = item.text
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        if (items.isEmpty()) return 1

        return items.size
    }

    override fun getItemViewType(position: Int) : Int {
        return if (items.isEmpty()) VIEW_TYPE_EMPTY else VIEW_TYPE_DATE
    }

    private fun getLayoutByType(viewType: Int) : Int {
        return if(viewType == VIEW_TYPE_DATE)
            R.layout.adapter_user_tweet
        else
            R.layout.view_no_results
    }

    // ViewHolder

    class ViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem)
}