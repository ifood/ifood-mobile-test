package br.com.tweetanalyzer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.twitter_list.*


/**
 * Created by gabrielsamorim
 * on 15/05/18.
 */
class TwitterList : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                android.R.id.home -> {
                    finish()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    lateinit var searchString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.twitter_list)

        getExtras()

        initLayout()

        startSearch()
    }

    private fun getExtras() {
        searchString = intent.getStringExtra(Constant.SEARCH_INPUT)
    }

    private fun initLayout() {
        if (supportActionBar != null)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

        twitter_recycler_view.layoutManager = LinearLayoutManager(this)
        twitter_recycler_view.itemAnimator = DefaultItemAnimator()
        twitter_recycler_view.visibility = View.GONE

        twitter_progress.visibility = View.VISIBLE
    }

    private fun startSearch() {
        val i = Intent(this, TwitterService::class.java)
        i.putExtra(Constant.SEARCH_INPUT, searchString)
        startService(i)
    }
}