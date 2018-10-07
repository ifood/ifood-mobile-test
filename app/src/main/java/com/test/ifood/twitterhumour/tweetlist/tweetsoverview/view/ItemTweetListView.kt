package com.test.ifood.twitterhumour.tweetlist.tweetsoverview.view

import com.test.ifood.twitterhumour.base.BaseView

interface ItemTweetListView: BaseView {

    fun onTweetClicked(text: String)
}