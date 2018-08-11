package bloder.com.domain.api.search

import bloder.com.domain.payloads.SearchResponsePayload
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchServices {

    @GET("search/tweets.json") fun searchTweets(@Query("q") name: String) : Single<Response<SearchResponsePayload>>
}