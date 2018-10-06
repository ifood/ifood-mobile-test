package com.test.ifood.twitterhumour.tweetlist.adapter

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.ifood.twitterhumour.R
import com.test.ifood.twitterhumour.base.BaseActivity
import com.test.ifood.twitterhumour.databinding.ItemTweetBinding
import com.test.ifood.twitterhumour.model.Tweet
import com.test.ifood.twitterhumour.tweetlist.viewmodel.ItemTweetListViewModel
import com.test.ifood.twitterhumour.tweetlist.viewmodel.factory.ItemTweetListViewModelFactory

class TweetListAdapter(private val activity: BaseActivity, private val tweets: List<Tweet>):
        RecyclerView.Adapter<TweetListAdapter.TweetListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetListViewHolder {
        val binding = DataBindingUtil.inflate<ItemTweetBinding>(LayoutInflater.from(activity),
                R.layout.item_tweet, parent, false)

        return TweetListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tweets.size
    }

    override fun onBindViewHolder(holder: TweetListViewHolder, position: Int) {
        val tweet = tweets.get(position)

        holder.itemViewBinding.viewModel = ViewModelProviders
                .of(activity, ItemTweetListViewModelFactory(activity.application, tweet))
                .get(position.toString(), ItemTweetListViewModel::class.java)
    }

    class TweetListViewHolder(val itemViewBinding: ItemTweetBinding) : RecyclerView.ViewHolder(itemViewBinding.root)
}