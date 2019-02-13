package movile.marcus.com.br.moviletest.ui.home

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.LayoutAssertions.noOverlaps
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import movile.marcus.com.br.moviletest.R
import okhttp3.mockwebserver.MockWebServer

class HomeRobots(var rule: ActivityTestRule<HomeActivity>, val server: MockWebServer) {

    fun initActivity(intent: Intent) {
        rule.launchActivity(intent)
    }

    fun validateIfNoOverlapView() {
        onView(withId(R.id.activityHomeContainer)).check(noOverlaps())
    }

}