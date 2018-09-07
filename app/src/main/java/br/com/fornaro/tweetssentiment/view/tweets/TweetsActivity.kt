package br.com.fornaro.tweetssentiment.view.tweets

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.widget.Toast
import br.com.fornaro.tweetssentiment.R
import br.com.fornaro.tweetssentiment.common.AppConstants
import br.com.fornaro.tweetssentiment.model.Tweet
import br.com.fornaro.tweetssentiment.viewmodel.TweetsViewModel
import kotlinx.android.synthetic.main.activity_tweets.*

class TweetsActivity : AppCompatActivity(), TweetsAdapter.OnTweetListener {

    private lateinit var viewModel: TweetsViewModel
    private val viewAdapter = TweetsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweets)

        val username = intent.extras?.getString(AppConstants.EXTRA_USERNAME)
                ?: throw Exception("extra_username is needed to be put on extras")

        setupRecyclerView()
        setupViewModel(username)
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(this@TweetsActivity, DividerItemDecoration.VERTICAL))
            adapter = viewAdapter
        }
    }

    private fun setupViewModel(username: String) {
        viewModel = ViewModelProviders.of(this).get(TweetsViewModel::class.java)
        viewModel.callback = callback
        viewModel.getTweets(username).observe(this, Observer {
            if (it != null) {
                viewAdapter.setData(it)
            }
        })
    }

    override fun analyze(tweet: Tweet) {
        viewModel.analyze(tweet)?.observe(this, Observer {
            viewAdapter.notifyDataSetChanged()
        })
    }

    private val callback = object : TweetsCallback {
        override fun onNoInternetConnection() {
            viewAdapter.notifyDataSetChanged()
            Toast.makeText(this@TweetsActivity, R.string.no_internet, Toast.LENGTH_SHORT).show()
        }
    }
}
