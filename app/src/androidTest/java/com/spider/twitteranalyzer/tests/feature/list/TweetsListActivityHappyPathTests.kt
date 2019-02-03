package com.spider.twitteranalyzer.tests.feature.list

import android.support.test.espresso.IdlingRegistry
import android.support.test.runner.AndroidJUnit4
import com.agoda.kakao.KRecyclerView
import com.spider.twitteranalyzer.base.extension.reformatDate
import com.spider.twitteranalyzer.base.test.EspressoIdlingResource
import com.spider.twitteranalyzer.tests.feature.list.rule.HappyPathTestRule
import com.spider.twitteranalyzer.tests.feature.list.screen.ListScreen
import com.spider.twitteranalyzer.tests.shared.DeviceTestUtils
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@Suppress("IllegalIdentifier")
@RunWith(AndroidJUnit4::class)
class TweetsListActivityHappyPathTests {
    @get:Rule
    val activityTestRule = HappyPathTestRule()

    val screen = ListScreen()

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
            whenFetchTweets("anderson")
            thenRecyclerViewShouldRenderCorrectStates()
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // WHEN                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun ListScreen.whenFetchTweets(username: String) {
        searchView { submitSearchQuery(username) }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THEN                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun ListScreen.thenRecyclerViewShouldRenderCorrectStates() {
        recycler {
            isVisible()
            hasSize(3)
            thenRecyclerItemShouldBeEqualPosition(0)
            thenRecyclerItemShouldBeEqualPosition(1)
            thenRecyclerItemShouldBeEqualPosition(2)
        }
    }

    private fun KRecyclerView.thenRecyclerItemShouldBeEqualPosition(position: Int) {
        childAt<ListScreen.Item>(position) {
            isVisible()
            tweetDate { hasText(Mocks.list[position].createdAt.reformatDate()) }
            tweetText { hasText(Mocks.list[position].text) }
            tweetUser { hasText("@${Mocks.list[position].user.name}") }
        }
    }
}