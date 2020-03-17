package com.ifood.challenge.home.presentation

import com.ifood.challenge.R
import com.ifood.challenge.base.extensions.view.getColorRes
import com.ifood.challenge.base.extensions.view.getString
import com.ifood.challenge.base.extensions.view.isVisible
import com.ifood.challenge.base.presentation.BaseRecyclerViewAdapter
import com.ifood.challenge.home.model.Sentiment
import com.ifood.challenge.home.model.Tweet
import kotlinx.android.synthetic.main.tweet_item.view.*

class TweetAdapter(onTweetClick: (Tweet) -> Unit) : BaseRecyclerViewAdapter<Tweet>(
    layoutResId = R.layout.tweet_item,
    bindView = { view, item ->
        val colorAndEmoji = when (item.sentiment) {
            Sentiment.Happy -> Pair(R.color.sentiment_happy, R.string.sentiment_emoji_happy)
            Sentiment.Neutral -> Pair(R.color.sentiment_neutral, R.string.sentiment_emoji_neutral)
            Sentiment.Sad -> Pair(R.color.sentiment_sad, R.string.sentiment_emoji_sad)
            else -> Pair(R.color.sentiment_none, R.string.sentiment_emoji_none)
        }

        view.tweetText.text = item.text
        view.tweetDate.text = item.createdAt
        view.tweetProgress.isVisible = item.isLoading
        view.tweetEmoji.text = view.getString(colorAndEmoji.second)
        view.tweetContainer.setBackgroundColor(view.getColorRes(colorAndEmoji.first))
        view.setOnClickListener { onTweetClick(item) }
    })
