package com.drury.twittermoodanalyzer.api

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.drury.twittermoodanalyzer.R
import com.drury.twittermoodanalyzer.api.google.GoogleLanguageService
import com.drury.twittermoodanalyzer.api.twitter.TwitterService
import com.drury.twittermoodanalyzer.utils.AppConstants
import com.google.gson.Gson
import com.twitter.sdk.android.core.models.Tweet
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.RequestBody

class ConnectionManager(private val context: Context): IConnectionManager {

    override fun hasInternetConnection(): Boolean {
        val localConnectivityManager = context.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager ?: return false

        val localNetworkInfo = localConnectivityManager.activeNetworkInfo

        return (localNetworkInfo != null && localNetworkInfo.isConnected
                && localNetworkInfo.isAvailable)

    }

    override fun getTweetsByUserName(user: String): Observable<List<Tweet>> {
        val api = TwitterServiceFactory(context).createService(TwitterService::class.java)
        return api.getTweetsByUsername(user)
    }



    override fun sendSentencesToCloudLanguageNatural(sentence: String): Observable<SentimentResult> {
        val api = GoogleServiceFactory().createService(GoogleLanguageService::class.java)
        val sentimentRequest = SentimentRequest(DocumentSentimentRequest(sentence))
        val requestBody = createRequestBody(Gson().toJson(sentimentRequest, SentimentRequest::class.java))
        val key = context.getString(R.string.gcp_api_key)
        return api.analyzeTweetMood(requestBody, key)
    }


    private fun createRequestBody(packet: String): RequestBody {
        return RequestBody.create(MediaType.parse(AppConstants.MEDIA_TYPE_JSON), packet)
    }

}