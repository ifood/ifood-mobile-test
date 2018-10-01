package br.com.andreyneto.ifood_mobile_test.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.andreyneto.ifood_mobile_test.AppExecutors
import br.com.andreyneto.ifood_mobile_test.R
import br.com.andreyneto.ifood_mobile_test.data.TweetRepository
import br.com.andreyneto.ifood_mobile_test.data.database.TweetDao
import br.com.andreyneto.ifood_mobile_test.data.database.TweetDatabase
import br.com.andreyneto.ifood_mobile_test.data.database.TweetEntry
import br.com.andreyneto.ifood_mobile_test.data.network.TweetNetworkDataSource
import br.com.andreyneto.ifood_mobile_test.utilities.InjectorUtils
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.extensions.android.json.AndroidJsonFactory
import com.google.api.services.language.v1beta2.CloudNaturalLanguage
import com.google.api.services.language.v1beta2.CloudNaturalLanguageRequestInitializer
import org.jetbrains.anko.doAsync
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder
import com.google.api.services.language.v1beta2.model.Document
import com.google.api.services.language.v1beta2.model.Features
import com.google.api.services.language.v1beta2.model.AnnotateTextRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    private var mAdapter: TweetAdapter? = null

    private var mViewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        val factory: MainViewModelFactory = InjectorUtils().provideMainActivityViewModelFactory(this)
        mViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel::class.java)

        editText.setOnEditorActionListener { editText, _, _ ->
            editText.text.let { charSequence ->
                hideKeyboard()
                if (charSequence.isNotEmpty()) {
                    mViewModel!!.getTweets(editText.text.toString()).removeObservers(this)
                    mViewModel!!.getTweets(editText.text.toString()).observe(this, Observer {
                        manageTweets(it)
                    })
                    showLoading()
                } else {
                    emptyScreen()
                }
            }
            true
        }

        setupRecycler()
    }

    private fun emptyScreen() {
        cardView.visibility = View.GONE
        val layoutParams: ConstraintLayout.LayoutParams = editText.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.verticalBias = 0.5f
        editText.layoutParams = layoutParams
    }

    private fun manageTweets(tweets: List<TweetEntry>?) {
        cardView.visibility = View.VISIBLE
        val layoutParams: ConstraintLayout.LayoutParams = editText.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.verticalBias = 0.0f
        editText.layoutParams = layoutParams
        mAdapter?.swapList(tweets!!)
        hideLoading()
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
            Log.e("$it - ${response.language} - $text", if (it > 0.25)"feliz" else if (it < -0.25) "triste" else "neutro")
        }
    }

    private fun setupRecycler() {
        mAdapter = TweetAdapter(this,  mutableListOf<TweetEntry>()) { tweet ->  clickHandler(tweet) }
        recyclerView.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = this.mAdapter
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    private fun clickHandler(tweet: TweetEntry) {
//        val sentimentIntent = Intent(this@MainActivity, SentimentActivity::class.java)
//        sentimentIntent.putExtra(SentimentActivity.TWEET_ID_EXTRA, tweetID)
//        startActivity(sentimentIntent)
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }
}
