package com.spider.twitteranalyzer.tests.feature.list.screen

import android.view.View
import com.agoda.kakao.KRecyclerItem
import com.agoda.kakao.KRecyclerView
import com.agoda.kakao.KTextView
import com.agoda.kakao.Screen
import com.spider.twitteranalyzer.R
import org.hamcrest.Matcher


open class ListScreen : Screen<ListScreen>() {
    val searchView: KMaterialSearchView = KMaterialSearchView { withId(R.id.search_view) }

    val recycler: KRecyclerView = KRecyclerView({
        withId(R.id.tweets_recycler_view)
    }, itemTypeBuilder = {
        itemType(ListScreen::Item)
    })

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val tweetDate: KTextView = KTextView(parent) { withId(R.id.tweet_date) }
        val tweetUser: KTextView = KTextView(parent) { withId(R.id.tweet_user) }
        val tweetText: KTextView = KTextView(parent) { withId(R.id.tweet_text) }
    }
}