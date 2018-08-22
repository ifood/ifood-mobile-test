package bloder.com.domain.api.sentiment

import bloder.com.domain.BuildConfig
import bloder.com.domain.payloads.sentiment.request.SentimentAnalyzerRequest
import bloder.com.domain.payloads.sentiment.response.SentimentAnalyzerResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface SentimentServices {

    @POST("./documents:analyzeSentiment")
    fun analyzeTweet(
            @Query("key") key: String = BuildConfig.GOOGLE_API_KEY,
            @Body request: SentimentAnalyzerRequest
    ) : Single<Response<SentimentAnalyzerResponse>>
}