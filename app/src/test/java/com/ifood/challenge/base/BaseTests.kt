package com.ifood.challenge.base

import com.ifood.challenge.base.common.network.NetworkHandler
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.ExpectedException
import testCommon.utils.MockServerDispatcher

abstract class BaseTests {

    @Rule
    @JvmField
    val schedulersRule = RxSchedulerOverrideRule()

    @Rule
    @JvmField
    val expectedException: ExpectedException = ExpectedException.none()

    @MockK
    lateinit var networkHandler: NetworkHandler

    private lateinit var mockServer: MockWebServer

    open val isMockServerEnabled: Boolean = false

    @Before
    open fun setUp() {
        MockKAnnotations.init(this)
        configureMockServer()
    }

    @After
    open fun tearDown() {
        stopMockServer()
    }

    @Throws
    open fun configureMockServer() {
        if (isMockServerEnabled) {
            mockServer = MockWebServer().apply {
                start(8080)
                dispatcher = MockServerDispatcher()
            }
        }
    }

    @Throws
    open fun stopMockServer() {
        if (isMockServerEnabled) mockServer.shutdown()
    }

    fun stubNetworkHandlerIsConnected(isConnected: Boolean) {
        every { networkHandler.isConnected } returns isConnected
    }

}
