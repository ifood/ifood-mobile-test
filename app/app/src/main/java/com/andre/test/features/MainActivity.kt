package com.andre.test.features

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andre.test.R
import com.andre.test.features.UiState.*
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.extensions.android.json.AndroidJsonFactory
import com.google.api.services.language.v1.CloudNaturalLanguage
import com.google.api.services.language.v1.CloudNaturalLanguageRequestInitializer
import com.twitter.sdk.android.core.models.Tweet
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    private val listAdapter = TweetListAdapter {
        viewModel.analyzeSentiment(naturalLanguageService, it.text)
    }

    private lateinit var viewModel: MainViewModel
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

        tweet_list.visibility = VISIBLE
        tweet_list.layoutManager = LinearLayoutManager(this)
        tweet_list.adapter = listAdapter

        viewModel.uiState.observe(this, Observer { uiState ->
            when (uiState) {
                is Initial -> showInitialText()
                is Loading -> {
                    hideDataList()
                    hideInfoText()
                    showProgress()
                }
                is Empty -> {
                    hideProgress()
                    hideDataList()
                    showEmptyText()
                }
                is Error -> {
                    hideProgress()
                    hideDataList()
                    showErrorText(uiState.errorId)
                }
                is Success -> {
                    hideProgress()
                    hideInfoText()
                    showDataList()
                }
            }
        })

        viewModel.tweetData.observe(this, Observer(this::loadList))

        viewModel.analyzeData.observe(this, Observer {
            Toast.makeText(this@MainActivity, "Sentiment ${it.score} : ${it.magnitude}", Toast.LENGTH_SHORT).show()
        })

    }

    private fun showDataList() {
        tweet_list.visibility = VISIBLE
    }

    private fun hideDataList() {
        tweet_list.visibility = GONE
    }

    private fun showProgress() {
        progressbar.visibility = VISIBLE
    }

    private fun hideProgress() {
        progressbar.visibility = GONE
    }

    private fun hideInfoText() {
        info_tv.visibility = GONE
    }

    private fun setInfoText(stringId: Int) {
        info_tv.visibility = VISIBLE
        info_tv.text = getString(stringId)
    }

    private fun showInitialText() = setInfoText(R.string.initial_text)

    private fun showEmptyText() = setInfoText(R.string.empty_tweet_text)

    private fun showErrorText(errorId: Int) = setInfoText(errorId)

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
