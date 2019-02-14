package movile.marcus.com.br.moviletest.ui.home

import android.content.Intent
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import io.mockk.every
import movile.marcus.com.br.moviletest.R
import movile.marcus.com.br.moviletest.model.repository.TwitterRepository
import movile.marcus.com.br.moviletest.util.RecyclerViewItemCountAssertion
import movile.marcus.com.br.moviletest.util.TestHelper
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

class HomeRobots(private var rule: ActivityTestRule<HomeActivity>, private val server: MockWebServer) {

    fun initActivity(intent: Intent) {
        rule.launchActivity(intent)
    }

    fun mockResponseSuccess(fileName: String) {
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(TestHelper.getStringFromFile(getInstrumentation().context, fileName))
        )
    }

    fun mockLocalSaveUser(twitterRepository: TwitterRepository) {
        every { twitterRepository.getLastSearch() } returns "movile"
    }

    fun validateIfViewAppearWithItems(total: Int) {
        onView(withId(R.id.activityHomeTweetList)).check(RecyclerViewItemCountAssertion(total))
    }
}