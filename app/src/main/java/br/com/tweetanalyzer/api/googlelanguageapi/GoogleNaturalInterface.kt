package br.com.tweetanalyzer.api.googlelanguageapi

import br.com.tweetanalyzer.models.TweetAnalyseRequest
import br.com.tweetanalyzer.models.TweetAnalyseResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by gabrielsamorim
 * on 19/05/18.
 */
interface GoogleNaturalInterface {

    @POST(NaturalLanguageConstant.SEARCH_URL)
    fun analyzeText(@Query("key") auth: String,
                    @Body request: TweetAnalyseRequest): Call<TweetAnalyseResult>
}