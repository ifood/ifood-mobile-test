package movile.marcus.com.br.moviletest.ui.home

import android.content.Intent
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import io.mockk.every
import movile.marcus.com.br.moviletest.R
import movile.marcus.com.br.moviletest.model.repository.TwitterRepository
import movile.marcus.com.br.moviletest.util.RecyclerViewItemCountAssertion
import movile.marcus.com.br.moviletest.util.TestHelper
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy

class HomeRobots(private var rule: ActivityTestRule<HomeActivity>, private val server: MockWebServer) {

    fun initActivity(intent: Intent) {
        rule.launchActivity(intent)
    }

    fun mockResponseSuccess() {
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(TestHelper.getStringFromFile(getInstrumentation().context, "get_tweets_success.json"))
        )
    }

    fun mockNotFound() {
        server.enqueue(
            MockResponse()
                .setResponseCode(404)
        )
    }

    fun mockNoInternet() {
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setSocketPolicy(SocketPolicy.DISCONNECT_AFTER_REQUEST)
        )
    }

    fun mockLocalSaveUser(twitterRepository: TwitterRepository) {
        every { twitterRepository.getLastSearch() } returns "movile"
    }

    fun validateIfViewAppearWithItems(total: Int) {
        onView(withId(R.id.activityHomeTweetList)).check(RecyclerViewItemCountAssertion(total))
    }

    fun validateIfNotFoundLayoutAppear() {
        onView(withId(R.id.activityHomeErrorView)).check(matches(isDisplayed()))
        onView(withText("Page not found.")).check(matches(isDisplayed()))
    }

    fun validateIfNotInternetLayoutAppear() {
        onView(withId(R.id.activityHomeErrorView)).check(matches(isDisplayed()))
        onView(withText("An internet error occurred.")).check(matches(isDisplayed()))
    }
}