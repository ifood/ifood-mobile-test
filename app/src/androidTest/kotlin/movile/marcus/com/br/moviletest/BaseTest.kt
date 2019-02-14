package movile.marcus.com.br.moviletest

import movile.marcus.com.br.moviletest.setup.TestServerUrl
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

open class BaseTest {

    @Rule
    @JvmField
    val server: MockWebServer = MockWebServer()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        TestServerUrl.url = server.url("/")
    }
}