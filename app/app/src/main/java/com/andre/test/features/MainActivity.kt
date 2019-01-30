package com.andre.test.features

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andre.test.R
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.extensions.android.json.AndroidJsonFactory
import com.google.api.services.language.v1.CloudNaturalLanguage
import com.google.api.services.language.v1.CloudNaturalLanguageRequestInitializer
import com.google.api.services.language.v1.model.AnnotateTextRequest
import com.google.api.services.language.v1.model.Document
import com.google.api.services.language.v1.model.Features

import kotlinx.android.synthetic.main.activity_main.*
import com.twitter.sdk.android.core.models.Tweet
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val listAdapter = TweetListAdapter {
        val document = Document()
        document.type = "PLAIN_TEXT"
        document.content = it.text

        val features = Features()
        features.extractDocumentSentiment = true

        val request = AnnotateTextRequest()
        request.document = document
        request.features = features

        val job = GlobalScope.async(Dispatchers.IO) { naturalLanguageService.documents().annotateText(request).execute() }
        GlobalScope.launch (Dispatchers.Main) {
            val response = job.await()
            val sentiment = response.documentSentiment

            Toast.makeText(this@MainActivity, "Tweet from ${it.user.name} magnitude: ${sentiment.magnitude} score: ${sentiment.score}", Toast.LENGTH_LONG).show()
        }
    }

    private lateinit var viewModel : MainViewModel
    private val naturalLanguageService by lazy {
        CloudNaturalLanguage.Builder(
            AndroidHttp.newCompatibleTransport(),
            AndroidJsonFactory(),
            null
        ).setCloudNaturalLanguageRequestInitializer(
            CloudNaturalLanguageRequestInitializer("AIzaSyDqepAUcbxla0TGr-SYlPBNZQj-2gq5gt8")
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainViewModel::class.java)

        tweet_list.visibility = View.VISIBLE
        tweet_list.layoutManager = LinearLayoutManager(this)
        tweet_list.adapter = listAdapter

        viewModel.uiState.observe(this, Observer { uiState ->
            when (uiState) {
                is UiState.Loading -> showProgress()
                is UiState.Empty -> showEmptyList()
                is UiState.Error -> showEmptyList()
                is UiState.Sucess -> {
                    hideProgress()
                    hideEmptyList()
                }
            }
        })

        viewModel.tweetData.observe(this, Observer(this::loadList))

    }

    private fun showProgress() {
        progressbar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progressbar.visibility = View.GONE
    }

    private fun showEmptyList() {
        tweet_list.visibility = View.GONE
    }

    private fun hideEmptyList() {
        tweet_list.visibility = View.VISIBLE
    }

    private fun loadList(list: List<Tweet>) {
        listAdapter.submitList(list)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.queryTweet(query)
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
