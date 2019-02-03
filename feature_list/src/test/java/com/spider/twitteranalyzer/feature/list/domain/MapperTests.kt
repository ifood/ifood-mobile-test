package com.spider.twitteranalyzer.feature.list.domain

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.base.extension.reformatDate
import com.spider.twitteranalyzer.base.network.api.twitter.dto.TweetResponse
import com.spider.twitteranalyzer.base.test.MockResponsesReader
import org.junit.Before
import org.junit.Test

@Suppress("IllegalIdentifier")
class MapperTests {

    private lateinit var subject: Mapper
    private lateinit var dto: List<TweetResponse>
    private var actual: List<Tweet>? = null
    private val list = listOf(
        Tweet(
            text = "@AlecLongstreth this is so fun to follow along during your day!",
            user = "@x",
            createdAt = "Fri Feb 01 22:02:18 +0000 2019".reformatDate()
        ),
        Tweet(
            text = "@zombieyeti Dude this is looking amazing!",
            user = "@x",
            createdAt = "Fri Feb 01 08:18:58 +0000 2019".reformatDate()
        ),
        Tweet(
            text = "U.S. Senate: National Pinball Day - Sign the Petition!",
            user = "@x",
            createdAt = "Wed Jan 30 21:34:21 +0000 2019".reformatDate()
        )
    )

    @Before
    fun setUp() {
        subject = Mapper()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // TESTS                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    fun `map - happy path`() {
        givenDto("tweets/tweets_-_happy_path.json")
        whenMapped()
        thenDtoShouldBeMappedCorrectly(list)
    }

    @Test
    fun `map - empty`() {
        givenDto("tweets/tweets_-_empty.json")
        whenMapped()
        thenDtoShouldBeMappedCorrectly(emptyList())
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // GIVEN                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun givenDto(filePath: String) {
        val rawValues = MockResponsesReader.readFile(filePath)
        val listType = object : TypeToken<List<TweetResponse>>() {
        }.type
        dto = Gson().fromJson<List<TweetResponse>>(rawValues, listType)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // WHEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun whenMapped() {
        actual = subject.map(dto)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun thenDtoShouldBeMappedCorrectly(expected: List<Tweet>) {
        assertThat(actual).isEqualTo(expected)
    }
}
