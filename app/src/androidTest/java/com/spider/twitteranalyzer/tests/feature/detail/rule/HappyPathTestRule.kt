package com.spider.twitteranalyzer.tests.feature.detail.rule

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import com.spider.twitteranalyzer.App
import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.base.test.ActivityInjectorForTest
import com.spider.twitteranalyzer.feature.detail.view.TweetDetailActivity
import com.spider.twitteranalyzer.feature.detail.viewmodel.DetailViewModel
import com.spider.twitteranalyzer.feature.detail.viewslice.detail.DetailViewSlice
import com.spider.twitteranalyzer.feature.detail.viewslice.state.StateViewSlice
import com.spider.twitteranalyzer.tests.feature.detail.Mocks


class HappyPathTestRule : ActivityTestRule<TweetDetailActivity>(TweetDetailActivity::class.java, true, true) {
    override fun getActivityIntent(): Intent {
        return Intent().apply {
            putExtra(Tweet.EXTRA_TWEET, Mocks.tweet)
        }
    }

    override fun beforeActivityLaunched() {
        super.beforeActivityLaunched()

        val app = InstrumentationRegistry.getTargetContext().applicationContext as App
        app.activityInjector = ActivityInjectorForTest.create<TweetDetailActivity> {
            viewModel = DetailViewModel.Impl(
                Mocks.state,
                Mocks.useCase
            )
            stateViewSlice = StateViewSlice.Impl(Mocks.errorMessageFactory)
            detailViewSlice = DetailViewSlice.Impl(
                actionLiveData = Mocks.mutableLiveData,
                showcaseEnabled = false
            )
        }
    }
}