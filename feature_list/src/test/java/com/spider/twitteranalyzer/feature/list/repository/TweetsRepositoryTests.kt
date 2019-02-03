package com.spider.twitteranalyzer.feature.list.repository

import com.spider.twitteranalyzer.base.domain.NotFoundAccountError
import com.spider.twitteranalyzer.base.domain.PrivateAccountError
import com.spider.twitteranalyzer.base.domain.TimeoutError
import com.spider.twitteranalyzer.base.domain.UnknownError
import com.spider.twitteranalyzer.base.network.api.twitter.dto.TweetResponse
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
class TweetsRepositoryTests {

    private val userName = "andersoncsilva"
    private val throwablePrivateAccountError = PrivateAccountError()
    private val throwableNotFoundAccountError = NotFoundAccountError()
    private val throwableUnknownError = UnknownError(RuntimeException())
    private val throwableTimeoutError = TimeoutError()

    private lateinit var server: MockWebServer
    private lateinit var subject: TweetsRepository
    private lateinit var test: TestObserver<List<TweetResponse>>

    private val list = listOf(
        TweetResponse(
            text = "@AlecLongstreth this is so fun to follow along during your day!",
            user = TweetResponse.User("x"),
            createdAt = "Fri Feb 01 22:02:18 +0000 2019"
        ),
        TweetResponse(
            text = "@zombieyeti Dude this is looking amazing!",
            user = TweetResponse.User("x"),
            createdAt = "Fri Feb 01 08:18:58 +0000 2019"
        ),
        TweetResponse(
            text = "U.S. Senate: National Pinball Day - Sign the Petition!",
            user = TweetResponse.User("x"),
            createdAt = "Wed Jan 30 21:34:21 +0000 2019"
        )
    )

    @Before
    fun setup() {
        server = MockWebServer()
        val api = Fixtures.twitterApi(server.url("/").toString())
        subject = TweetsRepository.Impl(api)
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
        givenServer(server, "tweets/tweets_-_happy_path.json")
        whenRepositoryFetchTweets()
        thenRepositoryShouldReturnCorrectValue(list)
    }

    @Test
    @Throws(FileNotFoundException::class)
    fun `execute - error - HTTP 401`() {
        givenServerError(server, 401)
        whenRepositoryFetchTweets()
        thenRepositoryShouldReturnError(throwablePrivateAccountError)
    }

    @Test
    @Throws(FileNotFoundException::class)
    fun `execute - error - HTTP 404`() {
        givenServerError(server, 404)
        whenRepositoryFetchTweets()
        thenRepositoryShouldReturnError(throwableNotFoundAccountError)
    }

    @Test
    @Throws(FileNotFoundException::class)
    fun `execute - error - HTTP 500`() {
        givenServerError(server, 500)
        whenRepositoryFetchTweets()
        thenRepositoryShouldReturnError(throwableUnknownError)
    }

    @Test
    @Throws(FileNotFoundException::class)
    fun `execute - error - timeout`() {
        givenServerTimeoutError(server)
        whenRepositoryFetchTweets()
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

    private fun whenRepositoryFetchTweets() {
        test = subject.fetchTweets(userName).test()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun thenRepositoryShouldReturnCorrectValue(expected: List<TweetResponse>) {
        test.assertOf { it.assertValue { t -> t == expected } }
    }

    private fun thenRepositoryShouldReturnError(expected: Throwable) {
        test.assertOf { it.assertError { t -> t.javaClass.typeName == expected.javaClass.typeName } }
    }
}