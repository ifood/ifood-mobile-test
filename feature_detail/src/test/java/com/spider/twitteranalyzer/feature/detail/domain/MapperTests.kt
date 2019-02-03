package com.spider.twitteranalyzer.feature.detail.domain

import com.google.common.truth.Truth
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.spider.twitteranalyzer.base.network.api.google.dto.SentimentResponse
import com.spider.twitteranalyzer.base.test.MockResponsesReader
import com.spider.twitteranalyzer.feature.detail.domain.model.Sentiment
import org.junit.Before
import org.junit.Test

@Suppress("IllegalIdentifier")
class MapperTests {

    private lateinit var subject: Mapper
    private lateinit var dto: SentimentResponse
    private var actual: Sentiment? = null


    @Before
    fun setUp() {
        subject = Mapper()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // TESTS                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    fun `map - happy result`() {
        givenDto("sentiment/sentiment-_happy.json")
        whenMapped()
        thenDtoShouldBeMappedCorrectly(Sentiment.HAPPY)
    }

    @Test
    fun `map - sad result`() {
        givenDto("sentiment/sentiment-_sad.json")
        whenMapped()
        thenDtoShouldBeMappedCorrectly(Sentiment.SAD)
    }

    @Test
    fun `map - neutral result`() {
        givenDto("sentiment/sentiment-_neutral.json")
        whenMapped()
        thenDtoShouldBeMappedCorrectly(Sentiment.NEUTRAL)
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // GIVEN                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun givenDto(filePath: String) {
        val rawValues = MockResponsesReader.readFile(filePath)
        val listType = object : TypeToken<SentimentResponse>() {}.type
        dto = Gson().fromJson<SentimentResponse>(rawValues, listType)
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

    private fun thenDtoShouldBeMappedCorrectly(expected: Sentiment) {
        Truth.assertThat(actual).isEqualTo(expected)
    }
}