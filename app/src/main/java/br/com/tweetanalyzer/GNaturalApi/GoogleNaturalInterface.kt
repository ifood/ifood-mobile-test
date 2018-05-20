package br.com.tweetanalyzer.GNaturalApi

import br.com.tweetanalyzer.models.SentimentalAnalyseRequest
import br.com.tweetanalyzer.models.SentimentalAnalyseResult
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
                    @Body request: SentimentalAnalyseRequest): Call<SentimentalAnalyseResult>
}