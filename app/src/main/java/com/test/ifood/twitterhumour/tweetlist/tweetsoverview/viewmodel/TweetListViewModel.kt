package com.test.ifood.twitterhumour.tweetlist.tweetsoverview.viewmodel

import android.app.Application
import android.databinding.ObservableInt
import android.view.View
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults
import com.test.ifood.twitterhumour.base.BaseView
import com.test.ifood.twitterhumour.base.BaseViewModel
import com.test.ifood.twitterhumour.languageprocessing.LanguageProcessor
import io.reactivex.Observable

class TweetListViewModel(application: Application): BaseViewModel<BaseView?>(application) {

    val loadingVisible = ObservableInt(View.GONE)

    fun processTweet(text: String): Observable<AnalysisResults> {
        val languageProcessor = LanguageProcessor(text)
        return Observable.fromFuture(languageProcessor.process())
    }

    fun updateLoadingState(showLoading: Boolean) {
        loadingVisible.set(if (showLoading) View.VISIBLE else View.GONE)
    }

}