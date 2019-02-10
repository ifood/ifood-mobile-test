package movile.marcus.com.br.moviletest.model.remote

import io.reactivex.Flowable
import movile.marcus.com.br.moviletest.model.data.TweetData
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitterApi {

    @GET("1.1/statuses/user_timeline.json")
    fun getTweetsByUsername(@Query("screen_name") username: String): Flowable<List<TweetData>>
}