package testCommon.utils

import com.ifood.challenge.home.model.Sentiment
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockServerDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when {
            isSearchUser(request) -> responseTwitterUsers()
            isUserTimeline(request) -> responseTwitterUserTimeline()

            isAnalyzeSentimentHappy(request) -> responseSentimentAnalyzedHappy()
            isAnalyzeSentimentNeutral(request) -> responseSentimentAnalyzedNeutral()
            isAnalyzeSentimentSad(request) -> responseSentimentAnalyzedSad()
            isAnalyzeSentimentBroken(request) -> responseSentimentAnalyzedBroken()

            else -> MockResponse().setResponseCode(404)
        }
    }

    /**
     * Twitter Service
     * */
    private fun responseTwitterUsers() =
        successResponse().setBody(JsonFile.Twitter.SEARCH_USER.json)

    private fun responseTwitterUserTimeline() =
        successResponse().setBody(JsonFile.Twitter.USER_TIMELINE.json)

    /**
     * Google Service
     * */
    private fun responseSentimentAnalyzedHappy() =
        successResponse().setBody(JsonFile.Google.ANALYZE_SENTIMENT_HAPPY.json)

    private fun responseSentimentAnalyzedNeutral() =
        successResponse().setBody(JsonFile.Google.ANALYZE_SENTIMENT_NEUTRAL.json)

    private fun responseSentimentAnalyzedSad() =
        successResponse().setBody(JsonFile.Google.ANALYZE_SENTIMENT_SAD.json)

    private fun responseSentimentAnalyzedBroken() =
        successResponse().setBody(JsonFile.Google.ANALYZE_SENTIMENT_BROKEN.json)


    /**
     * Helpers to check and get a correct response
     * */

    private fun isSearchUser(request: RecordedRequest) =
        (request.path ?: "").startsWith(URL_SEARCH_USER) && METHOD_GET == request.method

    private fun isUserTimeline(request: RecordedRequest) =
        (request.path ?: "").startsWith(URL_USER_TIMELINE) && METHOD_GET == request.method


    private fun isAnalyzeSentimentHappy(request: RecordedRequest) =
        (request.path ?: "").startsWith(URL_ANALYZE_SENTIMENT) && METHOD_POST == request.method
                && request.body.toString().contains(Sentiment.Happy.toString())

    private fun isAnalyzeSentimentNeutral(request: RecordedRequest) =
        (request.path ?: "").startsWith(URL_ANALYZE_SENTIMENT)
                && METHOD_POST == request.method
                && request.body.toString().contains(Sentiment.Neutral.toString())

    private fun isAnalyzeSentimentSad(request: RecordedRequest) =
        (request.path ?: "").startsWith(URL_ANALYZE_SENTIMENT)
                && METHOD_POST == request.method
                && request.body.toString().contains(Sentiment.Sad.toString())

    private fun isAnalyzeSentimentBroken(request: RecordedRequest) =
        (request.path ?: "").startsWith(URL_ANALYZE_SENTIMENT)
                && METHOD_POST == request.method
                && request.body.toString().contains("broken")

    private fun successResponse() = MockResponse().setResponseCode(200)

}

private const val METHOD_GET = "GET"
private const val METHOD_POST = "POST"

/**
 * Twitter Service
 * */
private const val URL_SEARCH_USER = "/1.1/users/search.json"
private const val URL_USER_TIMELINE = "/1.1/statuses/user_timeline.json"

/**
 * Google Service
 * */
private const val URL_ANALYZE_SENTIMENT = "/v1/documents:analyzeSentiment"
