package com.spider.twitteranalyzer.feature.list.viewslice.state

import android.support.design.widget.Snackbar
import android.view.View
import com.spider.twitteranalyzer.base.extension.show
import com.spider.twitteranalyzer.base.test.EspressoIdlingResource
import com.spider.twitteranalyzer.base.view.ErrorMessageFactory
import com.spider.twitteranalyzer.base.viewslice.BaseViewSlice
import com.spider.twitteranalyzer.base.viewslice.ViewSlice
import kotlinx.android.synthetic.main.activity_tweet_list.*
import javax.inject.Inject


interface StateViewSlice : ViewSlice {

    fun showLoading()
    fun showTweets()
    fun showEmpty()
    fun showError(throwable: Throwable)

    class Impl @Inject constructor(
        private val errorMessageFactory: ErrorMessageFactory
    ) : BaseViewSlice(),
        StateViewSlice {


        companion object {
            private const val STATE_SHOW_TWEET = 0
            private const val STATE_LOADING = 1
            private const val STATE_EMPTY = 2
            private const val STATE_ERROR = 3
        }

        override fun showEmpty() {
            showState(STATE_EMPTY)
            EspressoIdlingResource.decrement()
        }

        override fun showLoading() {
            showState(STATE_LOADING)
        }

        override fun showTweets() {
            showState(STATE_SHOW_TWEET)
            EspressoIdlingResource.decrement()
        }

        override fun showError(throwable: Throwable) {
            showState(STATE_ERROR, throwable)
            EspressoIdlingResource.decrement()
        }

        private fun showState(state: Int, throwable: Throwable? = null) {
            loading_bar.show(state == STATE_LOADING)
            tweets_empty.show(state == STATE_EMPTY)
            tweets_recycler_view.show(state == STATE_SHOW_TWEET)
            throwable?.run { show(root_layout) }
        }

        private fun Throwable.show(view: View) {
            Snackbar.make(
                view,
                errorMessageFactory.create(this).message,
                Snackbar.LENGTH_INDEFINITE
            ).apply {
                setAction(android.R.string.ok) { this.dismiss() }
            }.show()
        }


    }
}
