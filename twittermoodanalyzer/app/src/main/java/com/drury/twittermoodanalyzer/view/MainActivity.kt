package com.drury.twittermoodanalyzer.view

import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import com.drury.twittermoodanalyzer.BaseApp
import com.drury.twittermoodanalyzer.Interfaces
import com.drury.twittermoodanalyzer.R
import com.drury.twittermoodanalyzer.model.TweetModel
import com.drury.twittermoodanalyzer.presenter.IPresenter
import com.drury.twittermoodanalyzer.presenter.MainPresenter
import com.drury.twittermoodanalyzer.view.adapter.TweetAdapter
import com.drury.twittermoodanalyzer.view.component.ViewDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), IView.MainActivity {

    lateinit var mainPresenter: IPresenter.MainPresenter

    lateinit var tweetAdapter: TweetAdapter

    lateinit var loadingDialog: ViewDialog

    var tweets: MutableList<TweetModel> = mutableListOf<TweetModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null && savedInstanceState.containsKey("TWEETS")) {
            tweets = savedInstanceState.getParcelableArrayList<TweetModel>("TWEETS")
        }

        (applicationContext as BaseApp).component.inject(this)
        initViewComponents()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.app_bar_search)

        if (searchItem!=null) {
            val searchView = searchItem.actionView as SearchView

            val searchHint = getString(R.string.search_hint)
            searchView.setQueryHint(searchHint)

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        mainPresenter.getTweets(query)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun initViewComponents() {
        mainPresenter = MainPresenter(this)
        mainPresenter.onCreate()
        tweetAdapter = TweetAdapter(tweets, this)
        loadingDialog = ViewDialog(this)
        my_recycler_view.layoutManager = LinearLayoutManager(this)
        my_recycler_view.adapter = tweetAdapter
    }

    override fun populateTweets(tweetList: MutableList<TweetModel>) {
        tweets = tweetList
        tweetAdapter = TweetAdapter(tweets, this)
        my_recycler_view.layoutManager = LinearLayoutManager(this)
        my_recycler_view.adapter = tweetAdapter
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            savedInstanceState.putParcelableArrayList("TWEETS", ArrayList<Parcelable>(tweets))
        }
        super.onSaveInstanceState(savedInstanceState)
    }

    override fun showLoadingDialog() {
        loadingDialog.showDialog()
    }

    override fun hideLoadingDialog() {
        loadingDialog.hideDialog()
    }

}
