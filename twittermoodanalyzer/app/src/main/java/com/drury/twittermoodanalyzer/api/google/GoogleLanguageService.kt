package com.drury.twittermoodanalyzer.api.google

import com.drury.twittermoodanalyzer.api.SentimentResult
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface GoogleLanguageService {
    // Check
    // https://cloud.google.com/natural-language/docs/reference/rest/v1/documents/analyzeSentiment
    // to get more information about the API
    @POST("v1/documents:analyzeSentiment")
    fun analyzeTweetMood(@Body requestBody: RequestBody, @Query("key") key: String): Observable<SentimentResult>
}