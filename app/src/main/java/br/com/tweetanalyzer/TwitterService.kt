package br.com.tweetanalyzer

import android.app.IntentService
import android.content.Intent

/**
 * Created by gabrielsamorim
 * on 15/05/18.
 */
class TwitterService : IntentService("RequestTwitterList") {

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            val twitterUser = intent.getStringExtra(Constant.SEARCH_INPUT)

            if (twitterUser != null)
                searchTwitter(twitterUser)
        }
    }

    private fun searchTwitter(twitterUser: String) {

    }
}