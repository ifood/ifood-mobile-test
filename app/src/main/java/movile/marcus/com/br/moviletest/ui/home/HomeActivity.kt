package movile.marcus.com.br.moviletest.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_home.*
import movile.marcus.com.br.moviletest.R
import movile.marcus.com.br.moviletest.model.Status
import movile.marcus.com.br.moviletest.model.data.TweetAnalyzer
import movile.marcus.com.br.moviletest.model.data.TweetData
import movile.marcus.com.br.moviletest.ui.BaseActivity
import movile.marcus.com.br.moviletest.ui.BaseRecyclerAdapter
import movile.marcus.com.br.moviletest.ui.custom.CustomSentimentDialog

class HomeActivity : BaseActivity(), BaseRecyclerAdapter.OnItemClickListener {

    private val homeTweetListAdapter = HomeTweetListAdapter()
    private var user: String? = null

    private val homeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
    }

    private fun init() {
        setupRecyclerView()
        initObservers()
        if (homeViewModel.tweetResult.value == null) {
            homeViewModel.getLastSearch()
        }
    }

    private fun setupRecyclerView() {
        activityHomeTweetList?.layoutManager = LinearLayoutManager(
            applicationContext, LinearLayoutManager
                .VERTICAL, false
        )
        homeTweetListAdapter.listener = this
        activityHomeTweetList?.adapter = homeTweetListAdapter
    }

    private fun initObservers() {
        homeViewModel.tweetResult.observeResource(this, onSuccess = {
            completeLayout(it)
        }, onError = {
            when (it.status) {
                Status.INTERNET_ERROR -> {

                }
                else -> {
                    showDefaultError()
                }
            }
        })

        homeViewModel.googleResult.observeResource(this, onSuccess = {
            showSentimental(it.documentSentiment.score)
        }, onError = {
            when (it.status) {
                Status.INTERNET_ERROR -> {

                }
                else -> {
                    showDefaultError()
                }
            }
        })
    }

    private fun completeLayout(tweeList: List<TweetData>) {
        homeTweetListAdapter.addToList(tweeList as ArrayList<TweetData>)
    }

    private fun showSentimental(score: Double) {
        val sentimental = TweetAnalyzer(score).getSentimental()
        CustomSentimentDialog.Builder(sentimental, this@HomeActivity).build().show()
    }

    private fun showDefaultError() {
        val snackbar = Snackbar.make(activityHomeContainer, getString(R.string.text_error), Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(getString(R.string.btn_retry)) {
            homeViewModel.getLastSearch()
        }
        snackbar.show()
    }

    override fun onItemClick(view: View, position: Int) {
        val tweetData = homeTweetListAdapter.getItem(position)
        tweetData.text?.let {
            homeViewModel.getTextAnalyzer(it)
        }
    }
}