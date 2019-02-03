package com.spider.twitteranalyzer.tests.feature.detail

import android.support.test.espresso.IdlingRegistry
import com.spider.twitteranalyzer.base.test.EspressoIdlingResource
import com.spider.twitteranalyzer.feature.detail.domain.model.Sentiment
import com.spider.twitteranalyzer.tests.feature.detail.rule.HappyPathTestRule
import com.spider.twitteranalyzer.tests.feature.detail.screen.DetailScreen
import com.spider.twitteranalyzer.tests.shared.DeviceTestUtils
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class TweetsDetailActivityHappyPathTests {
    @get:Rule
    val activityTestRule = HappyPathTestRule()

    val screen = DetailScreen()

    @Before
    fun setup() {
        DeviceTestUtils.wakeUp()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // TESTS                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    fun execute() {
        screen {
            whenActivityCreatedShouldFillData()
            whenAnalyzeTweet()
            thenAnalyzeResponseShouldBeCorrect()
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // WHEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun DetailScreen.whenActivityCreatedShouldFillData() {
        detailTweetDate {
            isVisible()
            hasText(Mocks.tweet.createdAt)
        }
        detailTweetText {
            isVisible()
            hasText(Mocks.tweet.text)
        }
        detailTweetUser {
            isVisible()
            hasText(Mocks.tweet.user)
        }
    }

    private fun DetailScreen.whenAnalyzeTweet() {
        btnAnalyze {
            isClickable()
            isVisible()
            click()
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun DetailScreen.thenAnalyzeResponseShouldBeCorrect() {
        emoji {
            isVisible()
            hasText(Sentiment.HAPPY.emojiCode)
        }
    }
}