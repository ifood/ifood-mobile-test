package br.com.matheusbrandino.ifoodtest.api.twitter

import br.com.matheusbrandino.ifoodtest.model.TweetDto
import br.com.matheusbrandino.ifoodtest.model.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

class TwitterApi(retrofit: Retrofit) {

    private val service: TwitterService by lazy {
        retrofit.create(TwitterService::class.java)
    }

    fun searchTweets(
        username: String,
        sucess: (List<Tweet>) -> Unit,
        fallback: (Throwable) -> Unit
    ) {


        service.searchTweets(username).enqueue(object : Callback<TweetDto?> {
            override fun onFailure(call: Call<TweetDto?>, t: Throwable) {

                fallback(t)
            }

            override fun onResponse(call: Call<TweetDto?>, response: Response<TweetDto?>) {


                response.body()?.let {
                    sucess(it.tweets)
                }
            }
        })
    }

    private interface TwitterService {

        @GET("/1.1/search/tweets.json")
        fun searchTweets(
            @Query("q") username: String,
            @Query("lang") language: String = "en"
        ): Call<TweetDto>
    }
}