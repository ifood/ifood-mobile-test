package br.com.tweetanalyzer

import android.app.IntentService
import android.content.Intent
import br.com.tweetanalyzer.api.ApiSearchService
import br.com.tweetanalyzer.api.TwitterConstants
import br.com.tweetanalyzer.eventbus.TokenRetrieveEvent
import br.com.tweetanalyzer.eventbus.TwetterListResult
import br.com.tweetanalyzer.util.Constant
import br.com.tweetanalyzer.util.Util
import org.greenrobot.eventbus.EventBus

/**
 * Created by gabrielsamorim
 * on 15/05/18.
 */
class TwitterService : IntentService("RequestTwitterList") {

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            when (intent.getIntExtra(Constant.JOB_TYPE, -1)) {
                Constant.JOB_TYPE_GET_AUTH -> getAuth()
                Constant.JOB_GET_USER_INFO -> getUserInfo(intent.getStringExtra(Constant.SEARCH_USER_INPUT))
                Constant.JOB_TYPE_SEARCH_INPUT -> searchTwitter(intent.getStringExtra(Constant.SEARCH_INPUT))
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
            EventBus.getDefault().post(ApiSearchService().getTwitterUser("Bearer " + PreferenceController.getToken(baseContext), userName))

    private fun searchTwitter(twitterUser: String) =
            EventBus.getDefault().post(TwetterListResult(ApiSearchService().getTwitterList("Bearer " + PreferenceController.getToken(baseContext), twitterUser)))
}