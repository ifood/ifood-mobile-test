package com.drury.twittermoodanalyzer.presenter

import com.drury.twittermoodanalyzer.BaseApp
import com.drury.twittermoodanalyzer.R
import com.drury.twittermoodanalyzer.api.DocumentSentiment
import com.drury.twittermoodanalyzer.controller.AnalyzerController
import com.drury.twittermoodanalyzer.model.SentimentAnalyzer
import com.drury.twittermoodanalyzer.model.SentimentEnum
import com.drury.twittermoodanalyzer.model.TweetModel
import com.drury.twittermoodanalyzer.utils.AppConstants
import com.drury.twittermoodanalyzer.view.MoodActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_mood.*
import kotlinx.android.synthetic.main.tweet_item.*
import javax.inject.Inject

class MoodPresenter @Inject constructor(var moodActivity: MoodActivity): IPresenter.MoodPresenter {

    lateinit var currentMood: SentimentEnum

    lateinit var tweet: TweetModel

    lateinit var analyzerController: AnalyzerController

    override fun onCreate() {
        tweet = moodActivity.intent.getParcelableExtra<TweetModel>("TWEET")
        analyzerController = AnalyzerController(moodActivity.applicationContext as BaseApp)
        moodActivity.setTweetInfo(tweet)
    }

    override fun analyzeSentenceMood() {
        moodActivity.showLoadingDialog()
        analyzerController.analyzeTweetMood(tweet.text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { documentSentiment ->
                    processMood(documentSentiment)
                    moodActivity.hideLoadingDialog()
                }, {
                    e -> e.printStackTrace()
                    moodActivity.hideLoadingDialog()
                })
    }

    private fun processMood(documentSentiment: DocumentSentiment) {
        currentMood = SentimentAnalyzer(documentSentiment.score).getMoodEnum()
        changeViewToCurrentMood()
    }

    private fun setBackgroundColor() {
        val colorId = when (currentMood) {
            SentimentEnum.HAPPY -> AppConstants.COLOR_HAPPY
            SentimentEnum.SAD -> AppConstants.COLOR_SAD
            else -> AppConstants.COLOR_NEUTRAL
        }
        moodActivity.changeBackgroundColor(colorId)
    }

    private fun getMoodEmoji(): String {
        val unicodeEmoji = when (currentMood) {
            SentimentEnum.HAPPY -> AppConstants.EMOJI_HAPPY
            SentimentEnum.SAD -> AppConstants.EMOJI_SAD
            else -> AppConstants.EMOJI_NEUTRAL
        }
        return getEmojiByUnicode(unicodeEmoji)
    }

    private fun getTextMoodId(): Int {
        return when (currentMood) {
            SentimentEnum.HAPPY -> R.string.mood_happy
            SentimentEnum.SAD -> R.string.mood_sad
            else -> R.string.mood_neutral
        }
    }

    private fun getEmojiByUnicode(unicode: Int): String {
        return String(Character.toChars(unicode))
    }

    private fun changeViewToCurrentMood() {
        setBackgroundColor()
        moodActivity.textViewEmoji.text = getMoodEmoji()
        moodActivity.textViewMoodText.text = moodActivity.getString(getTextMoodId())
    }

}