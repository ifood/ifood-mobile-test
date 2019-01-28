package com.andre.test.features

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andre.test.R

import kotlinx.android.synthetic.main.activity_main.*
import com.twitter.sdk.android.core.models.Tweet
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    private val listAdapter = TweetListAdapter {
        Toast.makeText(this, "Tweet from ${it.user.name} clicked", Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val viewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainViewModel::class.java)

        tweet_list.visibility = View.VISIBLE
        tweet_list.layoutManager = LinearLayoutManager(this)
        tweet_list.adapter = listAdapter

        viewModel.queryTweet()

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
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
