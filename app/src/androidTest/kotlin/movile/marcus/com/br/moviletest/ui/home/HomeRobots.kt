package movile.marcus.com.br.moviletest.ui.home

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import okhttp3.mockwebserver.MockWebServer

class HomeRobots(var rule: ActivityTestRule<HomeActivity>, val server: MockWebServer) {

    fun initActivity(intent: Intent) {
        rule.launchActivity(intent)
    }

}