package movile.marcus.com.br.moviletest.model

import io.reactivex.Observable
import movile.marcus.com.br.moviletest.model.data.TweetData
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("1.1/statuses/user_timeline.json")
    fun getTweetsByUsername(@Query("screen_name") username: String): Observable<List<TweetData>>
}