package movile.marcus.com.br.moviletest.model.api

import io.reactivex.Flowable
import movile.marcus.com.br.moviletest.BuildConfig
import movile.marcus.com.br.moviletest.model.data.SentimentResult
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface GoogleApi {

    @POST("v1/documents:analyzeSentiment")
    fun analyzeTweetMood(@Body requestBody: RequestBody, @Query("key") key: String = BuildConfig.GOOGLE_NATURAL_LANGUAGE): Flowable<SentimentResult>
}