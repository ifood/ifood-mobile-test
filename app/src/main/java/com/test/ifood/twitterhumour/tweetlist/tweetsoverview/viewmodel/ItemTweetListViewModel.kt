package com.test.ifood.twitterhumour.tweetlist.tweetsoverview.viewmodel

import android.app.Application
import android.databinding.Bindable
import android.view.View
import com.test.ifood.twitterhumour.base.BaseViewModel
import com.test.ifood.twitterhumour.model.Tweet
import com.test.ifood.twitterhumour.tweetlist.tweetsoverview.view.ItemTweetListView

class ItemTweetListViewModel(application: Application,
                             override var view: ItemTweetListView?,
                             private val tweet: Tweet): BaseViewModel<ItemTweetListView>(application) {

    var tweetContent: String = " "
        @Bindable get() {
            return tweet.text
        }

    fun onTweetClicked(view: View) {
        this.view?.onTweetClicked(tweetContent)
    }

}