package movile.marcus.com.br.moviletest.ui.home

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import io.mockk.mockk
import movile.marcus.com.br.moviletest.BaseTest
import movile.marcus.com.br.moviletest.model.repository.TwitterRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest : BaseTest() {

    @Rule
    @JvmField
    val rule = ActivityTestRule<HomeActivity>(HomeActivity::class.java, false, false)

    private lateinit var twitterRepository: TwitterRepository

    private fun robots(func: HomeRobots.() -> Unit) = HomeRobots(rule, server).apply(func)

    private val intent = Intent()

    @Before
    fun before() {
        super.setup()
        twitterRepository = mockk()
    }

    @Test
    fun testIfHomeAppear() {
        robots {
            mockLocalSaveUser(twitterRepository)
            mockResponseSuccess("get_tweets_success.json")
            initActivity(intent)
            validateIfViewAppearWithItems(2)
        }
    }
}