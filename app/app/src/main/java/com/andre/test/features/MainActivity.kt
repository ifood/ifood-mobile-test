package com.andre.test.features

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andre.test.R
import com.andre.test.core.di.AppComponent
import com.andre.test.core.extension.hideKeyboard
import com.andre.test.features.UiState.*
import com.twitter.sdk.android.core.models.Tweet
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.sentiment_dialog_layout.view.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as TestApplication).appComponent
    }

    @Inject
    lateinit var viewModel: MainViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val listAdapter = TweetListAdapter {
        viewModel.analyzeSentiment(it.text)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]

        tweet_list.visibility = VISIBLE
        tweet_list.layoutManager = LinearLayoutManager(this)
        tweet_list.adapter = listAdapter

        viewModel.uiState.observe(this, Observer { uiState ->
            when (uiState) {
                is Initial -> showInitialText()
                is Loading -> {
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

        viewModel.analyzeData.observe(this, Observer(this::showSentimentDialog))

    }

    private fun showDataList() {
        tweet_list.visibility = VISIBLE
    }

    private fun hideDataList() {
        tweet_list.visibility = GONE
    }

    private fun showProgress() {
        progress_background.visibility = VISIBLE
        progressbar.visibility = VISIBLE
    }

    private fun hideProgress() {
        progress_background.visibility = GONE
        progressbar.visibility = GONE
    }

    private fun hideInfoText() {
        info_tv.visibility = GONE
    }

    private fun setInfoText(stringId: Int) {
        info_tv.visibility = VISIBLE
        info_tv.text = getString(stringId)
    }

    private fun showSentimentDialog(sentimentState: SentimentState) {
        val dialog = Dialog(this)
        val view = with(layoutInflater.inflate(R.layout.sentiment_dialog_layout, null)) {
            when (sentimentState) {
                is SentimentState.HappyTweet -> {
                    this.sentiment_emoji.text = String(Character.toChars(0x1F600))
                    setBackgroundColor(resources.getColor(R.color.happyColor, null))
                }
                is SentimentState.NeutralTweet -> {
                    this.sentiment_emoji.text = String(Character.toChars(0x1F610))
                    setBackgroundColor(resources.getColor(R.color.neutralColor, null))
                }
                is SentimentState.SadTweet -> {
                    this.sentiment_emoji.text = String(Character.toChars(0x1F61E))
                    setBackgroundColor(resources.getColor(R.color.sadColor, null))
                }
            }

            setOnClickListener {
                dialog.dismiss()
            }
            this
        }

        dialog.setContentView(view)

        dialog.show()
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
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
                searchView.clearFocus()
                hideKeyboard()
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
