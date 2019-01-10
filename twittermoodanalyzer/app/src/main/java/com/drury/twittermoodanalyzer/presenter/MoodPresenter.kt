package com.drury.twittermoodanalyzer.presenter

import com.drury.twittermoodanalyzer.R
import com.drury.twittermoodanalyzer.api.DocumentSentiment
import com.drury.twittermoodanalyzer.controller.AnalyzerController
import com.drury.twittermoodanalyzer.extension.fadeIn
import com.drury.twittermoodanalyzer.model.SentimentAnalyzer
import com.drury.twittermoodanalyzer.model.SentimentEnum
import com.drury.twittermoodanalyzer.model.TweetModel
import com.drury.twittermoodanalyzer.utils.AppConstants
import com.drury.twittermoodanalyzer.view.MoodActivity
import com.drury.twittermoodanalyzer.view.component.CustomAlertDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_mood.*
import retrofit2.HttpException

class MoodPresenter: IPresenter.MoodPresenter {

    private var moodActivity: MoodActivity? = null

    lateinit var tweet: TweetModel

    lateinit var analyzerController: AnalyzerController

    override fun onCreate() {
        tweet = moodActivity?.intent?.getParcelableExtra<TweetModel>(AppConstants.TWEET)!!
        analyzerController = AnalyzerController(moodActivity!!)
        moodActivity?.setTweetInfo(tweet)
    }

    override fun attachView(moodActivity: MoodActivity) {
        this.moodActivity = moodActivity
    }

    override fun detachView() {
        this.moodActivity = null
    }

    override fun analyzeSentenceMood() {
        moodActivity?.showLoadingDialog()
        analyzerController.analyzeTweetMood(tweet.text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { documentSentiment ->
                    moodActivity?.hideLoadingDialog()
                    processMood(documentSentiment)
                }, {
                    e -> errorHandler(e)
                })
    }

    private fun processMood(documentSentiment: DocumentSentiment) {
        val currentMood = SentimentAnalyzer(documentSentiment.score).getMoodEnum()
        changeViewToCurrentMood(currentMood)
    }

    private fun setBackgroundColor(mood: SentimentEnum) {
        val colorId = when (mood) {
            SentimentEnum.HAPPY -> AppConstants.COLOR_HAPPY
            SentimentEnum.SAD -> AppConstants.COLOR_SAD
            else -> AppConstants.COLOR_NEUTRAL
        }
        moodActivity?.changeBackgroundColor(colorId)
    }

    fun getMoodEmoji(mood: SentimentEnum): String {
        val unicodeEmoji = when (mood) {
            SentimentEnum.HAPPY -> AppConstants.EMOJI_HAPPY
            SentimentEnum.SAD -> AppConstants.EMOJI_SAD
            else -> AppConstants.EMOJI_NEUTRAL
        }
        return getEmojiByUnicode(unicodeEmoji)
    }

    fun getTextMoodId(mood: SentimentEnum): Int {
        return when (mood) {
            SentimentEnum.HAPPY -> R.string.mood_happy
            SentimentEnum.SAD -> R.string.mood_sad
            else -> R.string.mood_neutral
        }
    }

    fun getEmojiByUnicode(unicode: Int): String {
        return String(Character.toChars(unicode))
    }

    private fun changeViewToCurrentMood(mood: SentimentEnum) {
        setBackgroundColor(mood)
        moodActivity?.textViewEmoji?.text = getMoodEmoji(mood)
        moodActivity?.textViewMoodText?.text = moodActivity?.getString(getTextMoodId(mood))
        moodActivity?.textViewEmoji?.fadeIn(AppConstants.ANIMATION_TIME)
        moodActivity?.textViewMoodText?.fadeIn(AppConstants.ANIMATION_TIME)
    }

    private fun errorHandler(throwable: Throwable) {
        moodActivity?.hideLoadingDialog()
        var errorMessage = moodActivity?.getString(R.string.error_unknown)!!
        val alertTitle = moodActivity?.getString(R.string.error_window_title)
        if (throwable is HttpException) {
            errorMessage = moodActivity?.getString(R.string.error_could_not_process_sentence)!!
        }
        CustomAlertDialog().withCustomStyle(moodActivity!!, alertTitle!!, errorMessage)
    }

}