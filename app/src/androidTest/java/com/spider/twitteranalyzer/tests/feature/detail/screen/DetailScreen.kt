package com.spider.twitteranalyzer.tests.feature.detail.screen

import com.agoda.kakao.KTextView
import com.agoda.kakao.Screen
import com.spider.twitteranalyzer.R


open class DetailScreen : Screen<DetailScreen>() {
    val detailTweetDate: KTextView = KTextView { withId(R.id.detail_tweet_date) }
    val detailTweetUser: KTextView = KTextView { withId(R.id.detail_tweet_user) }
    val detailTweetText: KTextView = KTextView { withId(R.id.detail_tweet_text) }
    val btnAnalyze: KTextView = KTextView { withId(R.id.btn_analyze_tweet) }
    val emoji: KTextView = KTextView { withId(R.id.text_sentiment) }
}