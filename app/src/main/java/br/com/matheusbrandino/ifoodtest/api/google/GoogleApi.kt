package br.com.matheusbrandino.ifoodtest.api.google

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

class GoogleApi(retrofit: Retrofit) {

    private val service: GoogleService by lazy {
        retrofit.create(GoogleService::class.java)
    }

    fun analyseTweet(
        request: GoogleRequest,
        sucess: (Sentiment) -> Unit,
        fallback: (Throwable) -> Unit
    ) {


        service.analyseTweet(request).enqueue(object : Callback<GoogleResponse?> {
            override fun onFailure(call: Call<GoogleResponse?>, t: Throwable) {

                fallback(t)
            }

            override fun onResponse(call: Call<GoogleResponse?>, response: Response<GoogleResponse?>) {

                response.body()?.let {
                    sucess(it.sentiment)
                }
            }
        })
    }

    private interface GoogleService {

        @POST("v1/documents:analyzeSentiment")
        fun analyseTweet(
            @Body googleRequest: GoogleRequest,
            @Query("key") username: String = "AIzaSyDeaiVJyYIgi8qOWHx_XiSK2Cd9bxlLgMM"
        ): Call<GoogleResponse>
    }
}