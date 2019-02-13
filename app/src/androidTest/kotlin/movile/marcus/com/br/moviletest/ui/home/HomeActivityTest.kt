package movile.marcus.com.br.moviletest.ui.home

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import movile.marcus.com.br.moviletest.BaseTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest : BaseTest() {

    @Rule
    @JvmField
    val rule = ActivityTestRule<HomeActivity>(HomeActivity::class.java, false, false)

    private fun robots(func: HomeRobots.() -> Unit) = HomeRobots(rule, server).apply(func)

    private val intent = Intent()

    @Test
    fun testIfHomeAppear() {
        robots {
            initActivity(intent)
            validateIfNoOverlapView()
        }
    }
}