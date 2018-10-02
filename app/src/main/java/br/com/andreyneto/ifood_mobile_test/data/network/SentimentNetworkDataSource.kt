package br.com.andreyneto.ifood_mobile_test.data.network

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.andreyneto.ifood_mobile_test.AppExecutors
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.extensions.android.json.AndroidJsonFactory
import com.google.api.services.language.v1beta2.CloudNaturalLanguage
import com.google.api.services.language.v1beta2.CloudNaturalLanguageRequestInitializer
import com.google.api.services.language.v1beta2.model.AnnotateTextRequest
import com.google.api.services.language.v1beta2.model.Document
import com.google.api.services.language.v1beta2.model.Features

class SentimentNetworkDataSource private constructor(
        private val mContext: Context,
        private val mExecutors: AppExecutors) {

    val loadingSentiment = MutableLiveData<Boolean>()
    val mDownloadedSentiment: MutableLiveData<Float> = MutableLiveData()

    internal fun fetchSentiment(tweetText: String) {
        Log.d(LOG_TAG, "Fetch weather started")
        mExecutors.networkIO().execute {
            loadingSentiment.postValue(true)
            getSentiment(tweetText)
            loadingSentiment.postValue(false)
        }
    }

    private fun getSentiment(text: String) {
        val naturalLanguageService = CloudNaturalLanguage.Builder(
                AndroidHttp.newCompatibleTransport(),
                AndroidJsonFactory(),
                null
        ).setCloudNaturalLanguageRequestInitializer(
                CloudNaturalLanguageRequestInitializer("AIzaSyA0iEyFW1k4q06lxJmSGszex2BXuNCbZgw")
        ).setApplicationName("ifood-mobile-test").build()

        val document = Document()
        document.type = "PLAIN_TEXT"
        document.content = text
        val features = Features()
        features.extractDocumentSentiment = true
        val request = AnnotateTextRequest()
        request.document = document
        request.features = features
        val response = naturalLanguageService.documents()
                .annotateText(request).execute()
        response.documentSentiment.score.let {
            mDownloadedSentiment.postValue(it)
        }
    }

    companion object {
        private val LOG_TAG = SentimentNetworkDataSource::class.java.simpleName

        private val LOCK = Any()
        private var sInstance: SentimentNetworkDataSource? = null

        fun getInstance(context: Context, executors: AppExecutors): SentimentNetworkDataSource? {
            Log.d(LOG_TAG, "Getting the network data source")
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = SentimentNetworkDataSource(context.applicationContext, executors)
                    Log.d(LOG_TAG, "Made new network data source")
                }
            }
            return sInstance
        }
    }

}