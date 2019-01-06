package com.drury.twittermoodanalyzer.api

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.drury.twittermoodanalyzer.api.twitter.TwitterService
import com.drury.twittermoodanalyzer.utils.AppConstants
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.RequestBody

class ConnectionManager(private val context: Application): IConnectionManager {

    override fun hasInternetConnection(): Boolean {
        val localConnectivityManager = context.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager ?: return false

        val localNetworkInfo = localConnectivityManager.activeNetworkInfo

        return (localNetworkInfo != null && localNetworkInfo.isConnected
                && localNetworkInfo.isAvailable)

    }

    override fun getTweetsByUserName(user: String): Observable<TweetResult> {
        val api = TwitterServiceFactory(context).createService(TwitterService::class.java)
        return api.getTweetsByUsername(user)
    }



    override fun sendSentencesToCloudLanguageNatural(sentence: String): Observable<SentimentResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun createRequestBody(packet: String): RequestBody {
        return RequestBody.create(MediaType.parse(AppConstants.MEDIA_TYPE_JSON), packet)
    }

}