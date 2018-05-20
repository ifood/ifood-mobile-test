package br.com.tweetanalyzer.GNaturalApi

import br.com.tweetanalyzer.models.Document
import br.com.tweetanalyzer.models.SentimentalAnalyseRequest
import br.com.tweetanalyzer.models.SentimentalAnalyseResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by gabrielsamorim
 * on 19/05/18.
 */
class NLanguageSearch {

    fun analyseText(doc: Document): SentimentalAnalyseResult? = getNaturalAPI()
            ?.analyzeText(NaturalLanguageConstant.GOOGLE_API_KEY, SentimentalAnalyseRequest(doc))?.execute()?.body()

    private fun getNaturalAPI(): GoogleNaturalInterface? {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
                .baseUrl(NaturalLanguageConstant.GOOGLE_NATURAL_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
                .create(GoogleNaturalInterface::class.java)
    }
}