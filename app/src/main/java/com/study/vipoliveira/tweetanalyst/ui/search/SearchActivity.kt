package com.study.vipoliveira.tweetanalyst.ui.search

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.study.vipoliveira.tweetanalyst.R
import com.study.vipoliveira.tweetanalyst.ui.tweet.TweetsActivity
import com.study.vipoliveira.tweetanalyst.utils.SEARCH
import kotlinx.android.synthetic.main.search_activity.*

class SearchActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)
        initViews()
    }

    private fun initViews() {
        twitter_user_query.isIconified = false
        twitter_user_query.clearFocus()

        twitter_user_query.setOnSearchClickListener { openTweetActivity(twitter_user_query.query) }

        btn_go.setOnClickListener { openTweetActivity(twitter_user_query.query) }
    }

    private fun openTweetActivity(query: CharSequence?) {
        if (query != null && !query.isEmpty()) {
            val listTweets = Intent(this, TweetsActivity::class.java)
            val bundle = Bundle()
            bundle.putCharSequence(SEARCH, query)
            listTweets.putExtras(bundle)
            startActivity(listTweets)
        }
    }
}