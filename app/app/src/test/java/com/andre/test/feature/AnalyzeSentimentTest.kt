package com.andre.test.feature

import com.andre.test.BaseUnitTest
import com.andre.test.core.network.NetworkResponse
import com.andre.test.features.AnalyzeSentiment
import com.andre.test.mock.MockedObjects
import com.google.api.services.language.v1.CloudNaturalLanguage
import com.google.api.services.language.v1.model.*
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class AnalyzeSentimentTest: BaseUnitTest() {

    private lateinit var analyzeSentiment: AnalyzeSentiment

    @Mock private lateinit var naturalLanguage: CloudNaturalLanguage
    @Mock private lateinit var annotateTextResponse: AnnotateTextResponse


    @Before fun setUp() {
        analyzeSentiment = AnalyzeSentiment(naturalLanguage)

        given { naturalLanguage.documents().annotateText(createAnnotateTextRequest()).execute() }.willReturn(annotateTextResponse)
//        given { annotateTextResponse.documentSentiment }.willReturn(sentiment)

    }

    @Test fun `should call Cloud Natural Language execute`() {
        runBlocking {
            val networkResponse = analyzeSentiment.run(AnalyzeSentiment.Params(MockedObjects.tweetMessage))
            assert(networkResponse is NetworkResponse.Success<*>)
        }

    }

    private fun createAnnotateTextRequest(): AnnotateTextRequest {
        val document = Document()
        document.type = "PLAIN_TEXT"
        document.content = MockedObjects.tweetMessage

        val features = Features()
        features.extractDocumentSentiment = true

        val request = AnnotateTextRequest()
        request.document = document
        request.features = features

        return request
    }
}
