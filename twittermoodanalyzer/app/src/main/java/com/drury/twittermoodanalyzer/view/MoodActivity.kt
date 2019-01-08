package com.drury.twittermoodanalyzer.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.drury.twittermoodanalyzer.BaseApp
import com.drury.twittermoodanalyzer.R
import com.drury.twittermoodanalyzer.extension.toSimpleString
import com.drury.twittermoodanalyzer.model.TweetModel
import com.drury.twittermoodanalyzer.presenter.IPresenter
import com.drury.twittermoodanalyzer.presenter.MoodPresenter
import com.drury.twittermoodanalyzer.view.component.ViewDialog
import kotlinx.android.synthetic.main.activity_mood.*
import java.util.*

class MoodActivity : AppCompatActivity(), IView.MoodActivity {

    lateinit var moodPresenter: IPresenter.MoodPresenter
    lateinit var loadingDialog: ViewDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood)
        (applicationContext as BaseApp).component.inject(this)

        initViewComponents()
        moodPresenter.onCreate()
        moodPresenter.analyzeSentenceMood()
    }

    private fun initViewComponents() {
        moodPresenter = MoodPresenter(this)
        loadingDialog = ViewDialog(this)
    }

    override fun changeBackgroundColor(colorId: Int) {
        moodConstraintLayout.setBackgroundResource(colorId)
    }

    override fun showLoadingDialog() {

    }

    override fun hideLoadingDialog() {

    }

    override fun setTweetInfo(tweetModel: TweetModel) {
        textViewDate.text = Date(tweetModel.created).toSimpleString()
        textViewTweet.text = tweetModel.text
    }
}
