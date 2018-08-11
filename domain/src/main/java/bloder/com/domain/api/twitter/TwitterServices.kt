package bloder.com.domain.api.twitter

import bloder.com.domain.payloads.auth.TwitterAuthPayload
import bloder.com.domain.payloads.search.SearchResponsePayload
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface TwitterServices {

    @GET("1.1/statuses/user_timeline.json")
    fun searchTweets(
            @Header("authorization") auth: String,
            @Query("screen_name") name: String
    ) : Single<Response<SearchResponsePayload>>

    @POST("oauth2/token")
    fun getAuthToken(
            @Header("authorization") auth: String,
            @Field("grant_type") grandType: String = "client_credentials"
    ) : Single<Response<TwitterAuthPayload>>
}