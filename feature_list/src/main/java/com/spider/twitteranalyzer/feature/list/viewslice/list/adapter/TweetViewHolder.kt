package com.spider.twitteranalyzer.feature.list.viewslice.list.adapter

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.base.view.adapter.BaseViewHolder
import com.spider.twitteranalyzer.feature.list.viewslice.list.ListViewSlice
import kotlinx.android.synthetic.main.view_holder_tweet.*


class TweetViewHolder constructor(
    itemView: View,
    private val actionLiveData: MutableLiveData<ListViewSlice.Action>
) : BaseViewHolder<Tweet>(itemView) {

    override fun bind(data: Tweet) {
        tweet_date.text = data.createdAt
        tweet_text.text = data.text
        tweet_user.text = data.user

        setViewClickListener(actionLiveData, data)
    }

    private fun setViewClickListener(actionLiveData: MutableLiveData<ListViewSlice.Action>, data: Tweet) {
        tweet_container.setOnClickListener { actionLiveData.value = ListViewSlice.Action.TweetClicked(data) }
    }

}
