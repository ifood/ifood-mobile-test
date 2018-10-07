package com.test.ifood.twitterhumour.tweetlist.tweetsoverview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.test.ifood.twitterhumour.R
import com.test.ifood.twitterhumour.base.BaseActivity
import com.test.ifood.twitterhumour.databinding.ActivityTweetListBinding
import com.test.ifood.twitterhumour.delegate.contentView
import com.test.ifood.twitterhumour.model.Tweet
import com.test.ifood.twitterhumour.tweetlist.humour.HumourDialogFragment
import com.test.ifood.twitterhumour.tweetlist.tweetsoverview.adapter.TweetListAdapter
import com.test.ifood.twitterhumour.tweetlist.tweetsoverview.view.ItemTweetListView
import com.test.ifood.twitterhumour.tweetlist.tweetsoverview.viewmodel.TweetListViewModel
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class TweetListActivity: BaseActivity(), ItemTweetListView {

    companion object {
        private const val TWEET_LIST = "tweet.list"

        fun newIntent(context: Context, tweets: List<Tweet>): Intent {
            val intent = Intent(context, TweetListActivity::class.java)
            intent.putParcelableArrayListExtra(TWEET_LIST, ArrayList<Tweet>(tweets))

            return intent
        }
    }

    @Inject
    lateinit var viewModel: TweetListViewModel

    private val binding: ActivityTweetListBinding by contentView(R.layout.activity_tweet_list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet_list)
        binding.viewModel = viewModel

        if (!intent.extras.containsKey(TWEET_LIST)) {
            throw IllegalStateException("Missing tweet list.")
        }

        val tweets: List<Tweet> = intent.extras.getParcelableArrayList(TWEET_LIST)
        setupRecyclerView(tweets)
        setupToolbar(binding.toolbar as Toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView(tweets: List<Tweet>) {
        val recyclerView = binding.tweetListRecyclerView
        val adapter = TweetListAdapter(this, this, tweets)
        val manager = LinearLayoutManager(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = manager
    }

    override fun onTweetClicked(text: String) {
        viewModel.updateLoadingState(true)
        compositeDisposable.add(viewModel.processTweet(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            viewModel.updateLoadingState(false)
                            HumourDialogFragment.newInstance(it.sentiment.document.label).show(supportFragmentManager, "CAIO")
                        },
                        {
                            viewModel.updateLoadingState(false)
                            showErrorDialog(R.string.tweet_list_error_language_processing)
                        }))
    }
}

