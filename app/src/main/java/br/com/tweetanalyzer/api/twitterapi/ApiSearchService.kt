package br.com.tweetanalyzer.api.twitterapi

import br.com.tweetanalyzer.models.TokenType
import br.com.tweetanalyzer.models.TwitterModel
import br.com.tweetanalyzer.models.TwitterUserInfo
import br.com.tweetanalyzer.util.RetrofitBuilder

/**
 * Created by gabrielsamorim
 * on 16/05/18.
 */
class ApiSearchService {

    fun getTwitterUser(auth: String, search: String): TwitterUserInfo? = getTwitterAPI()?.getUserInfo(auth, search)?.execute()?.body()

    fun getTwitterList(auth: String, search: String): List<TwitterModel>? = getTwitterAPI()?.getTwitterList(auth, search)?.execute()?.body()

    fun getAuth(auth: String, authType: String): TokenType? = getTwitterAPI()?.getToken(auth, authType)?.execute()?.body()

    private fun getTwitterAPI(): TwitterInterface? =
            RetrofitBuilder().getRetrofit(TwitterConstants.SEARCH_URL_TWITTER)
                    .create(TwitterInterface::class.java)
}