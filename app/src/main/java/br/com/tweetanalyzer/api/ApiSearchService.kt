package br.com.tweetanalyzer.api

import br.com.tweetanalyzer.models.TokenType
import br.com.tweetanalyzer.models.TwitterModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by gabrielsamorim
 * on 16/05/18.
 */
class ApiSearchService {

    fun getTwitterList(auth: String, search: String): TwitterModel? = getTwitterAPI()?.getTwitterList(auth, search)?.execute()?.body()

    fun getAuth(auth: String, authType: String): TokenType? = getTwitterAPI()?.getToken(auth, authType)?.execute()?.body()

    private fun getTwitterAPI(): TwitterInterface? {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
                .baseUrl(TwitterConstants.SEARCH_URL_TWITTER)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
                .create(TwitterInterface::class.java)
    }

}