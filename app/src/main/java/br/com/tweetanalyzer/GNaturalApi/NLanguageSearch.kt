package br.com.tweetanalyzer.GNaturalApi

import br.com.tweetanalyzer.models.Document
import br.com.tweetanalyzer.models.SentimentalAnalyseRequest
import br.com.tweetanalyzer.models.SentimentalAnalyseResult
import br.com.tweetanalyzer.util.RetrofitBuilder


/**
 * Created by gabrielsamorim
 * on 19/05/18.
 */
class NLanguageSearch {

    fun analyseText(doc: Document): SentimentalAnalyseResult? = getNaturalAPI()
            ?.analyzeText(NaturalLanguageConstant.GOOGLE_API_KEY, SentimentalAnalyseRequest(doc))?.execute()?.body()

    private fun getNaturalAPI(): GoogleNaturalInterface? =
            RetrofitBuilder().getRetrofit(NaturalLanguageConstant.GOOGLE_NATURAL_API_URL)
                    .create(GoogleNaturalInterface::class.java)
}