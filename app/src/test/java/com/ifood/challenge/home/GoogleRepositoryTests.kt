package com.ifood.challenge.home

import com.ifood.challenge.base.BaseTests
import com.ifood.challenge.base.common.exception.EssentialParamMissingException
import com.ifood.challenge.base.common.exception.NetworkError
import com.ifood.challenge.home.data.GoogleRepository
import com.ifood.challenge.home.data.GoogleService
import com.ifood.challenge.home.data.SentimentMapper
import com.ifood.challenge.home.model.Sentiment
import org.junit.Before
import org.junit.Test
import retrofit2.create
import testCommon.di.retrofit
import testCommon.utils.assertCompleted
import testCommon.utils.assertWithError

class GoogleRepositoryTests : BaseTests() {

    private val service: GoogleService = retrofit.create()

    private val sentimentMapper = SentimentMapper()

    private lateinit var repository: GoogleRepository.Remote

    override val isMockServerEnabled: Boolean get() = true

    @Before
    override fun setUp() {
        super.setUp()
        repository = GoogleRepository.Remote(service, sentimentMapper, networkHandler)
    }

    @Test
    fun `should return sentiment happy with success`() {
        stubNetworkHandlerIsConnected(true)

        val testObserver = repository.analyzeSentiment(Sentiment.Happy.toString()).test()

        with(testObserver) {
            awaitTerminalEvent()
            assertCompleted(Sentiment.Happy)
            assertNoErrors()
        }
    }

    @Test
    fun `should return sentiment neutral with success`() {
        stubNetworkHandlerIsConnected(true)

        val testObserver = repository.analyzeSentiment(Sentiment.Neutral.toString()).test()

        with(testObserver) {
            awaitTerminalEvent()
            assertCompleted(Sentiment.Neutral)
            assertNoErrors()
        }
    }

    @Test
    fun `should return sentiment sad with success`() {
        stubNetworkHandlerIsConnected(true)

        val testObserver = repository.analyzeSentiment(Sentiment.Sad.toString()).test()

        with(testObserver) {
            awaitTerminalEvent()
            assertCompleted(Sentiment.Sad)
            assertNoErrors()
        }
    }

    @Test
    fun `should throws essential missing params error when or required data is not returned`() {
        stubNetworkHandlerIsConnected(true)

        val testObserver = repository.analyzeSentiment("broken").test()

        testObserver.assertWithError(EssentialParamMissingException::class.java)
    }

    @Test
    fun `should throws network error when is without internet connection`() {
        stubNetworkHandlerIsConnected(false)

        val testObserver = repository.analyzeSentiment(Sentiment.Sad.toString()).test()

        testObserver.assertWithError(NetworkError)
    }
}
