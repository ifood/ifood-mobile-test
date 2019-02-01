package com.andre.test.feature

    import com.andre.test.BaseUnitTest
    import com.andre.test.core.network.NetworkResponse
    import com.andre.test.features.AnalyzeSentiment
    import com.andre.test.mock.MockedObjects
    import com.google.api.services.language.v1.CloudNaturalLanguage
    import com.google.api.services.language.v1.model.*
    import com.nhaarman.mockitokotlin2.given
    import kotlinx.coroutines.runBlocking
    import org.junit.Before
    import org.junit.Test
    import org.mockito.Mock
    import java.io.IOException

class AnalyzeSentimentTest: BaseUnitTest() {

    private lateinit var analyzeSentiment: AnalyzeSentiment

    @Mock private lateinit var naturalLanguage: CloudNaturalLanguage
    @Mock private lateinit var documents: CloudNaturalLanguage.Documents
    @Mock private lateinit var annotateTextResponse: AnnotateTextResponse
    @Mock private lateinit var annotateText: CloudNaturalLanguage.Documents.AnnotateText
    @Mock private lateinit var sentiment: Sentiment

    @Before fun setUp() {
        analyzeSentiment = AnalyzeSentiment(naturalLanguage)
    }

    @Test fun `should return NetworkResponseSuccess with valid text`() {
        given { naturalLanguage.documents() }.willReturn(documents)
        given { documents.annotateText(createAnnotateTextRequest(MockedObjects.validTweetMessage)) }.willReturn(annotateText)
        given { naturalLanguage.documents().annotateText(createAnnotateTextRequest(MockedObjects.validTweetMessage)).execute() }.willReturn(annotateTextResponse)
        given { annotateTextResponse.documentSentiment }.willReturn(sentiment)

        runBlocking {
            val networkResponse = analyzeSentiment.run(AnalyzeSentiment.Params(MockedObjects.validTweetMessage))
            assert(networkResponse is NetworkResponse.Success<*>)
        }
    }

    @Test fun `should return NetworkResponseFetchFailure with invalid text`() {
        given { naturalLanguage.documents() }.willReturn(documents)
        given { documents.annotateText(createAnnotateTextRequest(MockedObjects.invalidTweetMessage)) }.willReturn(annotateText)
        given { naturalLanguage.documents().annotateText(createAnnotateTextRequest(MockedObjects.invalidTweetMessage)).execute() }.willThrow(IOException())

        runBlocking {
            val networkResponse = analyzeSentiment.run(AnalyzeSentiment.Params(MockedObjects.invalidTweetMessage))
            assert(networkResponse is NetworkResponse.FetchFailure)
        }
    }

    private fun createAnnotateTextRequest(content: String): AnnotateTextRequest {
        val document = Document()
        document.type = "PLAIN_TEXT"
        document.content = content

        val features = Features()
        features.extractDocumentSentiment = true

        val request = AnnotateTextRequest()
        request.document = document
        request.features = features

        return request
    }
}
