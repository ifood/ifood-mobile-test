package com.drury.twittermoodanalyzer.view

import com.drury.twittermoodanalyzer.model.TweetModel

interface IView {

    interface MainActivity {
        fun populateTweets(tweetList: MutableList<TweetModel>)
        fun showLoadingDialog()
        fun hideLoadingDialog()
    }

    interface MoodActivity {
        fun changeBackgroundColor(colorId: Int)
        fun setTweetInfo(tweetModel: TweetModel)
        fun showLoadingDialog()
        fun hideLoadingDialog()
    }
}