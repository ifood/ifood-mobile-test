package br.com.andreyneto.ifood_mobile_test

import android.app.Application
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.extensions.android.json.AndroidJsonFactory
import com.google.api.services.language.v1beta2.CloudNaturalLanguage
import com.google.api.services.language.v1beta2.CloudNaturalLanguageRequestInitializer
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

class App : Application() {

    var naturalLanguageService: CloudNaturalLanguage? = null
    var twitter: Twitter? = null

    override fun onCreate() {
        super.onCreate()

        naturalLanguageService =
                CloudNaturalLanguage.Builder(
                        AndroidHttp.newCompatibleTransport(),
                        AndroidJsonFactory(),
                        null)
                        .setCloudNaturalLanguageRequestInitializer(
                                CloudNaturalLanguageRequestInitializer(BuildConfig.GoogleNaturalLanguageKey))
                        .setApplicationName(getString(R.string.app_name)).build()

        val cb = ConfigurationBuilder()
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(BuildConfig.TwitterApiKey)
                .setOAuthConsumerSecret(BuildConfig.TwitterSecretKey)
                .setOAuthAccessToken(BuildConfig.TwitterToken)
                .setOAuthAccessTokenSecret(BuildConfig.TwitterTokenSecret)
        val tf = TwitterFactory(cb.build())
        twitter = tf.instance
    }
}