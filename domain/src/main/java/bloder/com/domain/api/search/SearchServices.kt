package bloder.com.domain.api.search

import bloder.com.domain.models.Status
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchServices {

    @GET("tweets.json") fun searchTweets(@Query("q") name: String) : Single<List<Status>>
}