package movile.marcus.com.br.moviletest

import movile.marcus.com.br.moviletest.setup.TestServerUrl
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule

open class BaseTest {

    @Rule
    @JvmField
    val server: MockWebServer = MockWebServer()

    @Before
    fun setup() {
        TestServerUrl.url = server.url("/")
    }
}