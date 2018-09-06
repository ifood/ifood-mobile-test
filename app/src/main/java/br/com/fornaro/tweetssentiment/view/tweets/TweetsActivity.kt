package br.com.fornaro.tweetssentiment.view.tweets

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import br.com.fornaro.tweetssentiment.R
import br.com.fornaro.tweetssentiment.common.AppConstants
import br.com.fornaro.tweetssentiment.viewmodel.TweetsViewModel
import kotlinx.android.synthetic.main.activity_tweets.*

class TweetsActivity : AppCompatActivity() {

    private lateinit var viewModel: TweetsViewModel

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
//            adapter = viewAdapter
        }
    }

    private fun setupViewModel(username: String) {
        viewModel = ViewModelProviders.of(this).get(TweetsViewModel::class.java)
    }
}
