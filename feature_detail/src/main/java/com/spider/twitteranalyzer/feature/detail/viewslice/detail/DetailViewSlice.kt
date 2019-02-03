package com.spider.twitteranalyzer.feature.detail.viewslice.detail

import android.app.Activity
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import android.graphics.Color
import com.github.florent37.tutoshowcase.TutoShowcase
import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.base.test.EspressoIdlingResource
import com.spider.twitteranalyzer.base.viewslice.BaseViewSlice
import com.spider.twitteranalyzer.base.viewslice.ViewSlice
import com.spider.twitteranalyzer.feature.detail.R
import com.spider.twitteranalyzer.feature.detail.domain.model.Sentiment
import kotlinx.android.synthetic.main.activity_tweet_detail.*
import javax.inject.Inject

interface DetailViewSlice : ViewSlice {

    fun getAction(): LiveData<Action>
    fun fillTweetDetail(tweet: Tweet?)
    fun fillSentiment(sentiment: Sentiment)

    sealed class Action {
        data class AnalyzeTweet(val tweet: Tweet) : Action()
    }

    class Impl @Inject constructor(
        private val actionLiveData: MutableLiveData<Action>,
        private val showcaseEnabled: Boolean = true
    ) : DetailViewSlice, BaseViewSlice() {

        private var showcase: TutoShowcase? = null

        override fun getAction(): LiveData<Action> = actionLiveData

        override fun fillSentiment(sentiment: Sentiment) {
            text_sentiment.text = sentiment.emojiCode
            root_layout.setBackgroundColor(Color.parseColor(sentiment.color))
        }

        override fun fillTweetDetail(tweet: Tweet?) {
            EspressoIdlingResource.increment()
            tweet?.let {
                detail_tweet_date.text = it.createdAt
                detail_tweet_text.text = it.text
                detail_tweet_user.text = it.user
                setViewClickListener(actionLiveData, it)
            }
            EspressoIdlingResource.decrement()
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate() {
            if (showcaseEnabled)
                setUpShowCaseToAnalyzeButton()
        }

        private fun setUpShowCaseToAnalyzeButton() {
            showcase = TutoShowcase.from(context as Activity)
                .setContentView(R.layout.tutorial_detail)
                .on(R.id.btn_analyze_tweet)
                .addRoundRect()
                .onClickContentView(R.id.btn_got_it) { showcase?.dismiss() }
                .showOnce("tutorial.analyze")
        }

        private fun setViewClickListener(actionLiveData: MutableLiveData<Action>, data: Tweet) {
            btn_analyze_tweet.setOnClickListener {
                actionLiveData.value = DetailViewSlice.Action.AnalyzeTweet(data)
            }
        }
    }


}