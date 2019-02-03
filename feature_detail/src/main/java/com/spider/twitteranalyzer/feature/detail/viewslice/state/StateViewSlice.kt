package com.spider.twitteranalyzer.feature.detail.viewslice.state

import android.support.design.widget.Snackbar
import android.view.View
import com.spider.twitteranalyzer.base.extension.show
import com.spider.twitteranalyzer.base.test.EspressoIdlingResource
import com.spider.twitteranalyzer.base.view.ErrorMessageFactory
import com.spider.twitteranalyzer.base.viewslice.BaseViewSlice
import com.spider.twitteranalyzer.base.viewslice.ViewSlice
import kotlinx.android.synthetic.main.activity_tweet_detail.*
import javax.inject.Inject

interface StateViewSlice : ViewSlice {

    fun showLoading()
    fun showContent()
    fun showError(throwable: Throwable)

    class Impl @Inject constructor(
        private val errorMessageFactory: ErrorMessageFactory
    ) : StateViewSlice, BaseViewSlice() {

        companion object {
            private const val STATE_LOADING = 0
            private const val STATE_SHOW_CONTENT = 1
            private const val STATE_ERROR = 2
        }

        override fun showLoading() {
            showState(STATE_LOADING)
            EspressoIdlingResource.increment()
        }

        override fun showContent() {
            showState(STATE_SHOW_CONTENT)
            EspressoIdlingResource.decrement()
        }

        override fun showError(throwable: Throwable) {
            showState(STATE_ERROR, throwable)
            EspressoIdlingResource.decrement()
        }

        private fun showState(state: Int, throwable: Throwable? = null) {
            loading_bar.show(state == STATE_LOADING)
            text_sentiment.show(state == STATE_SHOW_CONTENT)
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