package com.drury.twittermoodanalyzer.presenter

import com.drury.twittermoodanalyzer.R
import com.drury.twittermoodanalyzer.controller.AnalyzerController
import com.drury.twittermoodanalyzer.controller.IController
import com.drury.twittermoodanalyzer.view.MainActivity
import com.drury.twittermoodanalyzer.view.component.CustomAlertDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class MainPresenter: IPresenter.MainPresenter {

    lateinit var analyzerController: IController.AnalyzerController

    private var mainActivity: MainActivity? = null

    override fun onCreate() {
        analyzerController = AnalyzerController(mainActivity!!)
    }

    override fun attachView(mainActivity: MainActivity) {
        this.mainActivity = mainActivity
    }

    override fun detachView() {
        this.mainActivity = null
    }

    override fun getTweets(username: String) {
        // Call loading screen
        mainActivity?.tweets?.clear()
        mainActivity?.showLoadingDialog()
        analyzerController.getTweetsByUsername(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { tweetList ->
                    mainActivity?.hideLoadingDialog()
                    mainActivity?.populateTweets(tweetList.toMutableList())
                }, { e ->
                    errorHandler(e)
                })
    }

    private fun errorHandler(throwable: Throwable) {
        mainActivity?.hideLoadingDialog()
        var errorMessage = mainActivity?.getString(R.string.error_unknown)!!
        val alertTitle = mainActivity?.getString(R.string.error_window_title)!!
        if (throwable is HttpException) {
            if (throwable.code() == 404) {
                errorMessage = mainActivity?.getString(R.string.error_user_not_found)!!
            }
        }
        CustomAlertDialog().withCustomStyle(mainActivity!!, alertTitle, errorMessage)
    }
}