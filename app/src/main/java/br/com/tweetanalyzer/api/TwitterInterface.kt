package br.com.tweetanalyzer.api

import br.com.tweetanalyzer.models.TokenType
import br.com.tweetanalyzer.models.TwitterModel
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by gabrielsamorim
 * on 16/05/18.
 */
interface TwitterInterface {

    @GET(TwitterConstants.SEARCH_USER_TIMELINE)
    fun getTwitterList(@Header("Authorization") authorization: String, @Query("screen_name") twitterUser: String): Call<List<TwitterModel>>

    @FormUrlEncoded
    @POST("/oauth2/token")
    fun getToken(@Header("Authorization") authorization: String, @Field("grant_type") type: String): Call<TokenType>
}