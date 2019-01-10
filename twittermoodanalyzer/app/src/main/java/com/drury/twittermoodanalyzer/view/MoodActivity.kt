package com.drury.twittermoodanalyzer.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.drury.twittermoodanalyzer.R
import com.drury.twittermoodanalyzer.extension.fadeIn
import com.drury.twittermoodanalyzer.extension.toSimpleString
import com.drury.twittermoodanalyzer.model.TweetModel
import com.drury.twittermoodanalyzer.presenter.IPresenter
import com.drury.twittermoodanalyzer.presenter.MoodPresenter
import com.drury.twittermoodanalyzer.utils.AppConstants
import com.drury.twittermoodanalyzer.view.component.ViewDialog
import kotlinx.android.synthetic.main.activity_mood.*
import java.util.*


class MoodActivity : AppCompatActivity(), IView.MoodActivity {

    lateinit var moodPresenter: IPresenter.MoodPresenter
    lateinit var loadingDialog: ViewDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood)

        initViewComponents()
        moodPresenter.onCreate()
        moodPresenter.analyzeSentenceMood()
    }

    private fun initViewComponents() {
        moodPresenter = MoodPresenter()
        moodPresenter.attachView(this)
        loadingDialog = ViewDialog(this)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
    }

    override fun changeBackgroundColor(colorId: Int) {
        moodConstraintLayout.setBackgroundResource(colorId)
    }

    override fun showLoadingDialog() {
        loadingDialog.showDialog()
    }

    override fun hideLoadingDialog() {
        loadingDialog.hideDialog()
    }

    override fun setTweetInfo(tweetModel: TweetModel) {
        textViewDate.fadeIn(AppConstants.ANIMATION_TIME)
        textViewTweet.fadeIn(AppConstants.ANIMATION_TIME)
        textViewDate.text = Date(tweetModel.created).toSimpleString()
        textViewTweet.text = tweetModel.text

    }

    override fun onDestroy() {
        moodPresenter.detachView()
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
