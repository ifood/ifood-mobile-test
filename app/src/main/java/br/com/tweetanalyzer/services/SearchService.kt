package br.com.tweetanalyzer.services

import android.app.IntentService
import android.content.Intent
import br.com.tweetanalyzer.api.googlelanguageapi.NLanguageSearch
import br.com.tweetanalyzer.api.twitterapi.ApiSearchService
import br.com.tweetanalyzer.api.twitterapi.TwitterConstants
import br.com.tweetanalyzer.controller.PreferenceController
import br.com.tweetanalyzer.events.AnalyseSearchResult
import br.com.tweetanalyzer.events.TokenRetrieveEvent
import br.com.tweetanalyzer.events.TwetterListResult
import br.com.tweetanalyzer.events.UserInfoEvent
import br.com.tweetanalyzer.models.Document
import br.com.tweetanalyzer.models.JobType
import br.com.tweetanalyzer.models.TwitterModel
import br.com.tweetanalyzer.services.util.ServiceConstants
import br.com.tweetanalyzer.util.Util
import com.google.gson.Gson
import org.greenrobot.eventbus.EventBus

/**
 * Created by gabrielsamorim
 * on 15/05/18.
 */
class SearchService : IntentService("RequestTwitterList") {

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            val job = intent.getStringExtra(ServiceConstants.JOB_TYPE)
            val type = JobType.valueOf(job.toString())
            when (type) {
                JobType.GET_AUTH -> getAuth()
                JobType.GET_USER_INFO -> getUserInfo(intent.getStringExtra(ServiceConstants.SEARCH_USER_INPUT))
                JobType.SEARCH_INPUT -> searchTwitter(intent.getStringExtra(ServiceConstants.SEARCH_INPUT))
                JobType.ANALYSE_SENTIMENT -> analyseSentiment(Gson().fromJson(intent.getStringExtra(ServiceConstants.ANALYSE_SENTIMENT), TwitterModel::class.java))
            }
        }
    }

    private fun getAuth() {
        val token = ApiSearchService().getAuth("Basic " + Util.getBase64(TwitterConstants.TOKEN_TWITTER), "client_credentials")
        if (token != null) {
            PreferenceController.setToken(this, token.token)
            PreferenceController.setTokenType(this, token.tokenType)

            EventBus.getDefault().post(TokenRetrieveEvent(true))
        } else
            EventBus.getDefault().post(TokenRetrieveEvent(false))
    }

    private fun getUserInfo(userName: String) =
            EventBus.getDefault().post(UserInfoEvent(ApiSearchService().getTwitterUser("Bearer " + PreferenceController.getToken(baseContext), userName)))

    private fun searchTwitter(twitterUser: String) =
            EventBus.getDefault().post(TwetterListResult(ApiSearchService().getTwitterList("Bearer " + PreferenceController.getToken(baseContext), twitterUser)))

    private fun analyseSentiment(item: TwitterModel) =
            EventBus.getDefault().post(AnalyseSearchResult(NLanguageSearch().analyseText(Document(item.description)), item))
}
