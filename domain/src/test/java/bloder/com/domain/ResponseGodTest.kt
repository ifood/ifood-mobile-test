package bloder.com.domain

import bloder.com.domain.api_response.ResponseGod
import com.nhaarman.mockitokotlin2.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ResponseGodTest {

    private val responseGod = mock<ResponseGod>()

    @Test fun testUnknownError() {
        mockResponseGodWith(401)
        doNothing().whenever(responseGod).unknown(401)
        responseGod.handle(401)
        verify(responseGod, times(1)).unknown(401)
    }

    @Test fun testOn200Response() {
        mockResponseGodWith(200)
        doNothing().whenever(responseGod).on200()
        responseGod.handle(200)
        verify(responseGod, times(1)).on200()
    }

    @Test fun testOn404Response() {
        mockResponseGodWith(404)
        doNothing().whenever(responseGod).on404()
        responseGod.handle(404)
        verify(responseGod, times(1)).on404()
    }

    private fun mockResponseGodWith(code: Int) {
        doAnswer {
            val map = mockResponseMap()
            if (map[code] != null) map[code]?.invoke()
            else responseGod.unknown(code)
        }.whenever(responseGod).handle(code)
    }

    private fun mockResponseMap() : Map<Int, () -> Any> = mapOf(
            200 to { responseGod.on200() },
            404 to { responseGod.on404() }
    )
}