package movile.marcus.com.br.moviletest.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*
import movile.marcus.com.br.moviletest.R
import movile.marcus.com.br.moviletest.model.Status
import movile.marcus.com.br.moviletest.model.data.TweetAnalyzer
import movile.marcus.com.br.moviletest.model.data.TweetData
import movile.marcus.com.br.moviletest.ui.BaseActivity
import movile.marcus.com.br.moviletest.ui.BaseRecyclerAdapter
import movile.marcus.com.br.moviletest.ui.custom.CustomSentimentDialog

class HomeActivity : BaseActivity(), BaseRecyclerAdapter.OnItemClickListener, SearchView.OnQueryTextListener {

    private val homeTweetListAdapter = HomeTweetListAdapter()
    private var customSentimentDialog: CustomSentimentDialog? = null

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
        setupToolbar()
        setupRecyclerView()
        initObservers()
        if (homeViewModel.tweetResult.value == null) {
            homeViewModel.getLastSearch()
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(activityHomeToolbar as Toolbar)
        supportActionBar?.title = ""
        toolbarTitle.text = resources.getString(R.string.app_name)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val myActionMenuItem = menu?.findItem(R.id.action_search)
        val searchView = myActionMenuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return true
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
            it.documentSentiment?.let { sentiment ->
                showSentimental(sentiment.score)
                homeViewModel.removeGoogleResult()
            }
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
        customSentimentDialog = CustomSentimentDialog.Builder(sentimental, this@HomeActivity).build()
        customSentimentDialog?.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        customSentimentDialog?.dismiss()
    }

    private fun showDefaultError() {
        val snackbar = Snackbar.make(activityHomeContainer, getString(R.string.text_error), Snackbar.LENGTH_LONG)
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

    override fun onQueryTextSubmit(text: String?): Boolean {
        text?.let {
            homeViewModel.getTweetByUser(it)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }
}