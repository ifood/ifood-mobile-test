package com.drury.twittermoodanalyzer

import com.drury.twittermoodanalyzer.extension.toSimpleString
import com.drury.twittermoodanalyzer.model.SentimentAnalyzer
import com.drury.twittermoodanalyzer.model.SentimentEnum
import com.drury.twittermoodanalyzer.presenter.MainPresenter
import com.drury.twittermoodanalyzer.presenter.MoodPresenter
import com.drury.twittermoodanalyzer.utils.AppConstants
import com.drury.twittermoodanalyzer.view.MoodActivity
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import java.util.*
import javax.inject.Inject

class UnitTest {

    @Test
    fun test_dateSimpleFormat_success() {
        // year minus 1900
        val date = Date((2019-1900), 0,9, 12, 40)
        assertEquals("09/01/2019", date.toSimpleString())
    }

    @Test
    fun test_sentimentAnalyzerHappy_sucess() {
        val score = 1.0
        val sentimentAnalyzer = SentimentAnalyzer(score)
        val sentiment = SentimentEnum.HAPPY
        assertEquals(sentiment, sentimentAnalyzer.getMoodEnum())
    }

    @Test
    fun test_sentimentAnalyzerSad_sucess() {
        val score = -0.5
        val sentimentAnalyzer = SentimentAnalyzer(score)
        val sentiment = SentimentEnum.SAD
        assertEquals(sentiment, sentimentAnalyzer.getMoodEnum())
    }

    @Test
    fun test_sentimentAnalyzerNeutral_sucess() {
        val score = -0.0
        val sentimentAnalyzer = SentimentAnalyzer(score)
        val sentiment = SentimentEnum.NEUTRAL
        assertEquals(sentiment, sentimentAnalyzer.getMoodEnum())
    }

    @Test
    fun test_sentimentAnalyzerNeutralScoreOutOfRange_sucess() {
        val score = -2.0
        val sentimentAnalyzer = SentimentAnalyzer(score)
        val sentiment = SentimentEnum.NEUTRAL
        assertEquals(sentiment, sentimentAnalyzer.getMoodEnum())
    }

    @Test
    fun test_getMoodEmojiHappy_success() {
        val moodPresenter = MoodPresenter()
        val emoji = String(Character.toChars(AppConstants.EMOJI_HAPPY))
        assertEquals(emoji, moodPresenter.getMoodEmoji(SentimentEnum.HAPPY))
    }
}
