package com.andre.test.features

import androidx.recyclerview.widget.DiffUtil
import com.twitter.sdk.android.core.models.Tweet

class TweetDiffCallback : DiffUtil.ItemCallback<Tweet>() {

    override fun areContentsTheSame(oldItem: Tweet, newItem: Tweet): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: Tweet, newItem: Tweet): Boolean {
        return oldItem == newItem
    }
}