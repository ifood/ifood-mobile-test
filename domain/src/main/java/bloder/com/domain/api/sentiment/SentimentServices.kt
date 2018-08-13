package bloder.com.domain.api.sentiment

import bloder.com.domain.BuildConfig
import retrofit2.http.POST
import retrofit2.http.Query

interface SentimentServices {

    @POST("documents:analyzeSentiment")
    fun analyzeTweet(@Query("key") key: String = BuildConfig.GOOGLE_API_KEY)
}