package com.spider.twitteranalyzer.feature.detail.repository

import com.spider.twitteranalyzer.base.domain.TimeoutError
import com.spider.twitteranalyzer.base.domain.UnknownError
import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.base.network.api.google.dto.SentimentResponse
import com.spider.twitteranalyzer.base.test.Fixtures
import com.spider.twitteranalyzer.base.test.MockResponsesReader
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException
import java.io.IOException

@Suppress("IllegalIdentifier")
class TweetAnalyzeRepositoryTests {

    private lateinit var server: MockWebServer
    private lateinit var subject: TweetAnalyzeRepository
    private lateinit var test: TestObserver<SentimentResponse>
    private val throwableUnknownError = UnknownError(RuntimeException())
    private val throwableTimeoutError = TimeoutError()

    private val tweet = Tweet(
        text = "@AlecLongstreth this is so fun to follow along during your day! \ud83d\ude4c\ud83c\udffc",
        user = "@x",
        createdAt = "02/01/2019 20:02:18"
    )
    private val sentimentResponse = SentimentResponse(
        documentSentiment = SentimentResponse.DocumentSentiment(
            magnitude = 1.0,
            score = 1.0
        )
    )

    @Before
    fun setup() {
        server = MockWebServer()
        val api = Fixtures.googleApi(server.url("/").toString())
        subject = TweetAnalyzeRepository.Impl(api)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        server.shutdown()
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // TESTS                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @Throws(FileNotFoundException::class)
    fun `execute - success - HTTP 200`() {
        givenServer(server, "sentiment/sentiment-_happy.json")
        whenRepositoryAnalyzeTweet()
        thenRepositoryShouldReturnCorrectValue(sentimentResponse)
    }

    @Test
    @Throws(FileNotFoundException::class)
    fun `execute - error - HTTP 500`() {
        givenServerError(server, 500)
        whenRepositoryAnalyzeTweet()
        thenRepositoryShouldReturnError(throwableUnknownError)
    }

    @Test
    @Throws(FileNotFoundException::class)
    fun `execute - error - timeout`() {
        givenServerTimeoutError(server)
        whenRepositoryAnalyzeTweet()
        thenRepositoryShouldReturnError(throwableTimeoutError)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // GIVEN                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun givenServer(mockWebServer: MockWebServer, filePath: String) {
        val fixture = MockResponsesReader.readFile(filePath)
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(fixture))
    }

    private fun givenServerError(mockWebServer: MockWebServer, code: Int) {
        mockWebServer.enqueue(MockResponse().setResponseCode(code))
    }

    private fun givenServerTimeoutError(mockWebServer: MockWebServer) {
        mockWebServer.enqueue(MockResponse().setSocketPolicy(SocketPolicy.NO_RESPONSE))
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // WHEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun whenRepositoryAnalyzeTweet() {
        test = subject.analyzeTweet(tweet).test()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun thenRepositoryShouldReturnCorrectValue(expected: SentimentResponse) {
        test.assertOf { it.assertValue { t -> t == expected } }
    }

    private fun thenRepositoryShouldReturnError(expected: Throwable) {
        test.assertOf { it.assertError { t -> t.javaClass.typeName == expected.javaClass.typeName } }
    }

}