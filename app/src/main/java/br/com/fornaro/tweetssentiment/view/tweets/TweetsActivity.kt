package br.com.fornaro.tweetssentiment.view.tweets

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.widget.Toast
import br.com.fornaro.tweetssentiment.R
import br.com.fornaro.tweetssentiment.common.AppConstants
import br.com.fornaro.tweetssentiment.databinding.ActivityTweetsBinding
import br.com.fornaro.tweetssentiment.model.Tweet
import br.com.fornaro.tweetssentiment.viewmodel.TweetsViewModel
import kotlinx.android.synthetic.main.activity_tweets.*
import kotlinx.android.synthetic.main.content_scrolling.*

class TweetsActivity : AppCompatActivity(), TweetsAdapter.OnTweetListener {

    private lateinit var binding: ActivityTweetsBinding
    private lateinit var viewModel: TweetsViewModel
    private val viewAdapter = TweetsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tweets)

        val username = intent.extras?.getString(AppConstants.EXTRA_USERNAME)
                ?: throw Exception("extra_username is needed to be put on extras")

        toolbar.title = "@$username"

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

        viewModel.getUser(username).observe(this, Observer {
            binding.user = it
        })

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
